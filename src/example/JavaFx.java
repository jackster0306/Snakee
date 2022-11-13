package example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;

public class JavaFx extends Application {
    public Color scorecolour = Color.MAGENTA;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Snakee");
        TilePane tp = new TilePane();
        Button start = new Button("Start");
        EventHandler<ActionEvent> startevent = e -> {
            stage.hide();
            Play.main(new String[]{}, scorecolour);
        };
        start.setOnAction(startevent);
        start.setLayoutX(250);
        start.setLayoutX(600);
        tp.getChildren().add(start);

        Stage colstage = new Stage();
        TilePane coltp = new TilePane();
        colstage.setTitle("Change Snake Colour");
        Button red = new Button("Red");
        EventHandler<ActionEvent> redevent = e -> setcolour(Color.RED);
        red.setOnAction(redevent);

        Button yellow = new Button("Yellow");
        EventHandler<ActionEvent> yelevent = e -> setcolour(Color.YELLOW);
        yellow.setOnAction(yelevent);

        Button back = new Button("Back");
        EventHandler<ActionEvent> backevent = e -> {
            colstage.hide();
            stage.show();
        };
        back.setOnAction(backevent);

        coltp.getChildren().add(red);
        coltp.getChildren().add(yellow);
        coltp.getChildren().add(back);

        Button col = new Button("Change Colour");
        EventHandler<ActionEvent> colevent = e -> {
            stage.hide();
            Scene sc = new Scene(coltp, 500, 800);
            colstage.setScene(sc);
            colstage.show();
        };
        col.setOnAction(colevent);
        tp.getChildren().add(col);
        Scene sc = new Scene(tp, 500, 800);
        stage.setScene(sc);
        stage.show();
    }

    public void setcolour(Color col){
        scorecolour = col;
    }

}
