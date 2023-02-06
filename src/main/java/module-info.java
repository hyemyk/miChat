module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    exports Final.Controller;
    opens Final.Controller to javafx.fxml;
    exports Final.View;
    opens Final.View to javafx.fxml;
    exports Final;
    opens Final to javafx.fxml;
}