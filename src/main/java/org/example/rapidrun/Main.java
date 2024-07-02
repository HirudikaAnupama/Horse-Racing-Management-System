package org.example.rapidrun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("welcome-menu.fxml"));

        Image icon = new Image("icon-1.jpg");
        stage.getIcons().add(icon);


        Scene scene = new Scene(fxmlLoader.load(), 1100, 900);
        stage.setTitle("Rapid run");
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }


}