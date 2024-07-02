package org.example.rapidrun;

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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddDetails {

    public TextField addDetailsHorseIDLabel;
    public TextField addDetailsHorseNameLabel;
    public TextField addDetailsJackyNameLabel;
    public TextField addDetailsAgeLabel;
    public TextField addDetailsBreedLabel;
    public TextField addDetailsRaceRecordLabel;
    public Label addingHorseDetailsMsgBox;

    @FXML
    private ChoiceBox<String> groupChoiceBox;
    private final String[] groups = {"A", "B", "C", "D"};
    Array array = new Array();

    private static final String IMAGE_DIR = "stored_images/";

    static final List<File> imageFiles = new ArrayList<>();

    public AddDetails() {
        // Initialize the ChoiceBox with values
        groupChoiceBox = new ChoiceBox<>();
        groupChoiceBox.getItems().addAll(groups);
    }

    @FXML
    public void initialize() {
        // Initialize the ChoiceBox with values
        groupChoiceBox.getItems().addAll(groups);
    }

    @FXML
    protected void onImageUploadButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);
        if (selectedFiles != null) {
            for (File selectedFile : selectedFiles) {
                File dir = new File(IMAGE_DIR);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File storedFile = new File(IMAGE_DIR + selectedFile.getName());
                try {
                    copyFile(selectedFile.toPath(), storedFile.toPath());
                    imageFiles.add(storedFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    addingHorseDetailsMsgBox.setText("Failed to upload image: " + selectedFile.getName());
                    addingHorseDetailsMsgBox.setVisible(true);
                }
            }
        }
    }

    private void copyFile(Path source, Path destination) throws IOException {
        // Ensure the destination directory exists
        Files.createDirectories(destination.getParent());

        // Use try-with-resources to ensure the streams are closed
        try (FileChannel sourceChannel = new FileInputStream(source.toFile()).getChannel();
             FileChannel destChannel = new FileOutputStream(destination.toFile()).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
    }


    @FXML
    private void onAddDetailsConfirm() {
        String horseID = addDetailsHorseIDLabel.getText();
        String horseName = addDetailsHorseNameLabel.getText();
        String jackyName = addDetailsJackyNameLabel.getText();
        String age = addDetailsAgeLabel.getText();
        String breed = addDetailsBreedLabel.getText();
        String raceRecord = addDetailsRaceRecordLabel.getText();
        String group = groupChoiceBox.getValue();

        // Check some validations here

        // Check if all fields are empty or not
        if (horseID.isEmpty() || horseName.isEmpty() || jackyName.isEmpty() || age.isEmpty() || breed.isEmpty() ||
                raceRecord.isEmpty()) {
            addingHorseDetailsMsgBox.setText("* Fill all mandatory fields !");
            addingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Check horse group is null or not
        if (group == null || group.isEmpty()) {
            addingHorseDetailsMsgBox.setText("* Select horse group !");
            addingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Check whether Horse ID is integer or not
        try {
            int horse_ID_value = Integer.parseInt(horseID);
        } catch (NumberFormatException e) {
            addingHorseDetailsMsgBox.setText("ID should be integer !");
            addingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Get the image paths
        List<String> imagePaths = new ArrayList<>();
        for (File file : imageFiles) {
            imagePaths.add(file.getPath());
        }

        if (imagePaths.isEmpty()) {
            addingHorseDetailsMsgBox.setText("* Insert horse image");
            addingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Add Horse details correctly
        array.addDetails(horseID, horseName, jackyName, age, breed, raceRecord, group, imagePaths);

        // Display success message
        addingHorseDetailsMsgBox.setText("Data added successfully");
        addingHorseDetailsMsgBox.setVisible(true);

        // Check if the horse ID is already exists
        if (array.isIsHorseIDExist()) {
            addingHorseDetailsMsgBox.setText("Entered ID is already exists!");
            addingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        // Check if the array is full or not
        if (array.isArrayFull()) {
            addingHorseDetailsMsgBox.setText("Array is full!");
            addingHorseDetailsMsgBox.setVisible(true);
            return;
        }

        addDetailsHorseIDLabel.clear();
        addDetailsHorseNameLabel.clear();
        addDetailsJackyNameLabel.clear();
        addDetailsAgeLabel.clear();
        addDetailsBreedLabel.clear();
        addDetailsRaceRecordLabel.clear();
        groupChoiceBox.getItems().clear();
        imageFiles.clear();

        // Add group items back
        groupChoiceBox.getItems().addAll(groups); // Assuming groups is your array of items
    }

    @FXML
    private void onMenuPageDisplay(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onDeleteDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onUpdateDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("update-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onViewDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onSaveDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("save-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onRandomDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("random-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onWinnerDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("winner-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onExit() {
        System.exit(0);
    }
}
