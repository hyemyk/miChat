package Final.Controller;

import Final.View.WindowOpenManager;
import javafx.scene.Parent;

public class MainController {
    private WindowOpenManager windowOpenManager;

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

    public void refreshList() {}
}
