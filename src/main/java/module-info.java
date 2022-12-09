module com.siebers.pathdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.Snake to javafx.fxml;
    exports com.Snake;
    exports com.Snake.controller;
    opens com.Snake.controller to javafx.fxml;
    exports com.Snake.model;
    opens com.Snake.model to javafx.fxml;
}