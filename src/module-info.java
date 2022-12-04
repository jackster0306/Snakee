module CW.Snake {
    requires java.desktop;
    requires jlayer;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens example;
    opens Application;
    opens Resources;
    opens Application.Controllers;
    opens Application.Obstacles;
}