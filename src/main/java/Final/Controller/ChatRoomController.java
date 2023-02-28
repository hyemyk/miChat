package Final.Controller;

import Final.Client.Client;
import Final.Client.Room;
import Final.Service.UICommonService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatRoomController implements Initializable {
    @FXML
    private TextField txtInput;
    @FXML
    private TextArea txtDisplay;

    @FXML
    private TextField showName;

    @FXML
    private TextField showNo;

    private Client client;

    private Room thisRoom;
    private Parent chatRoomForm;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if(!txtInput.getText().isEmpty()) {
                    send();
                    txtInput.clear();
                }
            }
        });
    }

    public void setChatRoomForm(Parent chatRoomForm) {
       this.chatRoomForm=chatRoomForm;
    }

    public void send() {
        if(!txtInput.getText().isEmpty()) {
            client.sendChat(txtInput.getText(), thisRoom);
            txtInput.clear();
        }

    }

    public void stopClient() {
        client.sendLeave(thisRoom);
        UICommonService.windowClose(chatRoomForm);
    }

    public void setClient(Client client) {
        this.client = client;
        client.setTxtDisplay(txtDisplay, thisRoom.roomName);
        //String strShowNo = showNo.getText();
        client.setShowNo(showNo, thisRoom.roomName);


    }

    public void setThisRoom(Room thisRoom) {
        this.thisRoom = thisRoom;
        System.out.println("thisRoom : " + thisRoom.roomName);
        showName.setText(thisRoom.roomName);
       // client.setShowNo(showNo,thisRoom.roomName);
        // showNo.setText();

    }


}
