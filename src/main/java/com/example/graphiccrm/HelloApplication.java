package com.example.graphiccrm;


import com.google.gson.Gson;
import com.example.graphiccrm.dto.ClientDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    String URL = "http://localhost:4567";
    @Override
    public void start(Stage stage) throws IOException {
        // Create TableView to display client data
        TableView<ClientDTO> tableView = new TableView<>();

        // Define columns for the TableView
        TableColumn<ClientDTO, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("name"));

        TableColumn<ClientDTO, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("email"));

        TableColumn<ClientDTO, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("phone"));

        TableColumn<ClientDTO, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("address"));

        TableColumn<ClientDTO, String> clientTypeColumn = new TableColumn<>("Client Type");
        clientTypeColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("clientType"));

        // Add columns to the TableView
        tableView.getColumns().setAll(nameColumn, emailColumn, phoneColumn, addressColumn, clientTypeColumn);

        // Create a button to fetch client data
        Button fetchButton = new Button("Fetch Clients");

        // Set action for the fetch button
        fetchButton.setOnAction(event -> {
            // Call method to fetch and display client data
            tableView.setItems(fetchData());
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
    private ObservableList<ClientDTO> fetchData(){
        ObservableList<ClientDTO> clientDTOObservableList = FXCollections.observableArrayList();
        try {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL + "/clients")
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String dataString = response.body().string();
        Gson gson = new Gson();

        TypeToken<List<ClientDTO>> collectionType = new TypeToken<>(){};
// Note: For older Gson versions it is necessary to use `collectionType.getType()` as argument below,
// this is however not type-safe and care must be taken to specify the correct type for the local variable
            List<ClientDTO> list = gson.fromJson(dataString, collectionType);

        clientDTOObservableList = FXCollections.observableArrayList(list);
        System.out.println(list);
            System.out.println(list.get(0));
        }catch (IOException ioException){

        }finally {

        }
        List<ClientDTO> list = new ArrayList<>();
        list.add(new ClientDTO("name" , "phone", "phone", "address", "clientType"));
        ObservableList<ClientDTO> clientDTOS = FXCollections.observableArrayList(list);
        return clientDTOObservableList;
    }

    public static void main(String[] args) {
        launch();
    }
}
