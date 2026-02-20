module cse213.creditcard {
    requires javafx.controls;
    requires javafx.fxml;


    opens cse213.creditcard to javafx.fxml;
    exports cse213.creditcard;
}