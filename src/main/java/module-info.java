module main {
    requires javafx.controls;
    requires javafx.fxml;

    exports ChatProgram.Controller;
    opens ChatProgram.Controller to javafx.fxml;
    exports ChatProgram.main;
    opens ChatProgram.main to javafx.fxml;
    exports ChatProgram.ChatRoomPkg;
    opens ChatProgram.ChatRoomPkg to javafx.fxml;
}