package Final.Controller;

import Final.View.WindowOpenManager;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoomController implements Initializable {

    private WindowOpenManager windowOpenManager;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCreateChatForm(Parent createChatForm) {
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
    }

    public void create(String userId) {
        windowOpenManager.chatRoomOpen(userId);
    }
}
