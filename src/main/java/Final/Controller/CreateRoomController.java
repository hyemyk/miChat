package Final.Controller;

import Final.Client.Client;
import Final.Client.Room;
import Final.Service.UICommonService;
import Final.View.WindowOpenManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoomController implements Initializable {

    @FXML
    private TextField rName;

    private Parent createRoomForm;
    private WindowOpenManager windowOpenManager;
    private Client client;
    private String userId;
    private Room room;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCreateRoomForm(Parent createRoomForm) {
        this.createRoomForm = createRoomForm;
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }

    public void setClientInfo(Client client, String userId) {
        this.client = client;
        this.userId = userId;
    }
    public void create() {
        String roomName = rName.getText();
        client.sendCreate(roomName);
        windowOpenManager.chatRoomOpen();
    }

    public void cancel()  {
        UICommonService.windowClose(createRoomForm);
    }
}
