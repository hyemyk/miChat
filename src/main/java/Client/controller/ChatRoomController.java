package Client.controller;

import Client.DTO.ChatRoomDTO;
import Client.service.ChatRoomService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatRoomController implements Initializable {

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
}
