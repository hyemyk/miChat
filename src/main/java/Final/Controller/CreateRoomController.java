package Final.Controller;

import Final.View.WindowOpenManager;
import Final.Service.UICommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoomController implements Initializable {

    @FXML
    private TextField rName;

    private WindowOpenManager windowOpenManager;
    private CreateRoomController createRoomForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCreateChatForm(Parent createChatForm) {
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
    }

    public void create() {}

    public void cancel() {}
}
