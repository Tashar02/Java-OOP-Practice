package cse213.passport;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;

public class DataCreationToFileSec8Controller
{
    @javafx.fxml.FXML
    private DatePicker dateOfIssueDatePicker;
    @javafx.fxml.FXML
    private DatePicker dateOfExpireDatePicker;
    @javafx.fxml.FXML
    private TextField enterPreviousPassportNoTextField;
    @javafx.fxml.FXML
    private TextField enterNidTextField;
    @javafx.fxml.FXML
    private ComboBox<String> selectZipCodeComboBox;
    @javafx.fxml.FXML
    private CheckBox hasPreviousPassportCheckBox;
    @javafx.fxml.FXML
    private TextField enterNameTextField;
    @javafx.fxml.FXML
    private ComboBox<String> selectPassportTypeComboBox;
    @javafx.fxml.FXML
    private TextField enterNewPassportNoTextField;

    @javafx.fxml.FXML
    public void initialize() {
        selectZipCodeComboBox.getItems().addAll("0001", "0002", "0003");
        selectPassportTypeComboBox.getItems().addAll("Type1", "Type2", "Type3");
    }

    public void writeToPassport(Passport passport) throws IOException {
        File f;
        FileOutputStream fos;
        ObjectOutputStream oos;

        f = new File("Passport.bin");
        if (f.exists()) {
            fos = new FileOutputStream(f, true);
            oos = new AppendableObjectOutputStream(fos);
        } else {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
        }

        oos.writeObject(passport);
    }

    public void errorAlert(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error!");
        a.setContentText(s);
        a.showAndWait();
    }

    @javafx.fxml.FXML
    public void validateAndAppendNewPassportInstanceToPassportFileButtonOA(ActionEvent actionEvent) throws IOException {
        /* String ppNo, String nId, String name, String zipCode, String prevPassportNo, String passportType,
        LocalDate dateOfIssue, LocalDate dateOfExpire, boolean hasPreviousPassport*/
        if (dateOfIssueDatePicker.getValue().isAfter(dateOfExpireDatePicker.getValue())) {
            errorAlert("Date of issue can't be after date of expire");
            return;
        }

        if (!hasPreviousPassportCheckBox.isSelected()) {
            if (!enterPreviousPassportNoTextField.getText().isBlank()) {
                errorAlert("If you don't have a previous passport, you can't have a passport number");
            }
        }

        Passport passport = new Passport(
                enterNewPassportNoTextField.getText(),
                enterNidTextField.getText(),
                enterNameTextField.getText(),
                selectZipCodeComboBox.getValue(),
                enterPreviousPassportNoTextField.getText(),
                selectPassportTypeComboBox.getValue(),
                dateOfIssueDatePicker.getValue(),
                dateOfExpireDatePicker.getValue(),
                hasPreviousPassportCheckBox.isSelected());

        writeToPassport(passport);
    }

    @javafx.fxml.FXML
    public void goToDataLoadingSceneButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DataProcessingAndFilterOutputViewSec8.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Data Processing and Filter Output");
        stage.setScene(scene);
        stage.show();
    }
}