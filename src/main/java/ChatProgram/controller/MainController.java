package ChatProgram.controller;

import ChatProgram.ChatRoomPkg.ChatRoom;
import ChatProgram.ChatRoomPkg.User;
import ChatProgram.main.WindowOpenManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public WindowOpenManager windowOpenManager;
    private User loginUserInfo;

    @FXML
    private TableView<ChatRoom> roomTable;//테이블 뷰 이름

    @FXML
    private TableColumn<ChatRoom, String> roomName;//방 이름
    @FXML
    private TableColumn<ChatRoom, String> inviteeList;//참가자

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void getMyRoomList(User loginUserInfo) {
        ArrayList<ChatRoom> roomList = loginUserInfo.getRoomList();
        System.out.println("loginUserInfo.getRoomList() : " + loginUserInfo.getRoomList());

        roomName.setCellValueFactory(new PropertyValueFactory<ChatRoom, String>("roomName"));
        inviteeList.setCellValueFactory(new PropertyValueFactory<ChatRoom, String>("inviteeList"));


        ObservableList<ChatRoom> list = FXCollections.observableArrayList(roomList);
        System.out.println("ObservableList<ChatRoom> list : " + list);
        roomTable.setItems(list);

    }

    public void refreshList() {
        getMyRoomList(loginUserInfo);
    }

    public void setLoginUser(User loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    public void setMainForm(Parent mainForm) {
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }

    public void createChatOpen() {
        System.out.println("윈도우오픈매니저: " + windowOpenManager);
        windowOpenManager.createChatOpen();

    }


}
