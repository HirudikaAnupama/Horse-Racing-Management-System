package org.example.rapidrun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DeleteDetails {

    Array array = new Array();
    public TextField DeleteHorseIdTextArea;
    public Label DeleteHorseMsgLabel;

    public void onDeleteAddedHorseDetails(ActionEvent event) {
        // Check if the details array is empty
        if (array.getDetails_Array() == null || array.getDetails_Array().length == 0) {
            DeleteHorseIdTextArea.setText("No entries found."); // Display message if no entries found
            return;
        }

        // Get the id to delete from the input field
        String idToDelete = DeleteHorseIdTextArea.getText();

        // Delete the entry with the specified ID
        Array.deleteEntry(idToDelete);

        // Clear the input field for the ID
        DeleteHorseIdTextArea.clear();

        // Set the message based on the result of the delete operation
        if (Array.isEntryDeleted()) {
            DeleteHorseMsgLabel.setText("Removed ID " + idToDelete);
        } else {
            DeleteHorseMsgLabel.setText("ID " + idToDelete + " not found !");
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
