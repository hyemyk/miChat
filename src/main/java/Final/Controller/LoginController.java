package Final.Controller;

import Final.Client;
import Final.View.WindowOpenManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField id;

    public WindowOpenManager windowOpenManager;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void login() {
        String userId = id.getText();
        new Client().startClient(userId);
        windowOpenManager.mainOpen(userId);

    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }
}
