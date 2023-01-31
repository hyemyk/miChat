module main {
    requires javafx.controls;
    requires javafx.fxml;

    exports Client.controller;
    opens Client.controller to javafx.fxml;
    exports Client.main;
    opens Client.main to javafx.fxml;
}