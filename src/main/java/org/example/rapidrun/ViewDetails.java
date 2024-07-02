package org.example.rapidrun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ViewDetails {

    @FXML
    private ListView<HBox> listView;

    private final Array array = new Array();

    @FXML
    public void initialize() {
        // Clear the previous content
        listView.getItems().clear();

        displayDetails();
    }

    public void displayDetails() {
        String[][] detailsArray = array.getDetails_Array();

        // Bubble sort the detailsArray based on the first column (ID)
        bubbleSort(detailsArray);

        for (int i = 0; i < 20; i++) {
            if (i < detailsArray.length) {
                String[] details = detailsArray[i];
                if (details != null && details.length >= 8 && details[7] != null) {
                    String imagePath = details[7];
                    try {
                        Image image = loadImage(imagePath.substring(1, imagePath.length() - 1));
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(210); // Set width as needed
                        imageView.setFitHeight(210); // Set height as needed

                        // Create labels for other details, each on a new line
                        Label idLabel = new Label(" ID : " + details[0]);
                        Label nameLabel = new Label(" Name : " + details[1]);
                        Label jockeyNameLabel = new Label(" Jockey : " + details[2]);
                        Label ageLabel = new Label(" Age : " + details[3]);
                        Label breedLabel = new Label(" Breed : " + details[4]);
                        Label raceRecordLabel = new Label(" Race Record : " + details[5]);
                        Label groupLabel = new Label(" Group : " + details[6]);

                        // Create a VBox to hold all labels vertically
                        VBox vBox = new VBox(5); // 5 is the spacing between elements
                        vBox.getChildren().addAll(idLabel, nameLabel, jockeyNameLabel, ageLabel, breedLabel, raceRecordLabel, groupLabel);

                        // Create an HBox to hold the image and VBox with labels
                        HBox hBox = new HBox(10); // 10 is the spacing between elements
                        hBox.setPadding(new Insets(10));
                        hBox.getChildren().addAll(imageView, vBox);

                        // Add the HBox to the ListView
                        listView.getItems().add(hBox);

                    } catch (FileNotFoundException e) {
                        System.err.println("Error loading image: " + e.getMessage());  // Debugging line
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private Image loadImage(String imagePath) throws FileNotFoundException {
        try {
            // Ensure the file path is correct and the file exists
            File imageFile = new File(imagePath);
            if (!imageFile.exists() || !imageFile.canRead()) {
                throw new FileNotFoundException("File not found or not readable: " + imageFile.getAbsolutePath());
            }

            // Attempt to load the image directly
            return new Image(new FileInputStream(imageFile));
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    // Bubble sort algorithm to sort the array by Horse ID
    private void bubbleSort(String[][] arr) {
        if (arr == null || arr.length == 0) {
            // Handle the case where arr is null or empty
            System.out.println(" ");
            return;
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Check if arr[j] and arr[j+1] are not null before accessing them
                if (arr[j] != null && arr[j + 1] != null && arr[j][0] != null && arr[j + 1][0] != null) {
                    try {
                        // Convert Horse ID to integer for comparison
                        int id1 = Integer.parseInt(arr[j][0]);
                        int id2 = Integer.parseInt(arr[j + 1][0]);
                        if (id1 > id2) {
                            // Swap entries
                            String[] temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    } catch (NumberFormatException e) {
                        // Handle parsing errors
                        System.out.println(" " + e.getMessage());
                    }
                }
            }
        }
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
    private void onExit(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
