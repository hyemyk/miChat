package ChatProgram.controller;

import ChatProgram.ChatRoomPkg.User;
import ChatProgram.main.WindowOpenManager;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public WindowOpenManager windowOpenManager;
    private User loginUserInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLoginUser(User loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    public void setMainForm(Parent mainForm) {
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }

    public void createChatOpen() {
        System.out.println("윈도우오픈매니저: " + windowOpenManager);
        windowOpenManager.createChatOpen();

    }


}
