module cse213.courseoutline {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens cse213.courseoutline to javafx.fxml;
    exports cse213.courseoutline;
}