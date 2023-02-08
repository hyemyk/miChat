package Final.Controller;

import Final.Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatRoomController implements Initializable {
    @FXML
    private TextArea txtInput;
    @FXML
    private TextArea txtDisplay;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setChatRoomForm(Parent chatRoomForm) {
    }

    public void send() {
        client.sendChat(txtInput.getText());

    }

    public void stopClient() {

    }

    public void setClient(Client client) {
        this.client = client;
        client.setTxtDisplay(txtDisplay);
    }
}
