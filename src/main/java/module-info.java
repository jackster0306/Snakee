module com.Snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    opens com.Snake to javafx.fxml;
    exports com.Snake;
    exports com.Snake.Controllers;
    opens com.Snake.Controllers to javafx.fxml;
    exports com.Snake.Models;
    opens com.Snake.Models to javafx.fxml;
}