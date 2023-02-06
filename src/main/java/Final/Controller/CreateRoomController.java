package Final.Controller;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setCreateRoomForm(Parent createRoomForm) {
        this.createRoomForm = createRoomForm;
    }

    public void setWindowOpenManager(WindowOpenManager windowOpenManager) {
        this.windowOpenManager = windowOpenManager;
    }

    public void create() {
        windowOpenManager.chatRoomOpen();
    }

    public void cancel() {
        UICommonService.windowClose(createRoomForm);
    }
}
