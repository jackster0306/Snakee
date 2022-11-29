package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class LeaderboardController {

    ObservableList<String> tablechoices = FXCollections.observableArrayList("No Bombs Easy", "No Bombs Medium", "No Bombs Hard","Bombs Easy","Bombs Medium","Bombs Hard");

    @FXML
    private TableView leaderboard;

    @FXML
    private TableColumn NameCol;

    @FXML
    private TableColumn ScoreCol;

    @FXML
    private ChoiceBox selecttable;

    public void initialize(){
        selecttable.setItems(tablechoices);
        selecttable.getSelectionModel().selectedIndexProperty().addListener((ov, old, newval) -> {
            try {
                SetupLeaderboard(newval.intValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            selecttable.setValue("No Bombs Easy");
            SetupLeaderboard(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void SetupLeaderboard(int num) throws IOException {
        ObservableList<Data> data = GetData(num);
        NameCol.setCellValueFactory(new PropertyValueFactory<Data, String>("TheNames"));
        ScoreCol.setCellValueFactory(new PropertyValueFactory<Data, String>("TheScores"));
        leaderboard.setItems(data);
    }
    public ObservableList<Data> GetData(int num) throws IOException {
        File file = new File("C:\\Users\\jackg\\OneDrive\\Documents\\University\\Computer Science\\Year 2\\COMP2013 - Developing Maintainable Software\\CW - Snake\\src\\Resources\\SnakeeLeaderboard.xlsx");
        FileInputStream fs = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.getSheetAt(num);
        DataFormatter df = new DataFormatter();
        Iterator<Row> iterator = sheet.iterator();
        iterator.next();
        ObservableList<Data> names = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            names.add(new Data(df.formatCellValue(row.getCell(0)),df.formatCellValue(row.getCell(1))));
        }
        wb.close();
        fs.close();
        return names;
    }

    public void ToStartScreen() throws IOException {
        StartScreenJFX.setRoot("StartScreen");
    }
}
