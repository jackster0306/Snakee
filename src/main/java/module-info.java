module com.siebers.pathdemo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.siebers.pathdemo to javafx.fxml;
    exports com.siebers.pathdemo;
    exports com.siebers.pathdemo.controller;
    opens com.siebers.pathdemo.controller to javafx.fxml;
    exports com.siebers.pathdemo.model;
    opens com.siebers.pathdemo.model to javafx.fxml;
}