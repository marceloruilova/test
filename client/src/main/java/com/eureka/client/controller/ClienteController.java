package com.eureka.client.controller;

import com.eureka.client.model.Cliente;
import com.eureka.client.service.ClienteService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> getClientes() {
        List<Cliente> savedCliente = clienteService.getAllClientes();
        return ResponseEntity.ok(savedCliente);
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.saveOrUpdateCliente(cliente);
        rabbitTemplate.convertAndSend("clientExchange", "client.created", savedCliente.getId());
        return ResponseEntity.ok(savedCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        rabbitTemplate.convertAndSend("clientExchange", "client.updated", updatedCliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        rabbitTemplate.convertAndSend("clientExchange", "client.deleted", id);
        return ResponseEntity.ok().build();
    }
}
