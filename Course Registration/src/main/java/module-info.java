module cse213.midterm {
    requires javafx.controls;
    requires javafx.fxml;


    opens cse213.midterm to javafx.fxml;
    exports cse213.midterm;
}