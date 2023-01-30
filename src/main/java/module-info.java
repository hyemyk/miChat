module main {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.controller;
    opens com.controller to javafx.fxml;
    exports com.main;
    opens com.main to javafx.fxml;
}