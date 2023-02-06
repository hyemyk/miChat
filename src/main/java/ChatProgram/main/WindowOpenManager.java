package ChatProgram.main;

import ChatProgram.ChatRoomPkg.ChatRoom;
import ChatProgram.ChatRoomPkg.User;
import ChatProgram.controller.ChatRoomController;
import ChatProgram.controller.CreateChatController;
import ChatProgram.controller.MainController;
import ChatProgram.service.UICommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WindowOpenManager {
    private Stage stage;
    private User loginUserInfo;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //로그인 후 메인화면으로 전환
    public void mainOpen(User loginUserInfo) {

        this.loginUserInfo = loginUserInfo;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

        try {
            System.out.println("메인화면 로더 : " + loader);
            Parent mainForm = loader.load();
            System.out.println("폼에 로드 : " + mainForm);

            MainController mainCon = loader.getController();
            mainCon.setMainForm(mainForm); //메인화면 fxml파일에 메인화면 컨트롤러 연결

            mainCon.setWindowOpenManager(this);//메인 화면 컨트롤러에 오프너 세팅
            this.setStage(stage);

            mainCon.setLoginUser(loginUserInfo);

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



    //메인 화면에서 채팅방 만들기 창 오픈
    public void createChatOpen() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createRoom.fxml"));

        try {
            System.out.println("createChat창 로더 : " + loader);
            Parent createChatForm = loader.load();
            System.out.println("createChat폼 : " + createChatForm);


            CreateChatController createChatCon = loader.getController();
            createChatCon.setCreateChatForm(createChatForm);
            createChatCon.setLoginUserInfo(loginUserInfo);


            createChatCon.setWindowOpenManager(this);
            this.setStage(stage);


            Stage createChatStage = new Stage();
            createChatStage.setTitle("채팅방 만들기");
            Scene scene = new Scene(createChatForm);
            createChatStage.setScene(scene);
            createChatStage.show();



        } catch (IOException e) {
            UICommonService.msg("채팅방 만들기 화면에 문제가 발생했습니다. 관리자에게 문의하세요.");
            e.printStackTrace();
        }
    }

    public void chatRoomOpen(User loginUserInfo, ChatRoom createdRoom) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chatRoom.fxml"));
        try {
            Parent ChatRoomForm = loader.load();

            ChatRoomController con = loader.getController();
            con.setChatRoomForm(ChatRoomForm);

            //룸 이름, 참가 인원 띄우기 위한 메소드에 룸정보 보내기
            con.setShowRoomInfo(createdRoom);

            //소켓 담은 loginUserInfo 보내주기
            con.setSocket(loginUserInfo);

            //우선 보류
            //con.setChatRoomInfo(createdRoom);

            Stage room = new Stage();
            Scene scene = new Scene(ChatRoomForm);
            room.setTitle("채팅방 화면");
            room.setScene(scene);
            room.show();
        } catch (Exception e) {
            UICommonService.msg(" 관리자에게 문의하세요.");
            e.printStackTrace();
        }
    }
}
