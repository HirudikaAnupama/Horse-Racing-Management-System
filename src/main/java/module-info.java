module org.example.rapidrun {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rapidrun to javafx.fxml;
    exports org.example.rapidrun;
}