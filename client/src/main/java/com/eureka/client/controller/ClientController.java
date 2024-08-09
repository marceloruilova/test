package com.eureka.client.controller;

import com.eureka.client.model.Client;
import com.eureka.client.service.ClientService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        List<Client> savedClient = clientService.getAllClients();
        return ResponseEntity.ok(savedClient);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveOrUpdateClient(client);
        rabbitTemplate.convertAndSend("clientExchange", "client.created", savedClient.getId());
        return ResponseEntity.ok(savedClient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client updatedClient = clientService.updateAndMapClient(id, client);
        rabbitTemplate.convertAndSend("clientExchange", "client.updated", updatedClient);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        rabbitTemplate.convertAndSend("clientExchange", "client.deleted", id);
        return ResponseEntity.ok().build();
    }
}
