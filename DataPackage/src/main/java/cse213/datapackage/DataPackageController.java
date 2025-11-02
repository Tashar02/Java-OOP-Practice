package cse213.datapackage;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataPackageController
{
    @javafx.fxml.FXML
    private ComboBox<String> availabilityComboBox;
    @javafx.fxml.FXML
    private DatePicker offerEndsDateDatePicker;
    @javafx.fxml.FXML
    private TextField packageNameTextField;
    @javafx.fxml.FXML
    private TableColumn<DataPackage, String> availabilityTableColumn;
    @javafx.fxml.FXML
    private TableView<DataPackage> packageListTableView;
    @javafx.fxml.FXML
    private TableColumn<DataPackage, String> packageNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<DataPackage, String> validityTableColumn;
    @javafx.fxml.FXML
    private TableColumn<DataPackage, LocalDate> offerEndsDateTableColumn;
    @javafx.fxml.FXML
    private TextField dataAmountTextField;
    @javafx.fxml.FXML
    private TableColumn<DataPackage, Integer> priceTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> validityComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> filterValidityComboBox;
    @javafx.fxml.FXML
    private TextField priceTextField;
    @javafx.fxml.FXML
    private Label createPackageOutputLabel;
    @javafx.fxml.FXML
    private TextField filterPriceTextField;
    @javafx.fxml.FXML
    private TableColumn<DataPackage, Integer> dataAmountTableColumn;
    @javafx.fxml.FXML
    private Label findBestValuePackageOutputLabel;
    private final ArrayList<DataPackage> packageList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        validityComboBox.getItems().addAll("3 days", "7 days", "15 days", "30 days", "Unlimited");
        filterValidityComboBox.getItems().addAll("3 days", "7 days", "15 days", "30 days", "Unlimited");
        availabilityComboBox.getItems().addAll("App only", "Recharge only", "App and recharge");

        packageNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        dataAmountTableColumn.setCellValueFactory(new PropertyValueFactory<>("dataAmount"));
        validityTableColumn.setCellValueFactory(new PropertyValueFactory<>("validity"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        availabilityTableColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
        offerEndsDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("offerEndsDate"));
    }

    public void errorAlert(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(s);
        a.showAndWait();
    }

    @javafx.fxml.FXML
    public void createPackageOA(ActionEvent actionEvent) {
        if (packageNameTextField.getText().isBlank() || dataAmountTextField.getText().isBlank() ||
                (validityComboBox.getValue() == null) || priceTextField.getText().isBlank() ||
                (availabilityComboBox.getValue() == null) || (offerEndsDateDatePicker.getValue() == null)) {
            errorAlert("Input fields are not filled up");
            return;
        }


        for (DataPackage pack: packageList) {
            if (pack.getPackageName().equals(packageNameTextField.getText())) {
                errorAlert("Duplicate package found");
                return;
            }
        }

        //String packageName, String validity, String availability, int dataAmount,
        // int price, LocalDate offerEndsDate
        DataPackage dp = new DataPackage(
                packageNameTextField.getText(),
                validityComboBox.getValue(),
                availabilityComboBox.getValue(),
                Integer.parseInt(dataAmountTextField.getText()),
                Integer.parseInt(priceTextField.getText()),
                offerEndsDateDatePicker.getValue()
        );

        packageList.add(dp);
        packageListTableView.getItems().clear();
        packageListTableView.getItems().addAll(packageList);

        createPackageOutputLabel.setText("Successfully added to packageList");
    }

    @javafx.fxml.FXML
    public void filterOA(ActionEvent actionEvent) {
        packageListTableView.getItems().clear();

        for (DataPackage pack: packageList) {
            if (pack.getValidity().equals(filterValidityComboBox.getValue()) && pack.getPrice() <= Integer.parseInt(filterPriceTextField.getText())) {
                packageListTableView.getItems().add(pack);
            }
        }
    }

    @javafx.fxml.FXML
    public void resetFilterOA(ActionEvent actionEvent) {
        packageListTableView.getItems().clear();
        packageListTableView.getItems().addAll(packageList);
    }

    @javafx.fxml.FXML
    public void findBestValuePackageOA(ActionEvent actionEvent) {
        float lowestPrice = Float.MAX_VALUE;
        DataPackage bestPackage = packageList.getFirst();


        for (int i = 0; i <= packageList.size() - 1; i++) {
            DataPackage pack = packageList.get(i);

            if ((float) pack.getPrice() / pack.getDataAmount() < lowestPrice) {
                lowestPrice = (float) pack.getPrice() / pack.getDataAmount();
                bestPackage = pack;
            }
        }

        findBestValuePackageOutputLabel.setText("The best value package is " + bestPackage.getPackageName());
    }
}