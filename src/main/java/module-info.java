module com.example.graphiccrm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires okhttp3;
    requires com.google.gson;


    opens com.example.graphiccrm to javafx.base, javafx.fxml;
    opens com.example.graphiccrm.dto to javafx.base, javafx.fxml, com.google.gson;
    exports com.example.graphiccrm;
}