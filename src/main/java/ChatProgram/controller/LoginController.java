package ChatProgram.controller;

import ChatProgram.ChatRoomPkg.User;
import ChatProgram.main.Client;
import ChatProgram.main.WindowOpenManager;
import ChatProgram.service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField id;

    @FXML
    private PasswordField pw;

    public WindowOpenManager windowOpenManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login() {
        String userId = id.getText();
        String password = pw.getText();
       //User loginUserInfo = new User(userId);

        Boolean result = (new LoginService()).login(userId, password);

        if (result) {
            User loginUserInfo = Client.socketConnect();
            loginUserInfo.setId(userId);
            loginUserInfo.setPw(password);
            windowOpenManager.mainOpen(loginUserInfo);

        }
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }


}
