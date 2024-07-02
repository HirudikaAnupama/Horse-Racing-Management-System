package org.example.rapidrun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class SaveDetails {

    public Label saveDetailsLabel;

    Array array = new Array();

    private static String[][] SaveDetailsArray;

    public SaveDetails() {
       SaveDetailsArray = array.getDetails_Array();
    }

    public void onSaveHorseDetails(ActionEvent event) {
        // File paths for different groups and all horse details
        String filePathA = "Group A horses details.txt";
        String filePathB = "Group B horses details.txt";
        String filePathC = "Group C horses details.txt";
        String filePathD = "Group D horses details.txt";
        String allFilePath = "All horses details.txt";

        try {
            // Clear existing content of the group files and all horse details file
            Files.write(Paths.get(filePathA), "".getBytes());
            Files.write(Paths.get(filePathB), "".getBytes());
            Files.write(Paths.get(filePathC), "".getBytes());
            Files.write(Paths.get(filePathD), "".getBytes());
            Files.write(Paths.get(allFilePath), "".getBytes());

            // Iterate through horse details
            for (String[] horseDetails : SaveDetailsArray) {
                if (horseDetails != null && horseDetails.length > 6) {
                    String group = horseDetails[6]; // Get the group identifier
                    if (group != null && !group.isEmpty()) {
                        // Determine the file path based on the group
                        String filePath;
                        switch (group.charAt(0)) {
                            case 'A':
                                filePath = filePathA;
                                break;
                            case 'B':
                                filePath = filePathB;
                                break;
                            case 'C':
                                filePath = filePathC;
                                break;
                            case 'D':
                                filePath = filePathD;
                                break;
                            default:
                                continue; // Skip details with unrecognized groups
                        }

                        // Write the horse details to the corresponding group file
                        writeToFile(filePath, horseDetails);

                        // Append the horse details to the "All_horses_details.txt" file
                        writeToFile(allFilePath, horseDetails);
                    }
                }
            }

            saveDetailsLabel.setText("Horse details categorized and saved to files successfully.");
            saveDetailsLabel.setVisible(true); // Make the label visible
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to write horse details to a file
    private void writeToFile(String filePath, String[] horseDetails) throws Exception {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            // Write the horse details to the file
            writer.write(formatDetails(horseDetails) + System.lineSeparator());
        }
    }

    // Method to format horse details for writing to file
    private String formatDetails(String[] horseDetails) {
        // Format the horse details as a string
        return "Horse ID: " + horseDetails[0] + ", Horse Name: " + horseDetails[1] + ", Jacky Name: " +
                horseDetails[2] + ", Age: " + horseDetails[3] + ", Breed: " + horseDetails[4] +
                ", Race Record: " + horseDetails[5] + ", Group: " + horseDetails[6];
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
    private void onRandomDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("random-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onWinnerDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Get the current stage from the button
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("winner-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }


    @FXML
    private void onExit() throws IOException{
        System.exit(0);
    }


}
