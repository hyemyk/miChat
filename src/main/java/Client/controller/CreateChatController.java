package Client.controller;

import Client.main.WindowOpenManager;
import Client.service.UICommonService;
import Client.service.CreateChatService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateChatController implements Initializable {

    @FXML private TextField roomName;
    @FXML private TextField participant1;
    private WindowOpenManager windowOpenManager;
    private Parent createChatForm;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void invitation() {
        String rName = roomName.getText();
        String participant = participant1.getText();
        Boolean result = (new CreateChatService()).check(rName, participant);
        System.out.println(windowOpenManager);
        if (result) {
            windowOpenManager.chatRoomOpen();
            UICommonService.windowClose(createChatForm);
        }else {
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
