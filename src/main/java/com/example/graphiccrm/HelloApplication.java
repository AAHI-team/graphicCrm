package com.example.graphiccrm;

import com.example.graphiccrm.dto.ClientDTO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



import java.io.IOException;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create TableView to display client data
        TableView<ClientDTO> tableView = new TableView<>();

        // Define columns for the TableView
        TableColumn<ClientDTO, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<ClientDTO, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        TableColumn<ClientDTO, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        TableColumn<ClientDTO, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        TableColumn<ClientDTO, String> clientTypeColumn = new TableColumn<>("Client Type");
        clientTypeColumn.setCellValueFactory(cellData -> cellData.getValue().clientTypeProperty());

        // Add columns to the TableView
        tableView.getColumns().addAll(nameColumn, emailColumn, phoneColumn, addressColumn, clientTypeColumn);

        // Create a button to fetch client data
        Button fetchButton = new Button("Fetch Clients");

        // Set action for the fetch button
        fetchButton.setOnAction(event -> {
            try {
                // Call method to fetch and display client data
                tableView.setItems(fetchData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Create a VBox layout to hold the TableView and button
        VBox root = new VBox(10); // 10 pixels spacing between children
        root.setPadding(new Insets(10)); // 10 pixels padding around the VBox
        root.getChildren().addAll(fetchButton, tableView);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 800, 600); // Set scene size
        stage.setScene(scene);
        stage.setTitle("JavaFX Client"); // Set window title
        stage.show(); // Show the stage
    }

    // Method to fetch client data from the backend server
    private ObservableList<ClientDTO> fetchData() throws IOException {
        // Make HTTP GET request to fetch client data from the server
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:4567/members")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Parse JSON response and populate ObservableList
            Gson gson = new Gson();
            ClientDTO[] clientDTOArray = gson.fromJson(response.body().string(), ClientDTO[].class);
            return FXCollections.observableArrayList(Arrays.asList(clientDTOArray));
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
