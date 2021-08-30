module com.example.cs449sosgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.cs449sosgame to javafx.fxml;
    exports com.example.cs449sosgame;
}