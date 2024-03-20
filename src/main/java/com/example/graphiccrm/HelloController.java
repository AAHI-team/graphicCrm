package com.example.graphiccrm;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.graphiccrm.dto.ClientDTO;
import com.example.graphiccrm.service.ClientService;

public class HelloController {
    @FXML
    private TableView<ClientDTO> clientTable;

    @FXML
    private TableColumn<ClientDTO, String> nameColumn;

    @FXML
    private TableColumn<ClientDTO, String> emailColumn;

    @FXML
    private TableColumn<ClientDTO, String> phoneColumn;

    @FXML
    private TableColumn<ClientDTO, String> addressColumn;

    @FXML
    private TableColumn<ClientDTO, String> clientTypeColumn;

    private final ClientService clientService;

    // Constructor
    public HelloController() {
        this.clientService = new ClientService(); // Instantiate client service
    }

    @FXML
    private void initialize() {
        // Initialize table columns
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        clientTypeColumn.setCellValueFactory(cellData -> cellData.getValue().clientTypeProperty());

        // Load client data into table
        loadClientData();
    }

    // Method to load client data into the table
    private void loadClientData() {
        clientTable.setItems(clientService.getAllClients());
    }
}
