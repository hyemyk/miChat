package Client.controller;

import Client.DTO.RoomManager;
import Client.DTO.UserDTO;
import Client.main.WindowOpenManager;
import Client.service.UICommonService;
import Client.service.CreateChatService;
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
    private UserDTO loginUserInfo;

    String makeList = "";

    CreateChatService createChatService = new CreateChatService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    public void setLoginUserInfo(UserDTO loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    ArrayList<String> inviteeList = new ArrayList<>();

    public void invite(){

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
        inviteeList.add(loginUserInfo.getId());

        String roomName = rName.getText();
        String listBox = iList.getText();

        Boolean result = createChatService.createCheck(roomName, listBox);


        if (result) {
            createChatService.createRoom(loginUserInfo, roomName, inviteeList);
            windowOpenManager.chatRoomOpen(roomName, inviteeList);
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
