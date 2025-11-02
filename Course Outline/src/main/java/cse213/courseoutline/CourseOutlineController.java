package cse213.courseoutline;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class CourseOutlineController
{
    @javafx.fxml.FXML
    private ComboBox<String> semesterComboBox;
    @javafx.fxml.FXML
    private TextField yearTextField;
    @javafx.fxml.FXML
    private ComboBox<String> preReq1ComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> preReq3ComboBox;
    @javafx.fxml.FXML
    private TextArea descriptionTextArea;
    @javafx.fxml.FXML
    private ComboBox<String> preReq2ComboBox;
    @javafx.fxml.FXML
    private TextField noOfCreditsTextField;
    @javafx.fxml.FXML
    private Label processLabel;
    @javafx.fxml.FXML
    private TextField courseTitleTextField;
    @javafx.fxml.FXML
    private TextField courseIdTextField;
    @javafx.fxml.FXML
    private TableView<CourseOutline> courseTableView;
    @javafx.fxml.FXML
    private TableColumn<CourseOutline, String> courseTitleTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CourseOutline, String> semesterTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CourseOutline, String> courseIdTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CourseOutline, Integer> noOfCreditsTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CourseOutline, Integer> yearTableColumn;
    private final ArrayList<CourseOutline> outlineList = new ArrayList<>();
    @javafx.fxml.FXML
    private ComboBox<String> filteredsemesterComboBox;
    @javafx.fxml.FXML
    private TextField filteredNoOfCreditsTextField;

    @javafx.fxml.FXML
    public void initialize() {
        preReq1ComboBox.getItems().addAll("None", "CSC101", "CSE203", "CSE211", "CSE213");
        preReq2ComboBox.getItems().addAll("None", "CSC101", "CSE203", "CSE211", "CSE213");
        preReq3ComboBox.getItems().addAll("None", "CSC101", "CSE203", "CSE211", "CSE213");
        semesterComboBox.getItems().addAll("Spring", "Summer", "Autumn");
        filteredsemesterComboBox.getItems().addAll("Spring", "Summer", "Autumn");

        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        noOfCreditsTableColumn.setCellValueFactory(new PropertyValueFactory<>("noOfCredits"));
        yearTableColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        semesterTableColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
    }

    @javafx.fxml.FXML
    public void handleFilterAndLoadTableOnAction(ActionEvent actionEvent) {
        courseTableView.getItems().clear();
        for (CourseOutline co: outlineList) {
            if (Integer.parseInt(filteredNoOfCreditsTextField.getText()) == co.getNoOfCredits() && filteredsemesterComboBox.getValue() == co.getSemester())
                courseTableView.getItems().add(co);
        }
    }
    
    @javafx.fxml.FXML
    public void handleShowsemesterwisecourseoutlinecountOnAction(ActionEvent actionEvent) {
        processLabel.setText(Integer.toString(courseTableView.getItems().size()));
    }

    @javafx.fxml.FXML
    public void handleValidateAndAddNewCourseOutlineToArrayListOnAction(ActionEvent actionEvent) {
        if (!preReq3ComboBox.getValue().equals("None")) {
            if (preReq1ComboBox.getValue().equals("None") || preReq2ComboBox.getValue().equals("None")) {
                errorAlert("Prerequisite 2 and 3 cannot be None");
                return;
            }
        }

        if (preReq1ComboBox.getValue().equals("None")) {
            if (!preReq2ComboBox.getValue().equals("None") || !preReq3ComboBox.getValue().equals("None")) {
                errorAlert("Prerequisite 2 and 3 MUST be None");
                return;
            }
        }

        if (Integer.parseInt(yearTextField.getText()) > LocalDate.now().getYear()) {
            errorAlert("Year cannot be in future");
            return;
        }

        CourseOutline co = new CourseOutline(
                courseIdTextField.getText(),
                courseTitleTextField.getText(),
                preReq1ComboBox.getValue(),
                preReq2ComboBox.getValue(),
                preReq3ComboBox.getValue(),
                semesterComboBox.getValue(),
                descriptionTextArea.getText(),
                Integer.parseInt(noOfCreditsTextField.getText()),
                Integer.parseInt(yearTextField.getText())
        );

        outlineList.add(co);
    }

    public void errorAlert(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(s);
        a.showAndWait();
    }

}