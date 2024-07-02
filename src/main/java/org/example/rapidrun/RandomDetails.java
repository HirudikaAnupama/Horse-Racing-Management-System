package org.example.rapidrun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomDetails {

    private static final String[][] Random_horses_details_Array = new String[4][7]; // Array to store random horse names

    @FXML
    private Label Group_A_label;
    @FXML
    private Label Group_B_label;
    @FXML
    private Label Group_C_label;
    @FXML
    private Label Group_D_label;

    @FXML
    private ImageView Group_A_imageView;
    @FXML
    private ImageView Group_B_imageView;
    @FXML
    private ImageView Group_C_imageView;
    @FXML
    private ImageView Group_D_imageView;

    private final Array array = new Array(); // Assuming Array class is correctly implemented and accessible

    @FXML
    private void initialize() {
        String[][] Details_Array = array.getDetails_Array();

        List<String> detailsA = readRandomDetailsFromFile("Group A horses details.txt");
        List<String> detailsB = readRandomDetailsFromFile("Group B horses details.txt");
        List<String> detailsC = readRandomDetailsFromFile("Group C horses details.txt");
        List<String> detailsD = readRandomDetailsFromFile("Group D horses details.txt");

        String detailA = getRandomDetail(detailsA);
        String detailB = getRandomDetail(detailsB);
        String detailC = getRandomDetail(detailsC);
        String detailD = getRandomDetail(detailsD);

        horseNameA = extractHorseName(detailA);
        horseNameB = extractHorseName(detailB);
        horseNameC = extractHorseName(detailC);
        horseNameD = extractHorseName(detailD);

        Group_A_label.setText("Name : " + horseNameA);
        Group_B_label.setText("Name : " + horseNameB);
        Group_C_label.setText("Name : " + horseNameC);
        Group_D_label.setText("Name : " + horseNameD);

        Random_horses_details_Array[0] = horseNameA.split(" ");
        Random_horses_details_Array[1] = horseNameB.split(" ");
        Random_horses_details_Array[2] = horseNameC.split(" ");
        Random_horses_details_Array[3] = horseNameD.split(" ");






        printLastColumn(Details_Array);
    }

    private String horseNameA;
    private String horseNameB;
    private String horseNameC;
    private String horseNameD;



    private void printLastColumn(String[][] detailsArray) {
        if (detailsArray != null && detailsArray.length > 0) {

            for (String[] row : detailsArray) {
                if (row != null && row.length > 1 && row[1] != null) { // Add null checks
                    String horseName = row[1].trim(); // Trim after null check
                    if (!horseName.isEmpty()) {
                        String horseImage = row[row.length - 1].trim(); // Assuming last column has image path
                        horseImage = horseImage.replace("[", "").replace("]", ""); // Remove brackets
                        String convertedPath = convertPath(horseImage);


                        // Set image based on horseName
                        if (Objects.equals(horseNameA, horseName)) {
                            Group_A_imageView.setImage(new Image("file:" + convertedPath));
                        } else if (Objects.equals(horseNameB, horseName)) {
                            Group_B_imageView.setImage(new Image("file:" + convertedPath));
                        } else if (Objects.equals(horseNameC, horseName)) {
                            Group_C_imageView.setImage(new Image("file:" + convertedPath));
                        } else if (Objects.equals(horseNameD, horseName)) {
                            Group_D_imageView.setImage(new Image("file:" + convertedPath));
                        }
                    }
                }
            }
        }
    }



    public static String convertPath(String path) {
        return path.replace("\\", "/");
    }
    private List<String> readRandomDetailsFromFile(String filePath) {
        List<String> details = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                details.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return details;
    }

    private String getRandomDetail(List<String> details) {
        if (!details.isEmpty()) {
            Random random = new Random();
            return details.get(random.nextInt(details.size()));
        } else {
            return "Enter and save details first!";
        }
    }

    private String extractHorseName(String detail) {
        if (detail == null || detail.isEmpty()) {
            return "No data";
        }
        String[] parts = detail.split(",");
        for (String part : parts) {
            if (part.trim().startsWith("Horse Name:")) {
                String horseName = part.split(":")[1].trim();
                if (!horseName.isEmpty()) {
                    return horseName;
                }
            }
        }
        return "No name found";
    }


    public static String[][] getRandom_horses_details_Array() {
        return Random_horses_details_Array;
    }




    @FXML
    private void onAddDetails(ActionEvent event) throws IOException {
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
    private void onWinnerDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("winner-details.fxml")));
        stage.setScene(new Scene(parent, 1100, 900));
        stage.setTitle("Rapid run");
    }

    @FXML
    private void onExit() throws IOException{
        System.exit(0);
    }
}
