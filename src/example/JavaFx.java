package example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;


import java.awt.*;

public class JavaFx extends Application {
    private static Image currimg = new Image("example/UI-background.png");
    public void SetCurrImg(Image img){
        currimg = img;
    }
    public static Image GetCurrImg(){
        return currimg;
    }
    private static int speed = 5;
    public void SetSpeed(int to){
        speed = to;
    }
    public static int GetSpeed(){
        return speed;
    }
    private static Color scorecolour = Color.MAGENTA;

    public void SetScoreColour(Color col){
        scorecolour = col;
    }
    public static Color GetScoreColour(){
        return scorecolour;
    }



    private static String background = "UI-Sky-background";

    public static String GetBackground(){
        return background;
    }

    public void SetBackground(String st){
        background = st;
    }
    long lastRefreshTime = 0;
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        //Setup of Main Stage
        stage.setTitle("Snakee");
        Pane tp = new Pane();
        tp.setId("SSPane");
        Button start = new Button("Start");
        start.setId("startbutton");
        EventHandler<ActionEvent> startevent = e -> {
            stage.hide();
            Play.main(new String[]{});
        };
        start.setOnAction(startevent);
        start.setLayoutY(250);
        start.setLayoutX(600);
        tp.getChildren().add(start);



        VBox colvb = new VBox();
        Label changescore = new Label("Score Colour:");
        colvb.getChildren().add(changescore);
        colvb.setLayoutX(5);
        colvb.setLayoutY(50);
        HBox scoretp = new HBox();
        scoretp.getStyleClass().add("HBox");

        Button red = new Button();
        red.setId("red");
        red.setPrefSize(30,15);
        EventHandler<ActionEvent> redevent = e -> SetScoreColour(Color.RED);
        red.setOnAction(redevent);

        Button yellow = new Button();
        yellow.setId("yellow");
        yellow.setPrefSize(30,15);
        EventHandler<ActionEvent> yelevent = e -> SetScoreColour(Color.YELLOW);
        yellow.setOnAction(yelevent);

        Button magenta = new Button();
        magenta.setId("magenta");
        magenta.setPrefSize(30,15);
        EventHandler<ActionEvent> magevent = e -> SetScoreColour(Color.MAGENTA);
        magenta.setOnAction(magevent);

        Button green = new Button();
        green.setId("green");
        green.setPrefSize(30,15);
        EventHandler<ActionEvent> greene = e -> SetScoreColour(Color.GREEN);
        green.setOnAction(greene);

        scoretp.getChildren().add(red);
        scoretp.getChildren().add(yellow);
        scoretp.getChildren().add(magenta);
        scoretp.getChildren().add(green);
        colvb.getChildren().add(scoretp);
        tp.getChildren().add(colvb);



        //Setup Stage for changing the background
        VBox bgvb = new VBox();
        bgvb.setLayoutX(440);
        bgvb.setLayoutY(5);;
        Label backstage = new Label(" Current Background:                 Select:");
        bgvb.getChildren().add(backstage);
        HBox bghb = new HBox();
        bghb.getStyleClass().add("HBox");
        ImageView currview = new ImageView(currimg);
        currview.setFitHeight(180);
        currview.setPreserveRatio(true);
        bghb.getChildren().add(currview);
        VBox buttons = new VBox();
        buttons.getStyleClass().add("Vbox");
        Button sky = new Button();
        Image skyimg = new Image("example/UI-background.png");
        ImageView skyview = new ImageView(skyimg);
        skyview.setFitHeight(80);
        skyview.setPreserveRatio(true);
        sky.setGraphic(skyview);
        EventHandler<ActionEvent> skyevent = e -> {
            SetBackground("UI-Sky-background");
            SetCurrImg(skyimg);
        };
        sky.setOnAction(skyevent);
        Button cartoonsky = new Button();
        Image cskyimg = new Image("example/UI-background2.png");
        ImageView cskyview = new ImageView(cskyimg);
        cskyview.setFitHeight(80);
        cskyview.setPreserveRatio(true);
        cartoonsky.setGraphic(cskyview);
        EventHandler<ActionEvent> cskyevent = e -> {
            SetBackground("UI-CartoonSky-background");
            SetCurrImg(cskyimg);
        };
        cartoonsky.setOnAction(cskyevent);
        buttons.getChildren().add(sky);
        buttons.getChildren().add(cartoonsky);
        bghb.getChildren().add(buttons);
        bgvb.getChildren().add(bghb);


        tp.getChildren().add(bgvb);





        VBox speedvb = new VBox();
        speedvb.getStyleClass().add("Vbox");
        speedvb.setLayoutX(5);
        speedvb.setLayoutY(400);
        Label speedl = new Label("Current Speed: "+ GetSpeed());
        TextField tf = new TextField("Change Speed (Enter to confirm)");
        tf.setPrefSize(200,15);
        EventHandler<ActionEvent> tfevent = e -> {
            SetSpeed(Integer.parseInt(tf.getText()));
        };
        tf.setOnAction(tfevent);

        speedvb.getChildren().add(speedl);
        speedvb.getChildren().add(tf);
        tp.getChildren().add(speedvb);


        Scene sc = new Scene(tp, 870, 560);
        sc.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        sc.addPreLayoutPulseListener(() -> {
            long refreshTime = System.nanoTime();
            lastRefreshTime = refreshTime;
            changescore.setStyle("-fx-text-fill: "+checkcol()+"; ");
            currview.setImage(currimg);
            speedl.setText("Current Speed: "+ GetSpeed());
        });
        stage.setScene(sc);
        stage.show();
       // stage.setScene(test);
        //stage.show();
    }

    public String checkcol(){
        if(scorecolour == Color.yellow){
            return "#ffff00";
        }
        else if(scorecolour == Color.red){
            return "#ff0000";
        }
        else if(scorecolour == Color.green){
            return "green";
        }
        else{
            return "#ff00ff";
        }
    }


}
