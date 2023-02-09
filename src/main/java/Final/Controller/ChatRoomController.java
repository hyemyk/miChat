package Final.Controller;

import Final.Client.Client;
import Final.Client.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatRoomController implements Initializable {
    @FXML
    private TextArea txtInput;
    @FXML
    private TextArea txtDisplay;

    @FXML
    private TextField showName;

    @FXML
    private TextField showNo;

    private Client client;

    private Room thisRoom;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setChatRoomForm(Parent chatRoomForm) {

    }

    public void send() {
        client.sendChat(txtInput.getText(), thisRoom);

    }

    public void stopClient() {
        client.sendLeave(thisRoom);

    }

    public void setClient(Client client) {
        this.client = client;
        client.setTxtDisplay(txtDisplay, thisRoom.roomName);
    }

    public void setThisRoom(Room thisRoom) {
        this.thisRoom = thisRoom;
        System.out.println("thisRoom : " + thisRoom.roomName);
        showName.setText(thisRoom.roomName);
        showNo.setText(String.valueOf(thisRoom.size));
    }

}
