package cse213.midterm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MidtermView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Midterm!");
        stage.setScene(scene);
        stage.show();
    }
}
