//package Final.view;
//
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//
//public class tmp {
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
//    void displayText(String text) {
//        txtDisplay.appendText(text+"\n");
//    }
//}
