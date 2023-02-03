module main {
    requires javafx.controls;
    requires javafx.fxml;

    exports ChatProgram.controller;
    opens ChatProgram.controller to javafx.fxml;
    exports ChatProgram.main;
    opens ChatProgram.main to javafx.fxml;
    exports ChatProgram.ChatRoomPkg;
    opens ChatProgram.ChatRoomPkg to javafx.fxml;
}