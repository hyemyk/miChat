package com.controller;

import com.main.WindowOpenManager;
import com.service.CommonService;
import com.service.CreateChatService;
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

        }else {
            CommonService.msg("다시 입력하여 주세요.");
        }
    }

    public void cancel() {
        CommonService.windowClose(createChatForm);
    }

    public void setCreateChatForm(Parent createChatForm) {
        this.createChatForm = createChatForm;
    }
    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }

}
