module cse213.datapackage {
    requires javafx.controls;
    requires javafx.fxml;


    opens cse213.datapackage to javafx.fxml;
    exports cse213.datapackage;
}