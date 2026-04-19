package cse213.passport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataProcessingAndFilterOutputSec8Controller {
    @FXML
    private TableColumn<Passport, String> ppNoTableColumn;
    @FXML
    private TableColumn<Passport, String> zipCodeTableColumn;
    @FXML
    private ComboBox<String> selectZipCodeToSearchComboBox;
    @FXML
    private TableColumn<Passport, String> nameTableColumn;
    @FXML
    private ComboBox<String> selectPassportTypeToSearchComboBox;
    @FXML
    private TableView<Passport> passportTableView;
    @FXML
    private TableColumn<Passport, String> ppTypeTableColumn;
    @FXML
    private TableColumn<Passport, LocalDate> dateOfIssueTableColumn;
    @FXML
    private TableColumn<Passport, LocalDate> dateOfExpireTableColumn;

    ArrayList<Passport> passportList = new ArrayList<>();

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        selectZipCodeToSearchComboBox.getItems().addAll("0001", "0002", "0003");
        selectPassportTypeToSearchComboBox.getItems().addAll("Type1", "Type2", "Type3");

        ppNoTableColumn.setCellValueFactory(new PropertyValueFactory<>("ppNo"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ppTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("passportType"));
        zipCodeTableColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        dateOfIssueTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfIssue"));
        dateOfExpireTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfExpire"));

        passportList = readPassport();
    }

    public ArrayList<Passport> readPassport() throws IOException {
        File f;
        FileInputStream fis;
        ObjectInputStream ois = null;
        Passport passport;
        ArrayList<Passport> passportList = new ArrayList<>();

        try {
            f = new File("Passport.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);

            try {
                while (true) {
                    passport = (Passport) ois.readObject();
                    passportList.add(passport);
                }
            } catch (Exception e) {
            }
        } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (Exception e) {
                }
        }
        return passportList;
    }

    @FXML
    public void zipCodeOA(ActionEvent actionEvent) {
        passportTableView.getItems().clear();
        for (Passport passport: passportList) {
            if (selectPassportTypeToSearchComboBox.getValue().equals(passport.getPassportType())
            && selectZipCodeToSearchComboBox.getValue().equals(passport.getZipCode())) {
                passportTableView.getItems().add(passport);
            }
        }
    }

    @FXML
    public void passportTypeOA(ActionEvent actionEvent) {
        passportTableView.getItems().clear();
        for (Passport passport: passportList) {
            if (selectPassportTypeToSearchComboBox.getValue().equals(passport.getPassportType())
                    && selectZipCodeToSearchComboBox.getValue().equals(passport.getZipCode())) {
                passportTableView.getItems().add(passport);
            }
        }
    }
}