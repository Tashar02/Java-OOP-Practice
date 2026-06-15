module cse213.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens cse213.demo to javafx.fxml;
    exports cse213.demo;
}