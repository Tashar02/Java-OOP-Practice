package cse213.midterm;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

public class MidtermController
{
    @javafx.fxml.FXML
    private RadioButton filteredSelectCourseTypeTheoryRadioButton;
    @javafx.fxml.FXML
    private ComboBox<Integer> filteredSelectSectionComboBox;
    @javafx.fxml.FXML
    private RadioButton filteredSelectCourseTypeLabRadioButton;
    @javafx.fxml.FXML
    private CheckBox hasScholarshipCheckBox;
    @javafx.fxml.FXML
    private ComboBox<Integer> selectSectionComboBox;
    @javafx.fxml.FXML
    private TextField scholarshipRateTextField;
    @javafx.fxml.FXML
    private TextField studentIdTextField;
    @javafx.fxml.FXML
    private ComboBox<String> selectCourseComboBox;
    @javafx.fxml.FXML
    private Label noOfCreditsLabel;
    private final ArrayList<RegisteredCourse> registeredCourseList = new ArrayList<>();
    private Student tempStud;
    @javafx.fxml.FXML
    private TextArea regInfoTextArea;
    @javafx.fxml.FXML
    private Label noOfSpecificCoursesWithSameSectionLabel;

    @javafx.fxml.FXML
    public void initialize() {
        selectCourseComboBox.getItems().addAll("CSE200", "CSE200L", "CSE203", "CSE203L", "CSE211", "CSE211L", "CSE213", "CSE213L", "CSE104", "CSE104L");

        for (int i = 1; i <= 30; i++) {
            selectSectionComboBox.getItems().add(i);
            filteredSelectSectionComboBox.getItems().add(i);
        }
    }

    public void errorAlert(String s) {
        Alert a = new Alert(Alert.AlertType.ERROR);

        a.setContentText(s);
        a.showAndWait();
    }

    @javafx.fxml.FXML
    public void selectCourseOA(ActionEvent actionEvent) {
        if (selectCourseComboBox.getValue().endsWith("L")) {
            noOfCreditsLabel.setText("1");
        } else {
            noOfCreditsLabel.setText("3");
        }
    }

    @javafx.fxml.FXML
    public void addARegisteredCourseButtonOnClick(ActionEvent actionEvent) {
        if (registeredCourseList.isEmpty()) {
            if (hasScholarshipCheckBox.isSelected()) {
                if (Integer.parseInt(scholarshipRateTextField.getText()) == 0) {
                    errorAlert("You cannot set scholarship rate 0 if you have a scholarship");
                    return;
                }
            }

        }

        tempStud = new Student(
                Integer.parseInt(studentIdTextField.getText()),
                hasScholarshipCheckBox.isSelected(),
                Integer.parseInt(scholarshipRateTextField.getText())
        );

        RegisteredCourse rc = new RegisteredCourse(
               selectCourseComboBox.getValue(),
               selectSectionComboBox.getValue()
        );

        registeredCourseList.add(rc);
    }

    @javafx.fxml.FXML
    public void proceedToRegisterAndShowRegInfoButtonOnClick(ActionEvent actionEvent) {
        StringBuilder sb = new StringBuilder();
        int totalCredits = 0;

        sb.append(String.format("Student ID: %d, Scholarship: %s, Tuition fee per credit: 6000 Tk\n",
                tempStud.getStudentId(),
                tempStud.isHasScholarship()
        ));

        sb.append("Registered courses:\n");

        for (RegisteredCourse rc: registeredCourseList) {
            int credit = rc.getCourseId().endsWith("L") ? 1 : 3;

            sb.append(String.format("Course: %s, Credit: %d, Sec: %d\n",
                    rc.getCourseId(),
                    credit,
                    rc.getSection()));
            totalCredits += credit;
        }

        if (tempStud.isHasScholarship()) {
            if (totalCredits < 12 || totalCredits > 18) {
                errorAlert("Total credits for a scholarship holder must be within 12 to 18");
                registeredCourseList.clear();
                return;
            }
        } else {
            if (totalCredits < 6 || totalCredits > 18) {
                errorAlert("Total credits for a non-scholarship holder must be within 6 to 18");
                registeredCourseList.clear();
                return;
            }
        }

        sb.append(String.format("%nCredits enrolled: %d, Total payable: %d",
                totalCredits, (totalCredits * 6000)));

        regInfoTextArea.setText(sb.toString());
    }

    @javafx.fxml.FXML
    public void showTotalNoOfCoursesButtonOnClick(ActionEvent actionEvent) {
        boolean theorySelected = filteredSelectCourseTypeTheoryRadioButton.isSelected();
        boolean labSelected = filteredSelectCourseTypeLabRadioButton.isSelected();
        int section = filteredSelectSectionComboBox.getValue();
        int count = 0;

        for (RegisteredCourse rc: registeredCourseList) {
            boolean isLab = rc.getCourseId().endsWith("L");

            if ((theorySelected && !isLab) || (labSelected && isLab) && rc.getSection() == section) {
                count++;
            }
        }

        noOfSpecificCoursesWithSameSectionLabel.setText(String.format("No of %s courses having section-%d is: %d",
                theorySelected ? "Theory" : "Lab",
                section,
                count));
    }
}