module com.example.charnumfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.charnumfx to javafx.fxml;
    exports com.example.charnumfx;
}