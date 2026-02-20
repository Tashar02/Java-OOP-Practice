package cse213.creditcard;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CreditCardController
{
    @javafx.fxml.FXML
    private TextField cardNoTextField;
    @javafx.fxml.FXML
    private TextField searchMinCreditLimitTextField;
    @javafx.fxml.FXML
    private ComboBox<String> cardTypeComboBox;
    @javafx.fxml.FXML
    private TextField creditLimitTextField;
    @javafx.fxml.FXML
    private ComboBox<String> gatewayNameComboBox;
    @javafx.fxml.FXML
    private DatePicker dateOfExpiryDatePicker;
    @javafx.fxml.FXML
    private TextField holderNameTextField;
    @javafx.fxml.FXML
    private Label showLabel;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, String> gatewayNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, Integer> creditLimitTableColumn;
    @javafx.fxml.FXML
    private TableView<CreditCard> creditCardTableView;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, String> cardNoTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CreditCard, String> holderNameTableColumn;
    @javafx.fxml.FXML
    private ComboBox<String> searchGatewayNameComboBox;
    private ArrayList<CreditCard> cardList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        gatewayNameComboBox.getItems().addAll("Visa", "MasterCard");
        searchGatewayNameComboBox.getItems().addAll("Visa", "MasterCard");
        cardTypeComboBox.getItems().addAll("silver", "gold", "platinum", "titanium");

        cardNoTableColumn.setCellValueFactory(new PropertyValueFactory<>("cardNo"));
        holderNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("holderName"));
        gatewayNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("gatewayName"));
        creditLimitTableColumn.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
    }

    @javafx.fxml.FXML
    public void showAverageCreditLimitFromLoadedTableViewButtonOnAction(ActionEvent actionEvent) {
        float sum = 0.0F;
        float avg;

        for (CreditCard cc: cardList) {
            sum += cc.getCreditLimit();
        }

        avg = sum / (float) cardList.size();

        showLabel.setText("The average credit limit from the loaded TableView is " + avg);
    }

    @javafx.fxml.FXML
    public void searchAndLoadTableButtonOnAction(ActionEvent actionEvent) {
        /* cardList = [obj1, obj2, obj3] */
        /* each obj -> String cardNo, String holderName, String gatewayName, String cardType,
        LocalDate dateOfExpiry, int creditLimit */
        /* Class name always begins with Capital letter and they use equals(). Exception: Integer, Boolean */
        creditCardTableView.getItems().clear();
        for (CreditCard cc: cardList) {
            if (searchGatewayNameComboBox.getValue().equals(cc.getGatewayName()) &&
                    cc.getCreditLimit() >= Integer.parseInt(searchMinCreditLimitTextField.getText())) {
                creditCardTableView.getItems().add(cc);
            }
        }
    }

    public void errorAlert(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);

        a.setContentText(s);
        a.showAndWait();
    }

    @javafx.fxml.FXML
    public void validateAndAddNewCardToArrayListButtonOnAction(ActionEvent actionEvent) {
        /* String cardNo, String holderName, String gatewayName, String cardType,
        LocalDate dateOfExpiry, int creditLimit */
        if (cardNoTextField.getLength() != 16) {
            errorAlert("Card no must be exactly 16 digit");
            return;
        }

        if ((gatewayNameComboBox.getValue().equals("Visa") && !cardNoTextField.getText().startsWith("4")) ||
                (gatewayNameComboBox.getValue().equals("MasterCard") && !cardNoTextField.getText().startsWith("5"))) {
            errorAlert("Invalid starting digit for gateway");
            return;
        }

        CreditCard cc = new CreditCard(
                cardNoTextField.getText(),
                holderNameTextField.getText(),
                gatewayNameComboBox.getValue(),
                cardTypeComboBox.getValue(),
                dateOfExpiryDatePicker.getValue(),
                Integer.parseInt(creditLimitTextField.getText()));

        cardList.add(cc);
    }
}