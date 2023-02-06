package Final;
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

public class Server extends Application{
    //비동기 채널 그룹
    AsynchronousChannelGroup channelGroup;
    //클라이언트 연결 수락
    AsynchronousServerSocketChannel serverSocketChannel;
    List<Client> connections = new Vector<Client>();

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

                Client client = new Client(socketChannel);
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
            AsynchronousSocketChannel socketChannel;

        public Client(AsynchronousSocketChannel socketChannel) {
                this.socketChannel = socketChannel;
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
                        System.out.println("ServerId" + data);

                        for(Client  client : connections) {
                            client.send(data); //모든 클라이언트에게 보내기
                        }

                        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                        socketChannel.read(byteBuffer, byteBuffer, this); //데이터 다시 읽기
                    }catch(Exception e) {

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
