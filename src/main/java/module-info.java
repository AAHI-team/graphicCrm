module com.example.graphiccrm {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;


    opens com.example.graphiccrm to javafx.fxml;
    exports com.example.graphiccrm;
}