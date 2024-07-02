package org.example.rapidrun;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;



public class UpdateDetails {

    public TextField UpdateDetailsHorseIDLabel;
    public TextField UpdateDetailsHorseNameLabel;
    public TextField UpdateDetailsJackyNameLabel;
    public TextField UpdateDetailsAgeLabel;
    public TextField UpdateDetailsBreedLabel;
    public TextField UpdateDetailsRaceRecordLabel;
    public Label UpdatingHorseDetailsMsgBox;

    @FXML
    private ChoiceBox<String> groupChoiceBox;

    private final String[] groups = {"A", "B", "C", "D"};

    private static final String IMAGE_DIR = "stored_images/";

    static final List<File> updateImageFiles = new ArrayList<>();

    Array array = new Array();

    public UpdateDetails() {
        // Initialize the ChoiceBox with values
        groupChoiceBox = new ChoiceBox<>();
        groupChoiceBox.getItems().addAll(groups);
    }

    @FXML
    public void initialize() {
        // Initialize the ChoiceBox with values
        groupChoiceBox.getItems().addAll(groups);

    }

    public void onUpdateImageUploadButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
        if (selectedFiles != null) {
            for (File selectedFile : selectedFiles) {
                try {
                    File dir = new File(IMAGE_DIR);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    String fileName = selectedFile.getName();
                    File storedFile = new File(IMAGE_DIR + fileName);

                    // Handle existing file scenario
                    if (storedFile.exists()) {
                        // Example: Append timestamp to avoid overwrite
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        storedFile = new File(IMAGE_DIR + timeStamp + "_" + fileName);
                    }

                    Files.copy(selectedFile.toPath(), storedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Update UI on JavaFX Application Thread
                    File finalStoredFile = storedFile;
                    Platform.runLater(() -> {
                        updateImageFiles.add(finalStoredFile);
                        // Update UI elements if needed
                    });

                } catch (IOException ex) {
                    ex.printStackTrace();
                    // Handle or log the exception
                }
            }
        }
    }

    public void onUpdateDetailsConfirm(ActionEvent event) {

        String horseID = UpdateDetailsHorseIDLabel.getText();
        String horseName = UpdateDetailsHorseNameLabel.getText();
        String jackyName = UpdateDetailsJackyNameLabel.getText();
        String age = UpdateDetailsAgeLabel.getText();
        String breed = UpdateDetailsBreedLabel.getText();
        String raceRecord = UpdateDetailsRaceRecordLabel.getText();
        String group = groupChoiceBox.getValue();

        // Check if any of the fields are empty
        if (horseID.isEmpty() || horseName.isEmpty() || jackyName.isEmpty() || age.isEmpty() || breed.isEmpty() || raceRecord.isEmpty()) {
            // Display a message indicating all fields must be filled
            UpdatingHorseDetailsMsgBox.setText("Fill all the mandatory fields !");
            UpdatingHorseDetailsMsgBox.setVisible(true);
            return; // Exit the method
        }

        // Check if group is null or empty
        if (group == null || group.isEmpty()) {
            UpdatingHorseDetailsMsgBox.setText("Select horse group !");
            UpdatingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Check whether Horse ID is integer or not
        int value;
        try {
            value = Integer.parseInt(horseID);
        } catch (NumberFormatException e) {
            UpdatingHorseDetailsMsgBox.setText("ID should be integers !");
            UpdatingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Get the image paths
        List<String> imagePaths = new ArrayList<>();
        for (File file : updateImageFiles) {
            imagePaths.add(file.getPath());
        }

        if (imagePaths.isEmpty()){
            UpdatingHorseDetailsMsgBox.setText("* Insert horse image");
            UpdatingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Check if Horse ID(Label_1) exists in the array and update its entry
        for (int i = 0; i < array.getDetails_Array().length; i++) {
            // Check if the entry exists and has the specified label_1
            if (array.getDetails_Array()[i] != null && array.getDetails_Array()[i].length > 0 && array.getDetails_Array()[i][0] != null && array.getDetails_Array()[i][0].equals(horseID)) {
                // Update the entry with the new values

                array.getDetails_Array()[i] = new String[]{horseID, horseName, jackyName, age, breed, raceRecord, group, String.valueOf(imagePaths)};
                // Display a message indicating the entry was updated
                UpdatingHorseDetailsMsgBox.setText("Horse details Updated");
                UpdatingHorseDetailsMsgBox.setVisible(true);
                // Clear the labels after successful update
                clearLabels();
                return;
            }
        }
        // Display a message indicating the entry with label_1 was not found
        UpdatingHorseDetailsMsgBox.setText("The horse ID entered cannot be found !");
        UpdatingHorseDetailsMsgBox.setVisible(true);
    }

    // Method to clear all the labels
    private void clearLabels() {
        UpdateDetailsHorseIDLabel.clear();
        UpdateDetailsHorseNameLabel.clear();
        UpdateDetailsJackyNameLabel.clear();
        UpdateDetailsAgeLabel.clear();
        UpdateDetailsBreedLabel.clear();
        UpdateDetailsRaceRecordLabel.clear();
        groupChoiceBox.getItems().clear();
        updateImageFiles.clear();

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
