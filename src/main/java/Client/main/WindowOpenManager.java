package Client.main;

import Client.controller.ChatRoomController;
import Client.controller.CreateChatController;
import Client.controller.MainController;
import Client.service.UICommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowOpenManager {
    private Stage stage;
    private String id;

    Stage createChatStage = new Stage();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //로그인 후 메인화면으로 전환
    public void mainOpen(String id) {
        this.id = id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

        try {
            System.out.println("메인화면 로더 : " + loader);
            Parent mainForm = loader.load();
            System.out.println("폼에 로드 : " + mainForm);

            MainController mainCon = loader.getController();
            mainCon.setMainForm(mainForm); //메인화면 fxml파일에 메인화면 컨트롤러 연결

            mainCon.setWindowOpenManager(this);//메인 화면 컨트롤러에 오프너 세팅
            this.setStage(stage);

            mainCon.setLoginId(id);

            Scene scene = new Scene(mainForm);
            System.out.println(stage);
            stage.setTitle("메인 화면");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            UICommonService.msg("메인 화면에 문제가 발생했습니다. 관리자에게 문의하세요.");
            e.printStackTrace();
        }
    }



    //메인 화면에서 채팅방 생성 창 오픈
    public void createChatOpen() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createChat.fxml"));

        try {
            System.out.println("createChat창 로더 : " + loader);
            Parent createChatForm = loader.load();
            System.out.println("createChat폼 : " + createChatForm);


            CreateChatController createChatCon = loader.getController();
            createChatCon.setCreateChatForm(createChatForm);


            createChatCon.setWindowOpenManager(this);
            this.setStage(stage);


 //           Stage createChatStage = new Stage();
            createChatStage.setTitle("채팅방 만들기");
            Scene scene = new Scene(createChatForm);
            createChatStage.setScene(scene);
            createChatStage.show();

        } catch (IOException e) {
            UICommonService.msg("채팅방 만들기 화면에 문제가 발생했습니다. 관리자에게 문의하세요.");
            e.printStackTrace();
        }
    }

    public void chatRoomOpen() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chatRoom.fxml"));
        try {
            Parent ChatRoomForm = loader.load();

            ChatRoomController Con = loader.getController();
            Con.setChatRoomForm(ChatRoomForm);

            Scene scene = new Scene(ChatRoomForm);
            createChatStage.setTitle("채팅방 화면");
            createChatStage.setScene(scene);
            createChatStage.show();
        } catch (Exception e) {
            UICommonService.msg(" 관리자에게 문의하세요.");
            e.printStackTrace();
        }
    }


}
