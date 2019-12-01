import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.stream.IntStream;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    double a;
    double eccentricity;
    double T;
    double desiredError;
    String planet;
    String method;
    int indexMethod;
    int indexPlanet;

    RegulaPunktuStalego regulaPunktuStalego = new RegulaPunktuStalego(new LiczFunkcje());
    MetodaStycznych metodaStycznych = new MetodaStycznych((new LiczFunkcje()));
    MetodaSiecznych metodaSiecznych = new MetodaSiecznych((new LiczFunkcje()));
    Bisekcja bisekcja = new Bisekcja((new LiczFunkcje()));

    ArrayList xPunkty = new ArrayList();
    ArrayList yPunkty = new ArrayList();

    String[] panetList = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "My Planet"};
    double[] eccentricityList = {0.2056, 0.0068, 0.0167, 0.0934, 0.0484, 0.0542, 0.0472, 0.0086, 0.2488};
    double[] TList = {88, 225, 365, 687, 12 * 365, 29 * 365, 84 * 365, 165 * 365, 248 * 365};
    double[] aList = {0.387, 0.723, 1.000, 1.524, 5.203, 9.537, 19.191, 30.069, 39.482};
    //Object [] methodsList={bisekcja, regulaPunktuStalego, metodaSiecznych,metodaStycznych};


    ObservableList choiceBoxMethodList = FXCollections.observableArrayList("Bisection", "Iterative", "Tangent", "Secant");
    ObservableList choiceBoxPlanetList = FXCollections.observableArrayList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "My Planet");

    String[] methodsListString = {"Bisection", "Iterative", "Tangent", "Secant"};  //robie tak zeby nie porownywac obiektu z nazwa bo sie nie da tylko indeksy w listach
    //String [] planetListString= {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};


    //XYChart.Series<Number,Number> series = new XYChart.Series();
    XYChart.Series series = new XYChart.Series();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textT;

    @FXML
    private TextField textEccentricity;

    @FXML
    private TextField textDistance;

    @FXML
    private ChoiceBox<String> choiceBoxMethod;

    @FXML
    private ChoiceBox<String> choiceBoxPlanet;

    @FXML
    private TextField textDesiredError;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonDraw;

    @FXML
    private Button buttonAdjustPlanet;

    @FXML
    private Button buttonAdjustMethod;

    @FXML
    private TextArea action;


    @FXML
    private ScatterChart<Number, Number> chartTrajectory;


    @FXML
    void buttonClearOnAction(ActionEvent event) {
        chartTrajectory.getData().remove(series);
        series = new XYChart.Series();
        chartTrajectory.getData().add(series);
        a=0;
        textDistance.setText("0");
        eccentricity=0;
        textEccentricity.setText("0");
        T=0;
        textT.setText("0");
        desiredError=0;
        textDesiredError.setText("0");
        planet=null;
        method=null;


        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("Clear chart" + "\n");
        action.setText(actionInfo);

    }

    private void saveTextToFile() {
        File file = new File("C:/Users/wiole_5ewf698/IdeaProjects/MN_L5_projekt/src/main/java/MyTajectory.txt");
        try {
            PrintWriter writer;

            writer = new PrintWriter(file);
            writer.println("PUNKTY X");
            for (int i = 0; i < xPunkty.size(); i++) {
                writer.println(xPunkty.get(i));
            }
            writer.println("PUNKTY Y");
            for (int i = 0; i < yPunkty.size(); i++) {
                writer.println(yPunkty.get(i));
            }
            writer.close();
        } catch (IOException ex) {
            String actionInfo = this.action.getText();
            actionInfo = actionInfo.concat("Błąd zapisu do pliku");
            action.setText(actionInfo);
        }

    }


    @FXML
    void buttonSaveOnAction(ActionEvent event) {
        saveTextToFile();
        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("File saved" + "\n");
        action.setText(actionInfo);


    }


    @FXML
    void buttonDrawOnAction(ActionEvent event) {

        makeData();
        int max = xPunkty.size();
        for (int i = 0; i < max; i++) {
            series.getData().add(new XYChart.Data(xPunkty.get(i), yPunkty.get(i)));
        }
        System.out.println("Data done.");
        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("Data done" + "\n");
        action.setText(actionInfo);
        //series.getData().addAll(Collections.singleton(chartTrajectory.getData().setAll()));
        chartTrajectory.getData().add(series);

        actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("Series added" + "\n");
        action.setText(actionInfo);
    }


    @FXML
    void testDesiredErrorOnAction(ActionEvent event) {
        whatDesiredError();
    }

    @FXML
    void textDistanceOnAction(ActionEvent event) {
        whatDistance();

    }

    @FXML
    void textEccentricityOnAction(ActionEvent event) {
        whatEccetricity();

    }

    @FXML
    void buttonAdjustPlanetOnAction(ActionEvent event) {
        whichPlanet();

    }

    @FXML
    void buttonAdjustMethodOnAction(ActionEvent event) {
        whichMethod();

    }

    @FXML
    void textTOnAction(ActionEvent event) {
        String info = textT.getText();
        T = Double.parseDouble(info);

        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("T value: " + info.toString() + "\n");
        action.setText(actionInfo);

    }


    void whatEccetricity() {
        String info = textEccentricity.getText();
        eccentricity = Double.parseDouble(info);

        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("Eccentricity value: " + info.toString() + "\n");
        action.setText(actionInfo);

    }

    void whatDistance() {
        String info = textDistance.getText();
        a = Double.parseDouble(info);

        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("Distance value: " + info.toString() + "\n");
        action.setText(actionInfo);
    }

    void whatDesiredError() {
        String info = textDesiredError.getText();
        double infoDouble = Double.parseDouble(info);
        if (infoDouble >= 1) {
            String actionInfo = this.action.getText();
            actionInfo = actionInfo.concat("ERROR!(Set error value < 1)");
            action.setText(actionInfo);
        } else {
            desiredError = infoDouble;
            String actionInfo = this.action.getText();
            actionInfo = actionInfo.concat("Error value: " + info.toString() + "\n");
            action.setText(actionInfo);
        }


    }

    void whichPlanet() {

        String planet = choiceBoxPlanet.getValue();
        indexPlanet = IntStream.range(0, panetList.length).filter(i -> panetList[i] == planet).findFirst().getAsInt();
        System.out.println(indexPlanet);
        this.planet = planet;

        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("Planet: " + planet + "\n");
        action.setText(actionInfo);
    }

    void whichMethod() {
        String method = choiceBoxMethod.getValue();
        indexMethod = IntStream.range(0, methodsListString.length).filter(i -> methodsListString[i] == method).findFirst().getAsInt();
        System.out.println(indexMethod);
        this.method = method;

        String actionInfo = this.action.getText();
        actionInfo = actionInfo.concat("Method: " + method + "\n");
        action.setText(actionInfo);

    }

    void Data() {


        if (indexMethod == 0) {
            xPunkty = bisekcja.trajektoriaX(desiredError, T, eccentricity, a);
            yPunkty = bisekcja.trajektoriaY(desiredError, T, eccentricity, a);
        } else if (indexMethod == 1) {
            xPunkty = regulaPunktuStalego.trajektoriaX(desiredError, T, eccentricity, a);
            yPunkty = regulaPunktuStalego.trajektoriaY(desiredError, T, eccentricity, a);
        } else if (indexMethod == 2) {
            xPunkty = metodaStycznych.trajektoriaX(desiredError, T, eccentricity, a);
            yPunkty = metodaStycznych.trajektoriaY(desiredError, T, eccentricity, a);
        } else if (indexMethod == 3) {
            xPunkty = metodaSiecznych.trajektoriaX(desiredError, T, eccentricity, a);
            yPunkty = metodaSiecznych.trajektoriaY(desiredError, T, eccentricity, a);
        }
        System.out.println("a:");
        System.out.println(a);
        System.out.println("Eccentricity:");
        System.out.println(eccentricity);
        System.out.println("T:");
        System.out.println(T);
        System.out.println("Punkty X");
        System.out.println(xPunkty);
        System.out.println("Punkty Y");
        System.out.println(yPunkty);
    }

    void makeData() {

        if (choiceBoxPlanet.getValue().equals("My Planet") ) {
            if (T == 0 || eccentricity == 0 || eccentricity>=1 || a == 0 || desiredError == 0) {
                String actionInfo = this.action.getText();
                actionInfo = actionInfo.concat("ERROR! Set all parameters" + "\n" + "(T, eccentricity, a, desired error)" + "\n");
                action.setText(actionInfo);
            }
            else{
                String actionInfo = this.action.getText();
                actionInfo = actionInfo.concat("Parameters correct!" + "\n");
                action.setText(actionInfo);
                Data();
                System.out.println(xPunkty);
                System.out.println(yPunkty);
            }

        } else {
            a = aList[indexPlanet];
            eccentricity = eccentricityList[indexPlanet];
            T = TList[indexPlanet];
           Data();
        }
    }



    @FXML
    void initialize() {
        assert textEccentricity != null : "fx:id=\"textEccentricity\" was not injected: check your FXML file 'Test1.fxml'.";
        assert textDistance != null : "fx:id=\"textDistance\" was not injected: check your FXML file 'Test1.fxml'.";
        assert choiceBoxMethod != null : "fx:id=\"choiceBoxMethod\" was not injected: check your FXML file 'Test1.fxml'.";
        assert choiceBoxPlanet != null : "fx:id=\"choiceBoxPlanet\" was not injected: check your FXML file 'Test1.fxml'.";
        assert textDesiredError != null : "fx:id=\"textDesiredError\" was not injected: check your FXML file 'Test1.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'Test1.fxml'.";
        assert buttonClear != null : "fx:id=\"buttonClear\" was not injected: check your FXML file 'Test1.fxml'.";
        assert chartTrajectory != null : "fx:id=\"chartTrajectory\" was not injected: check your FXML file 'Test1.fxml'.";

        choiceBoxMethod.setItems(choiceBoxMethodList);
        choiceBoxPlanet.setItems(choiceBoxPlanetList);
        chartTrajectory.setLegendVisible(false);





    }
}
