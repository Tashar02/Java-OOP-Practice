package cse213.datapackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DataPackageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Data Package Selector");
        stage.setScene(scene);
        stage.show();
    }
}
