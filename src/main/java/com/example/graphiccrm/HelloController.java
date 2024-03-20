package com.example.graphiccrm;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.graphiccrm.dto.ClientDTO;
import com.example.graphiccrm.service.ClientService;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private TableView<ClientDTO> clientTable;

    @FXML
    TableColumn<ClientDTO, String> nameColumn = new TableColumn<>("Name");

    @FXML
    TableColumn<ClientDTO, String> emailColumn = new TableColumn<>("Email");

    @FXML
    TableColumn<ClientDTO, String> phoneColumn = new TableColumn<>("Phone");


    @FXML
    TableColumn<ClientDTO, String> addressColumn = new TableColumn<>("Address");

    @FXML
    TableColumn<ClientDTO, String> clientTypeColumn = new TableColumn<>("Client Type");




    private ClientService clientService;

    // Constructor
    public HelloController() {
        this.clientService = new ClientService(); // Instantiate client service
    }

    @FXML
    private void initialize() {
        // Initialize table columns


        nameColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("name"));

        emailColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("email"));

        phoneColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("phone"));

        addressColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("address"));

        clientTypeColumn.setCellValueFactory(new PropertyValueFactory<ClientDTO, String>("clientType"));
        // Add columns to the TableView
        clientTable.getColumns().setAll(nameColumn, emailColumn, phoneColumn, addressColumn, clientTypeColumn);
        // Load client data into table
        //loadClientData();
        List<ClientDTO> list = new ArrayList<>();
        list.add(new ClientDTO("name" , "phone", "phone", "address", "clientType"));
        clientTable.setItems(FXCollections.observableArrayList(list));
    }

    // Method to load client data into the table
    private void loadClientData() {
        clientTable.setItems(clientService.getAllClients());
    }
}
