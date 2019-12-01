import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //nazwa katalogu z resources
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Test1.fxml"));
        primaryStage.setTitle("Orbit");
        Scene scene = new Scene(root, 889, 599); //nowa odslona w oparciu o obiekt klasy parent
        primaryStage.setScene(scene);
        primaryStage.show();




    }
}
