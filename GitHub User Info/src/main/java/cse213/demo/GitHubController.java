package cse213.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class GitHubController
{
    @javafx.fxml.FXML
    private TextField nameTextField;
    @javafx.fxml.FXML
    private TextField numberTextField;
    @javafx.fxml.FXML
    private TableColumn<GitHub, String> nameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<GitHub, String> locationTableColumn;
    @javafx.fxml.FXML
    private TableView<GitHub> githubTableView;
    @javafx.fxml.FXML
    private TableColumn<GitHub, Integer> numberTableColumn;
    @javafx.fxml.FXML
    private TableColumn<GitHub, String> departmentTableColumn;
    @javafx.fxml.FXML
    private TextField locationTextField;
    @javafx.fxml.FXML
    private ComboBox<String> departmentComboBox;

    ArrayList<GitHub> githubList = new ArrayList<>();
    @javafx.fxml.FXML
    private ComboBox<String> filterLocationComboBox;

    @javafx.fxml.FXML
    public void initialize() {
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberTableColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        departmentTableColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        departmentComboBox.getItems().addAll("CSE", "EEE", "BBA");

        filterLocationComboBox.getItems().addAll("Dhaka", "Chattogram", "Sylhet");
    }

    @javafx.fxml.FXML
    public void addToArrayListButtonOA(ActionEvent actionEvent) {
        /* String name, int number, String location, String department */
        if ((numberTextField.getText().length() != 11) || !numberTextField.getText().startsWith("0")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Number must be 11 digits and start with 0. Please redo!");
            a.showAndWait();
            return;
        }

        GitHub gh = new GitHub(
                nameTextField.getText(),
                Integer.parseInt(numberTextField.getText()),
                locationTextField.getText(),
                departmentComboBox.getValue()
        );

        githubList.add(gh);
    }

    @javafx.fxml.FXML
    public void loadTableButtonOA(ActionEvent actionEvent) {
        // githubList = [1, 2, 3]
        // for i in githubList:
        //     githubTableView.add(i)
        githubTableView.getItems().clear();
        for (GitHub i: githubList) {
            // See if "i" object's (GitHub) variable has the same data as the filtered data
            // Here our variable is "location" in GitHub class.
            if (i.getLocation().equals(filterLocationComboBox.getValue())) {
                githubTableView.getItems().add(i);
            }
        }
    }
}