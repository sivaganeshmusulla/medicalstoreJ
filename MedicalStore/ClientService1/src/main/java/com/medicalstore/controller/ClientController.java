package com.medicalstore.controller;


import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicalstore.entity.Client;

import com.medicalstore.service.ClientService;


import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    
   
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    @GetMapping("-/getClient/{id}")
    public Client getClientByIds(@PathVariable Long id) {
    	Client client = clientService.getClientById(id);
        return client;
    
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = clientService.saveClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client existingClient = clientService.getClientById(id);
        
        existingClient.setFirstName(updatedClient.getFirstName());
        existingClient.setLastName(updatedClient.getLastName());
        existingClient.setContactNumber(updatedClient.getContactNumber());
        existingClient.setAddress(updatedClient.getAddress());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setDateOfBirth(updatedClient.getDateOfBirth());
        
        existingClient.setUsername(updatedClient.getUsername());
        existingClient.setPassword(updatedClient.getPassword());
        
        
        Client updatedClientEntity = clientService.saveClient(existingClient);
        return new ResponseEntity<>(updatedClientEntity, HttpStatus.OK);
    }
//    @GetMapping("/salesByClient/{clientId}")
//    public ResponseEntity<List<Sales>> getSalesByClient(@PathVariable Long clientId) {
//        List<Sales> sales = salesService.getSalesByClientId(clientId);
//        return new ResponseEntity<>(sales, HttpStatus.OK);
//    }

}
