package ChatProgram.controller;

import ChatProgram.ChatRoomPkg.ChatRoom;
import ChatProgram.ChatRoomPkg.User;
import ChatProgram.main.Client;
import ChatProgram.service.ChatRoomService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private TextArea text;

    private User loginUserInfo;
    private ChatRoom chatRoom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setChatRoomForm(Parent chatRoomForm) {
    }

    /*
inviteeList 나를 지우고, roomList(내가 속한 방들)에서 룸 지우기
 */

    //텍스트 입력하고 보내기 버튼 클릭
    public void send(){
        String sendText = text.getText();

        //서버에 소켓과 같이 텍스트 보내기
        Client.send(loginUserInfo.getSocket(), sendText, chatRoom);
        //System.out.println("chatRoom.getChatContent() : " + chatRoom.getChatContent());
        //showContent.setText();
    }
    public void exitRoom(ChatRoom room) {

        ChatRoomService chatRoomService = new ChatRoomService();
        chatRoomService.exitRoom(room);
    }

    public void setShowRoomInfo(String roomName, ArrayList<String> inviteeList) {
        showName.setText(roomName);
        showNo.setText(String.valueOf(inviteeList.size()));

    }
    public void setShowContent(ChatRoom content) {
        showContent.setText(content.getChatContent());
    }

    public void setSocket(User loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    public void setChatRoomInfo(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
