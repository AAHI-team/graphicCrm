package com.example.graphiccrm.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.graphiccrm.dto.ClientDTO;

import java.util.Arrays;
import java.util.List;

public class ClientService {
    // Method to fetch all clients (dummy implementation)
    public ObservableList<ClientDTO> getAllClients() {
        // Dummy list of clients (replace with actual data fetched from the server)
        List<ClientDTO> clients = Arrays.asList(
                new ClientDTO("John Doe", "john@example.com", "123456789", "123 Main St", "Regular"),
                new ClientDTO("Jane Smith", "jane@example.com", "987654321", "456 Oak Ave", "VIP")
        );

        // Convert the list to an observable list
        return FXCollections.observableArrayList(clients);
    }
}
