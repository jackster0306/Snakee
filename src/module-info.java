module CW.Snake {
    requires java.desktop;
    requires jlayer;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens example;
    opens Application;
    opens Resources.Music;
    opens Resources.Images;
    opens Resources.TextFiles;
    opens Application.Controllers;
    opens Application.Obstacles;
}