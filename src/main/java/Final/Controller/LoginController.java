package Final.Controller;

import Final.Client.Client;
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

    Client client = new Client();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void login() {

        String userId = id.getText();
        client.startClient(userId);
        System.out.println("ControllorId : " + userId);
        windowOpenManager.mainOpen(userId,client);

    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }

/*    public void setClient(Client client) {
        this.client = client;
    }*/


}
