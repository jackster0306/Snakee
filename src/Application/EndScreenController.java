package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EndScreenController {
    @FXML
    private Label endsclabel;
    public void initialize() throws IOException {
        endsclabel.setText((PlayScreenController.GetScore()));


        File file = new File("C:\\Users\\jackg\\OneDrive\\Documents\\University\\Computer Science\\Year 2\\COMP2013 - Developing Maintainable Software\\CW - Snake\\src\\Resources\\SnakeeLeaderboard.xlsx");

        FileInputStream inputStream = new FileInputStream(file);


        Workbook wb = WorkbookFactory.create(inputStream);

        Sheet sheet = wb.getSheetAt(StartScreenController.GetChosen());

        int rownum = sheet.getLastRowNum() + 1;

        Row row = sheet.createRow(rownum);

        Cell cell = row.createCell(0);

        cell.setCellValue(StartScreenController.GetPlayerName());

        Cell cell2 = row.createCell(1);
        cell2.setCellValue(Integer.parseInt(endsclabel.getText()));

        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);

        wb.close();
        out.close();

    }
    public void Restart() throws IOException {
        StartScreenJFX.setRoot("StartScreen");
    }

    public void Exit(){
        System.exit(0);
    }
}
