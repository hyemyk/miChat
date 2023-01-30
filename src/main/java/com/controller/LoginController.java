package com.controller;

import com.main.WindowOpenManager;
import com.service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField id;

    @FXML
    private PasswordField pw;

    public WindowOpenManager windowOpenManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login() {
        String userId = id.getText();
        String password = pw.getText();
        String result = (new LoginService()).login(userId, password);

        if (result.equals("y")) {
            windowOpenManager.mainOpen(userId);
        }
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }


}
