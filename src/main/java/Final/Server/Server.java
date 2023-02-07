package Final.Server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Server extends Application{
    //비동기 채널 그룹
    AsynchronousChannelGroup channelGroup;
    //클라이언트 연결 수락
    AsynchronousServerSocketChannel serverSocketChannel;
    List<Client> connections = new Vector<Client>();
    String id;
    RoomManager roomManager;

    public Server() {
        this.roomManager = new RoomManager(connections);
    }

    //서버 시작
    void startServer() {
        try {
            //비동기 채널 그룹 생성
            channelGroup = AsynchronousChannelGroup.withFixedThreadPool(Runtime.getRuntime().availableProcessors(), Executors.defaultThreadFactory());
            //서버소켓 채널 열기
            serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
            //아이피 포트 바인드
            serverSocketChannel.bind(new InetSocketAddress(50001));
        }catch(Exception e) {
            if(serverSocketChannel.isOpen()) {
                stopServer();
            }
            return;
        }
        Platform.runLater(()->{
            displayText("[서버 시작]");
            btnStartStop.setText("stop");
        });

        //콜백 메소드 CompletionHandler 구현
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>(){

            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
                try {
                    String message = "[연결 수락: "+socketChannel.getRemoteAddress() + " : "+Thread.currentThread().getName() + "]";
                    Platform.runLater(()->displayText(message));
                }catch(IOException e) {

                }

                Client client = new Client(socketChannel, id, connections, roomManager);
                connections.add(client);
                Platform.runLater(()->displayText("[연결 개수: "+connections.size() + "]"));
                serverSocketChannel.accept(null, this); //실제 accept동작
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                if(serverSocketChannel.isOpen()) {
                    stopServer();
                }
            }

        }); //accept()종료

    } //startServer()종료

    //서버 종료
    void stopServer() {
        try {
            connections.clear();
            if(channelGroup != null && !channelGroup.isShutdown()) {
                channelGroup.shutdown();
            }
            Platform.runLater(()->{
                displayText("[서버 종료]");
                btnStartStop.setText("start");
            });
        }catch(Exception e) {

        }
    }

    //데이터를 통신하는 객체
    class Client{

        public List<Client> connections;
        public RoomManager roomManager;
        public Room room;
        AsynchronousSocketChannel socketChannel;

        public Client(AsynchronousSocketChannel socketChannel, String id,List<Client> connections, RoomManager roomManager) {
                this.socketChannel = socketChannel;
                this.connections = connections;
                this.roomManager = roomManager;
                this.room = null;
                receive();
            }

            //클라이언트로부터 데이터 받기
            void receive() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>(){

                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    try {
                        String message = "[요청 처리: "+socketChannel.getRemoteAddress() +" : "+Thread.currentThread().getName() + "]";
                        Platform.runLater(()->displayText(message));

                        attachment.flip();
                        Charset charset = Charset.forName("utf-8");
                        String data = charset.decode(attachment).toString();
                        //====================Json 시작
                        JSONParser jsonParser = new JSONParser();
                        JSONObject token = (JSONObject) jsonParser.parse(data);
                        String method = token.get("method").toString();
                        System.out.println(method);

                        switch (method) {
                            // #방생성
                            case "/room/create":
                                System.out.println("room : "+ room);
                                if (room == null) {
                                    System.out.println("실행함1?");
                                    System.out.println(Client.this);
                                    System.out.println(token.get("title").toString());

                                    roomManager.createRoom( token.get("title").toString(), Client.this);
                                    System.out.println("실행함?");
                                    Platform.runLater(()-> {
                                        try {
                                            displayText("[채팅서버] 채팅방 개설 " + socketChannel.getRemoteAddress());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    Platform.runLater(()->displayText("[채팅서버] 현재 채팅방 갯수 " + roomManager.rooms.size()));
                                }
                                break;
                            // #방입장
                            case "/room/entry":
                                if (room == null) {
                                    for (int i = 0; i < roomManager.rooms.size(); i++) {
                                        if (roomManager.rooms.get(i).id.equals(token.get("id").toString())) {
                                            roomManager.rooms.get(i).entryRoom(Client.this);
                                            Platform.runLater(()-> {
                                                try {
                                                    displayText("[채팅서버] 채팅방 입장" + socketChannel.getRemoteAddress());
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            });
                                            break;
                                        }
                                    }
                                }
                                break;
                            // #방탈출
                            case "/room/leave":
                                if (room != null) {
                                    Client.this.room.leaveRoom(Client.this);
                                    Platform.runLater(()-> {
                                        try {
                                            displayText("[채팅서버] 채팅방 나감" + socketChannel.getRemoteAddress());
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                }
                                break;
                            // #쳇전송
                            case "/chat/send":
                                if (room != null) {
                                    for (Client c : room.clients) {
                                        if (c != Client.this) c.sendEcho(token.get("id").toString(), token.get("message").toString());
                                    }
                                }
                                break;
                            default:
                                Platform.runLater(()-> {
                                    try {
                                        displayText("[채팅서버] 메소드가 올바르지 않습니다. : " + socketChannel.getRemoteAddress());
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                        }
                        //====================Json 끝
                        System.out.println(data);

                        for(Client  client : connections) {
                            client.send(data); //모든 클라이언트에게 보내기
                        }

                        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                        socketChannel.read(byteBuffer, byteBuffer, this); //데이터 다시 읽기
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try{
                        String message = "[클라이언트 통신 안됨: "+socketChannel.getRemoteAddress()+" : " + Thread.currentThread().getName() + "]";
                        Platform.runLater(()->displayText(message));
                        connections.remove(Client.this);
                        socketChannel.close();
                    }catch(IOException e) {

                    }

                }

            });
        }



        //클라이언트로 데이터 전송
        void send(String data) {
            Charset charset = Charset.forName("utf-8");
            ByteBuffer byteBuffer = charset.encode(data);
            socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){

                @Override
                public void completed(Integer result, Void attachment) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    try{
                        String message = "[클라이언트 통신 안됨: "+socketChannel.getRemoteAddress()+" : " + Thread.currentThread().getName() + "]";
                        Platform.runLater(()->displayText(message));
                        connections.remove(Client.this);
                        socketChannel.close();
                    }catch(IOException e) {

                    }
                }

            });
        }


        /**
         * [ Method :: sendStatus ]
         *
         * @DES :: entry & leave 의 상태에 따라 계속적으로 클라이언트에게 인원수 전송
         * @S.E :: "method" /room/status
         */
        public void sendStatus() {
            String packet = String.format("{\"method\":\"%s\",\"rooms\":%s}", "/room/status", roomManager.roomStatus);
            Charset charset = Charset.forName("utf-8");
            ByteBuffer byteBuffer = charset.encode(packet);
            socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){

                @Override
                public void completed(Integer result, Void attachment) {
                    String message = "\"[채팅서버] 송신 /room/status\"]";
                    Platform.runLater(()->displayText(message));

                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    try{
                        String message = "[클라이언트 통신 안됨: "+socketChannel.getRemoteAddress()+" : " + Thread.currentThread().getName() + "]";
                        Platform.runLater(()->displayText(message));
                        connections.remove(Client.this);
                        socketChannel.close();
                    }catch(IOException e) {

                    }
                }

            });
        }

        private void sendEcho(String id, String message) {
            String packet = String.format("{\"method\":\"%s\",\"id\":\"%s\",\"message\":\"%s\"}", "/chat/echo", id, message);
            Charset charset = Charset.forName("utf-8");
            ByteBuffer byteBuffer = charset.encode(packet);
            socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){

                @Override
                public void completed(Integer result, Void attachment) {
                    try {
                        String message = "[채팅서버] 송신 /chat/echo : \"" + socketChannel.getRemoteAddress();
                        Platform.runLater(()->displayText(message));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    try{
                        String message = "[클라이언트 통신 안됨: "+socketChannel.getRemoteAddress()+" : " + Thread.currentThread().getName() + "]";
                        Platform.runLater(()->displayText(message));
                        connections.remove(Client.this);
                        socketChannel.close();
                    }catch(IOException e) {

                    }
                }

            });
        }
    }

    //UI 생성 코드
    TextArea txtDisplay;
    Button btnStartStop;
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 300);

        txtDisplay = new TextArea();
        txtDisplay.setEditable(false);
        BorderPane.setMargin(txtDisplay, new Insets(0,0,2,0));
        root.setCenter(txtDisplay);

        btnStartStop = new Button("start");
        btnStartStop.setPrefHeight(30);
        btnStartStop.setMaxWidth(Double.MAX_VALUE);
        btnStartStop.setOnAction(e->{
            if(btnStartStop.getText().equals("start")) {
                startServer();
            }else {
                stopServer();
            }
        });
        root.setBottom(btnStartStop);

        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("app.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        //닫기 버튼 이벤트 처리
        primaryStage.setOnCloseRequest(event->stopServer());
        primaryStage.show();
    }

    void displayText(String text) {
        txtDisplay.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
