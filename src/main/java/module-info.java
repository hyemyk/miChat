module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    exports Final.Controller;
    opens Final.Controller to javafx.fxml;
    exports Final.View;
    opens Final.View to javafx.fxml;
    exports Final.Server;
    opens Final.Server to javafx.fxml;
    exports Final.Client;
    opens Final.Client to javafx.fxml;
}