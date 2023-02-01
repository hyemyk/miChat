package Client.controller;

import Client.DTO.UserDTO;
import Client.main.WindowOpenManager;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public WindowOpenManager windowOpenManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLoginUser(UserDTO loginUserInfo) {
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
