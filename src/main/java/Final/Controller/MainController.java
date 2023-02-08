package Final.Controller;

import Final.Client.Client;
import Final.View.WindowOpenManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

public class MainController {
    @FXML
    private ListView listView;

    private WindowOpenManager windowOpenManager;

    private Client client;

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
        client.sendRoomList();
    }

    public void setClient(Client client) {
        this.client = client;
        client.setListView(listView);
    }
}
