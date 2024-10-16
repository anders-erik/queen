module com.example.queen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.queen to javafx.fxml;
    exports com.example.queen;
}