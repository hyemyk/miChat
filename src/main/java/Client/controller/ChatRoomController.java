package Client.controller;

import Client.DTO.ChatRoomDTO;
import Client.DTO.UserDTO;
import Client.service.ChatRoomService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatRoomController implements Initializable {

    @FXML
    private TextField showName;
    @FXML
    private TextField showNo;
    @FXML
    private TextArea showContent;
    @FXML
    private TextArea sendText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setChatRoomForm(Parent chatRoomForm) {
    }

    /*
inviteeList 나를 지우고, roomList(내가 속한 방들)에서 룸 지우기
 */
    public void exitRoom(ChatRoomDTO room) {

        ChatRoomService chatRoomService = new ChatRoomService();
        chatRoomService.exitRoom(room);
    }

    public void setShowRoomInfo(String roomName, ArrayList<String> inviteeList) {
        showName.setText(roomName);
        showNo.setText(String.valueOf(inviteeList.size()));

    }
}
