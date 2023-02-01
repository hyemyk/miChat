package Client.controller;

import Client.DTO.RoomManager;
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
    private String id;
    String makeList = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    ArrayList<String> inviteeList = new ArrayList<>();

    public void invite(){
        String invitee = iName.getText();
        Boolean result = (new CreateChatService()).userCheck(id, invitee);
        Boolean listCheck = inviteeList.contains(invitee);

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
        String roomName = rName.getText();
        String listBox = iList.getText();
        Boolean result = (new CreateChatService()).createCheck(roomName, listBox);


        if (result) {
            RoomManager roomManager = new RoomManager();
            roomManager.createRoom(roomName, inviteeList);

            windowOpenManager.chatRoomOpen();
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

    public void setLoginId(String id) {
        this.id = id;
    }
}
