package Final.Controller;

import Final.Client.Client;
import Final.Client.Room;
import Final.View.WindowOpenManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ListView<Room> listView;

    private WindowOpenManager windowOpenManager;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // client.requestRoomList();
        // listView.setItems(FXCollections.observableArrayList());
        listView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Room>() {
                    @Override
                    public void changed(ObservableValue<? extends Room> observable, Room oldValue, Room newValue) {
                        //System.out.println("newValue.roomName : " + newValue.roomName);
                        if(newValue != null){
                            client.sendEntry(newValue);
                            windowOpenManager.chatRoomOpen(newValue);
                        }
                    }
                }
        );
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }

    public void setMainForm(Parent mainForm) {
    }

    public void setLoginUser(String userId) {
    }

    public void createChatOpen() {
        System.out.println("윈도우오픈매니저: " + windowOpenManager);
        windowOpenManager.createRoomOpen();

    }

    public void refreshList() {
        client.requestRoomList();


    }

    public void setClient(Client client) {
        this.client = client;
        client.setListView(listView);
    }




}
