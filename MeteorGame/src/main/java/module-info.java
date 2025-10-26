module org.example.meteorgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.meteorgame to javafx.fxml;
    exports org.example.meteorgame;
}