package Final.Client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Client {
    AsynchronousChannelGroup channelGroup;
    AsynchronousSocketChannel socketChannel;

    TextArea txtDisplay;
    ListView listView;

    Room room;
    String id;

    public void setListView(ListView listView) {
        this.listView = listView;
        System.out.println("listView : "+ listView);
    }
    public void setTxtDisplay(TextArea txtDisplay) {
        this.txtDisplay = txtDisplay;
    }
    void displayText(String text) {
        txtDisplay.appendText(text+"\n");
    }

    void initText() {
        txtDisplay.setText("");
    }

    //연결 시작
    public void startClient(String id) {
        this.id = id;
        try {
            channelGroup = AsynchronousChannelGroup.withFixedThreadPool(Runtime.getRuntime().availableProcessors(), Executors.defaultThreadFactory());
            socketChannel = AsynchronousSocketChannel.open(channelGroup);
            socketChannel.connect(new InetSocketAddress("localhost", 50001), null, new CompletionHandler<Void,Void>(){

                @Override
                public void completed(Void result, Void attachment) {
                    try {

                        String msg = "[연결 완료: " + socketChannel.getRemoteAddress() + "]";
                        System.out.println(msg);
                        System.out.println("받아온 ID: " + id);
                        sendId(id);

//


                        //btnConn.setText("stop");
                        //btnSend.setDisable(false);
                    } catch (Exception e) {

                        }
                    receive(); //서버에서 보낸 데이터 받기
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    //Platform.runLater(()->displayText("[서버 통신 실패]"));
                    if(socketChannel.isOpen()) {
                        stopClient();
                    }
                }

            });
        }catch(Exception e) {}
    }

    //연결 종료
    void stopClient() {
//        Platform.runLater(()->{
//            displayText("[연결 종료]");
//            btnConn.setText("start");
//            btnSend.setDisable(true);
//        });
        if(channelGroup!=null && !channelGroup.isShutdown()) {
            channelGroup.shutdown();
        }
    }

    //서버로부터 데이터 받기
   public void receive() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>(){

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                try {
                    attachment.flip();
                    Charset charset = Charset.forName("utf-8");
                    String data = charset.decode(attachment).toString();
                    //====================Json 시작
                    JSONParser jsonParser = new JSONParser();
                    JSONObject token = (JSONObject) jsonParser.parse(data);
                    String method = token.get("method").toString();
                    System.out.println(method);
                    Platform.runLater(()->displayText(data));
                    System.out.println(data);

                    switch (method) {
                        case "/room/roomList":
                            Platform.runLater(()->{
                                // init listView
                                listView.getItems().clear();

                                // append listView
                                JSONArray rooms = (JSONArray) token.get("rooms");
                                for (int i = 0; i < rooms.size(); i++) {
                                    JSONObject room = (JSONObject) rooms.get(i);
                                    System.out.println(room.toString());
                                    listView.getItems().add(
                                            new Room(
                                                    room.get("id").toString(),
                                                    room.get("roomName").toString(),
                                                    0/*Integer.parseInt(room.get("size").toString())*/)
                                    );
                                }
                            });
                            break;
                        case "/chat/echo":
                            Platform.runLater(()->{ displayText("[채팅클라이언트] " + token.get("id") + " :: " + token.get("message") ); });
                            break;
                    }

                    ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                    socketChannel.read(byteBuffer, byteBuffer,this); //데이터 다시 읽기
                }catch(Exception e) {

                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                //Platform.runLater(()->chatRoomCon.displayText("[서버 통신 실패]"));
                stopClient();
            }

        });
    }


    public void sendRoomList() {

        String data = String.format("{\"method\":\"%s\"}", "/room/roomList");
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){
            @Override
            public void completed(Integer result, Void attachment) {
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                stopClient();
            }

        });
    }
    public void sendId(String id) {

        String data = String.format("{\"method\":\"%s\",\"id\":\"%s\"}", "/login/id", id);
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){
            @Override
            public void completed(Integer result, Void attachment) {
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                stopClient();
            }

        });
    }

    public void sendCreate(String roomName) {

        String data = String.format("{\"method\":\"%s\",\"roomName\":\"%s\"}", "/room/create", roomName);
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){
            @Override
            public void completed(Integer result, Void attachment) {
                // ### print() ###
                printEntry(id, roomName);
            }


            @Override
            public void failed(Throwable exc, Void attachment) {
                stopClient();
            }

        });

    }

    public void sendEntry( Room room ) {
        Client.this.room = room;

        String data = String.format("{\"method\":\"%s\",\"id\":\"%s\"}", "/room/entry", room.id);
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){
            @Override
            public void completed(Integer result, Void attachment) {
                // ### print() ###
                printEntry(id, room.roomName);
            }
            @Override
            public void failed(Throwable exc, Void attachment) {
                stopClient();
            }

        });

    }

    public void sendLeave() {

        String data = String.format("{\"method\":\"%s\"}", "/room/leave");
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){
            @Override
            public void completed(Integer result, Void attachment) {
                // ### print() ###
                printLeave();
            }



            @Override
            public void failed(Throwable exc, Void attachment) {
                stopClient();
            }

        });

    }

    //서버로 데이터 전송
    public void sendChat(String message) {
        String data = String.format("{\"method\":\"%s\",\"id\":\"%s\",\"message\":\"%s\"}", "/chat/send", id, message);
        Charset charset = Charset.forName("utf-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>(){
            @Override
            public void completed(Integer result, Void attachment) {
                Platform.runLater(()->displayText(id + "님"));
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                Platform.runLater(()->displayText("[서버 통신 실패]"));
                stopClient();
            }

        });
    }


    void printEntry( String id, String roomName ) {
        Platform.runLater(()->{
            initText();
            displayText("\"" + roomName + "\"에 오신 것을 환영합니다. " + id + "님" );
            displayText("채팅방 이름 : " + roomName );
        });
    }

    void printLeave() {
        Platform.runLater(()->{
            initText();
            displayText("[채팅클라이언트] 채팅을 나갔습니다. 새로운 채팅방을 만들거나 찾아주세요." );
        });
    }



    // UI생성 코드

//    TextField txtInput;
//    Button btnConn, btnSend;
//
//
//    public void start(Stage primaryStage) throws Exception {
//        // Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        BorderPane root = new BorderPane();
//        root.setPrefSize(500, 300);
//
//        txtDisplay = new TextArea();
//        txtDisplay.setEditable(false);
//        BorderPane.setMargin(txtDisplay, new Insets(0,0,2,0));
//        root.setCenter(txtDisplay);
//
//        BorderPane bottom = new BorderPane();
//        txtInput = new TextField();
//        txtInput.setPrefSize(60, 30);
//        BorderPane.setMargin(txtInput, new Insets(0,1,1,1));
//
//        btnConn = new Button("start");
//        btnConn.setPrefSize(60, 30);
//        //start stop 버튼이벤트 처리
//        btnConn.setOnAction(e->{
//            if(btnConn.getText().equals("start")) {
//                startClient();
//            }else if(btnConn.getText().equals("stop")) {
//                stopClient();
//            }
//        });
//
//        btnSend = new Button("send");
//        btnSend.setPrefSize(60, 30);
//        btnSend.setDisable(true);
//        //send 버튼 이벤트 처리
//        btnSend.setOnAction(e->send(txtInput.getText()));
//
//        bottom.setCenter(txtInput);
//        bottom.setLeft(btnConn);
//        bottom.setRight(btnSend);
//        root.setBottom(bottom);
//
//        Scene scene = new Scene(root);
//        //       scene.getStylesheets().add(getClass().getResource("app.css").toString());
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Client");
//        primaryStage.setOnCloseRequest(event->stopClient());
//        primaryStage.show();
//    }
//



//    public TextArea getTxtDisplay() {
//        return txtDisplay;
//    }




}