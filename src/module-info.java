module CW.Snake {
    requires java.desktop;
    requires jlayer;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires javafx.media;
    opens example;
    opens Application;
    opens Resources;
}