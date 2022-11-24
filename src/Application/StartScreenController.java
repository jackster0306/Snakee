package Application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javafx.util.Duration;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class StartScreenController {
    ObservableList<String> tablechoices = FXCollections.observableArrayList("No Bombs Easy", "No Bombs Medium", "No Bombs Hard","Bombs Easy","Bombs Medium","Bombs Hard");
    private static boolean bombs = false;

    public static boolean GetToBomb(){
        return bombs;
    }
    Image skyimg = new Image("example/UI-background.png");
    Image cartimg = new Image("example/UI-background2.png");

    static String background;

    public static String GetBackground(){
        return background;
    }

    static String scorecol = "magenta";

    static String snakecol = "green";

    public static String GetScoreCol(){
        return scorecol;
    }

    static int speed;

    static int level;

    public static int GetDiff(){
        return level;
    }

    private static String playername;

    public static String GetPlayerName(){
        return playername;
    }


    @FXML
    private ImageView bgimg;

    @FXML
    private Label scorelabel;

    @FXML
    private Label numspeed;

    @FXML
    private TextField speedtf;

    @FXML
    private Label snakelabel;

    @FXML
    private CheckBox checkbomb;

    @FXML
    private Label diff;

    @FXML
    private TableView leaderboard;

    @FXML
    private TableColumn NameCol;

    @FXML
    private TableColumn ScoreCol;

    @FXML
    private ChoiceBox selecttable;

    @FXML
    private TextField name;

    @FXML
    private Label namelbl;


    private static int chosen;

    public static int GetChosen(){
        return chosen;
    }

    public void initialize() throws IOException {
        chosen = 0;
        snakecol = "green";
        selecttable.setItems(tablechoices);
        level = 1;
        speed = 5;
        speedtf.setText("Change Speed (Enter to confirm)");
        scorecol = "magenta";
        selecttable.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            try {
                SetupLeaderboard(newval.intValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void SetRedSnake(){
        snakelabel.setStyle("-fx-text-fill: red;");
        snakecol = "red";
    }
    public void SetGreenSnake(){
        snakelabel.setStyle("-fx-text-fill: green;");
        snakecol = "green";
    }
    public void SetMagentaSnake(){
        snakelabel.setStyle("-fx-text-fill: magenta;");
        snakecol = "magenta";
    }
    public void SetYellowSnake(){
        snakelabel.setStyle("-fx-text-fill: yellow;");
        snakecol = "yellow";
    }

    public void SetEasy(){
        diff.setText("Easy");
        level = 1;
    }

    public void SetMedium(){
        diff.setText("Medium");
        level = 2;
        chosen += 1;
    }

    public void SetHard(){
        diff.setText("Hard");
        level = 3;
        chosen += 2;
    }

    public void SkyImage(){
        bgimg.setImage(skyimg);
        background = "sky";
    }

    public void CartImage(){
        bgimg.setImage(cartimg);
        background = "cart";
    }

    public void SetSpeed(){
        speed = Integer.parseInt(speedtf.getText());
        numspeed.setText(Integer.toString(speed));
    }

    public static int GetSpeed(){
        return speed;
    }
    public void SetRed(){
        scorelabel.setStyle("-fx-text-fill: red;");
        scorecol = "red";
    }
    public void SetGreen(){
        scorelabel.setStyle("-fx-text-fill: green;");
        scorecol = "green";
    }
    public void SetMagenta(){
        scorelabel.setStyle("-fx-text-fill: magenta;");
        scorecol = "magenta";
    }
    public void SetYellow(){
        scorelabel.setStyle("-fx-text-fill: yellow;");
        scorecol = "yellow";
    }



    @FXML
    private void PlayGame() throws IOException {
        if(playername == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("You must enter a name");
            a.show();
        }
        else{
            bombs = checkbomb.isSelected();
            if(bombs)
                chosen += 3;
            if(level == 1){
                StartScreenJFX.setRoot("PlayScreen");
            } else if (level == 2) {
                StartScreenJFX.setRoot("PlayScreenMedium");
            } else if (level == 3) {
                StartScreenJFX.setRoot("PlayScreenHard");
            }
        }

    }

    public static ObservableList<Data> data;

    public static void AddData(Data d){
        data.add(d);
    }
    public void SetupLeaderboard(int num) throws IOException {
        data = GetData(num);
        NameCol.setCellValueFactory(new PropertyValueFactory<Data, String>("TheNames"));
        ScoreCol.setCellValueFactory(new PropertyValueFactory<Data, String>("TheScores"));
        leaderboard.setItems(data);
    }

    static File file;

    public static File getFile(){
        return file;
    }
    public ObservableList<Data> GetData(int num) throws IOException {
        file = new File("C:\\Users\\jackg\\OneDrive\\Documents\\University\\Computer Science\\Year 2\\COMP2013 - Developing Maintainable Software\\CW - Snake\\src\\Application\\SnakeeLeaderboard.xlsx");
        FileInputStream fs = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.getSheetAt(num);
        DataFormatter formatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        ObservableList<Data> names = FXCollections.observableArrayList();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            names.add(new Data(formatter.formatCellValue(row.getCell(0)),formatter.formatCellValue(row.getCell(1))));
        }
        wb.close();
        fs.close();
        return names;
    }

    public void SetName(){
        playername = name.getText();
        namelbl.setText(playername);
    }
}







