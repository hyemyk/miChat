package Final.View;

import Final.Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ChatApplication.class.getResource("login.fxml"));

        System.out.println("로그인화면 로더 loader: " + loader);

        Parent loginForm = loader.load();

        WindowOpenManager windowOpenManager = new WindowOpenManager();
        windowOpenManager.setStage(stage);

        LoginController loginCon = loader.getController();
        loginCon.setWindowOpenManager(windowOpenManager);

        Scene scene = new Scene(loginForm);
        System.out.println("첫 스테이지: " + stage);
        stage.setTitle("로그인 화면");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }

}

