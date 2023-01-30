package com.service;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CommonService {
    
    // alert창 띄우기
    public static void msg(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.show();
    }
    
    // 스테이지 닫기
    public static void windowClose(Parent form) {
        Stage stage = (Stage)form.getScene().getWindow();
        stage.close();
    }
}
