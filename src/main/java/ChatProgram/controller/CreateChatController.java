package ChatProgram.controller;

import ChatProgram.ChatRoomPkg.ChatRoom;
import ChatProgram.ChatRoomPkg.User;
import ChatProgram.main.WindowOpenManager;
import ChatProgram.service.UICommonService;
import ChatProgram.service.CreateChatService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateChatController implements Initializable {

    @FXML
    private TextField rName;
    @FXML
    private TextField iName;
    @FXML
    private TextArea iList;

    private WindowOpenManager windowOpenManager;
    private Parent createChatForm;
    private User loginUserInfo;

    String makeList = "";

    CreateChatService createChatService = new CreateChatService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setLoginUserInfo(User loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    ArrayList<String> inviteeList = new ArrayList<>();

    public void invite() {

        String invitee = iName.getText();

        Boolean result = createChatService.userCheck(loginUserInfo.getId(), invitee);
        Boolean listCheck = inviteeList.contains(invitee);


        System.out.println(result);
        System.out.println(listCheck);

        if (result && !listCheck) {
            inviteeList.add(invitee);
            showList();
            iList.setText(makeList);
        }
    }

    public String showList() {
        String invitee = iName.getText();
        makeList = makeList + invitee + "\n" ;
        return makeList ;

    }

    public void create() {
        // 내 자신을 inviteeList에 먼저 추가
        inviteeList.add(loginUserInfo.getId());

        String roomName = rName.getText();
        String listBox = iList.getText();

        //입력값 유무 체크
        Boolean result = createChatService.createCheck(roomName, listBox);

        if (result) {
            ChatRoom createdRoom = createChatService.createRoom(loginUserInfo, roomName, inviteeList);

            /*ChatRoom chatRoom = new ChatRoom();
            chatRoom.setInviteeList(inviteeList);
            chatRoom.setRoomName(roomName);*/

            //테스트 위해 일단 비활성화
            //windowOpenManager.chatRoomOpen(roomName, inviteeList);

            //클라이언트 소켓 담은 loginUserInfo 오프너에 보내주기
            windowOpenManager.chatRoomOpen(loginUserInfo, createdRoom);
            UICommonService.windowClose(createChatForm);


        } else {
            UICommonService.msg("다시 입력하여 주세요.");
        }
    }

    public void cancel() {
        UICommonService.windowClose(createChatForm);
    }

    public void setCreateChatForm(Parent createChatForm) {
        this.createChatForm = createChatForm;
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }



}
