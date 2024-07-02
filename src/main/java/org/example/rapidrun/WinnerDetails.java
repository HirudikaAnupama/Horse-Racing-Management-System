package org.example.rapidrun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

import java.io.IOException;
import java.util.*;
public class WinnerDetails {

    private final String[][] randomHorsesDetailsArray;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label winnerLabel;

    // Default constructor needed for FXMLLoader
    public WinnerDetails() {
        randomHorsesDetailsArray = RandomDetails.getRandom_horses_details_Array();
    }

    // Initialize method called after FXML file is loaded
    @FXML
    public void initialize() {
        if (randomHorsesDetailsArray == null || randomHorsesDetailsArray.length == 0) {
            winnerLabel.setText("Invalid or empty horse details array.");
            return;
        }

        List<String> horseNames = extractHorseNamesFromArray();

        if (horseNames.isEmpty()) {
            winnerLabel.setText("Select random horses before selecting winners");
            return;
        }

        if (horseNames.size() < 4) {
            winnerLabel.setText("Select at least 4 random horses");
            return;
        }

        populateBarChartAndWinnerLabel(horseNames);
    }

    // Method to extract horse names from the 2D array
    private List<String> extractHorseNamesFromArray() {
        List<String> horseNames = new ArrayList<>();
        for (String[] row : randomHorsesDetailsArray) {
            if (row != null && row.length > 0 && row[0] != null && !row[0].isEmpty()) {
                horseNames.add(row[0]); // Assuming horse name is at index 0
            }
        }
        return horseNames;
    }

    private void populateBarChartAndWinnerLabel(List<String> horseNames) {
        barChart.getData().clear();
        xAxis.getCategories().clear();

        // Select 4 random horse names
        List<String> selectedHorseNames = selectRandomHorseNames(horseNames, 4);

        // Prepare data for chart
        List<Integer> raceTimes = generateRandomTimes(selectedHorseNames.size());
        List<Pair<String, Integer>> horseTimePairs = pairHorseNamesWithTimes(selectedHorseNames, raceTimes);

        // Sort pairs by time in ascending order
        horseTimePairs.sort(Comparator.comparingInt(Pair::getValue));

        // Populate bar chart with the top 3 horses
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < Math.min(horseTimePairs.size(), 3); i++) {
            String horseName = horseTimePairs.get(i).getKey();
            int time = horseTimePairs.get(i).getValue();
            series.getData().add(new XYChart.Data<>(horseName, time));
        }

        barChart.getData().add(series);

        // Customize axis labels
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));
        xAxis.setTickLabelFill(Color.MAROON);

        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 14));
        yAxis.setTickLabelFill(Color.MAROON);

        // Set winner label text
        StringBuilder winnerText = new StringBuilder();
        for (int i = 0; i < Math.min(horseTimePairs.size(), 3); i++) {
            String place = (i == 0) ? "1st place :  " : (i == 1) ? "2nd place :  " : "3rd place :  ";
            String horseName = horseTimePairs.get(i).getKey();
            int time = horseTimePairs.get(i).getValue();
            winnerText.append(place).append(horseName).append("  -  ").append(time).append("s\n");
        }
        winnerLabel.setText(winnerText.toString());
    }

    private List<String> selectRandomHorseNames(List<String> horseNames, int count) {
        Collections.shuffle(horseNames);
        return horseNames.subList(0, Math.min(count, horseNames.size()));
    }

    private List<Pair<String, Integer>> pairHorseNamesWithTimes(List<String> horseNames, List<Integer> times) {
        List<Pair<String, Integer>> horseTimePairs = new ArrayList<>();
        for (int i = 0; i < horseNames.size(); i++) {
            horseTimePairs.add(new Pair<>(horseNames.get(i), times.get(i)));
        }
        return horseTimePairs;
    }

    private List<Integer> generateRandomTimes(int count) {
        Random random = new Random();
        List<Integer> times = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            times.add(random.nextInt(90) + 10); // Random time between 10 and 99 seconds
        }
        return times;
    }


    @FXML
    private void onAddDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }


    @FXML
    private void onDeleteDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onUpdateDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("update-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onViewDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }


    @FXML
    private void onSaveDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("save-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onRandomDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("random-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }


    @FXML
    private void onExit() throws IOException{
        System.exit(0);
    }
}
