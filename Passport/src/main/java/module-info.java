module cse213.passport {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens cse213.passport to javafx.fxml;
    exports cse213.passport;
}