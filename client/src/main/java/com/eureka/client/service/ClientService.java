package com.eureka.client.service;

import com.eureka.client.model.Client;
import com.eureka.client.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveOrUpdateClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client updateAndMapClient(Long id, Client updatedClient) {
        return clientRepository.findById(id).map(client -> {
            client.setName(updatedClient.getName());
            client.setGender(updatedClient.getGender());
            client.setAge(updatedClient.getAge());
            client.setCi(updatedClient.getCi());
            client.setAddress(updatedClient.getAddress());
            client.setCellphone(updatedClient.getCellphone());
            client.setClientId(updatedClient.getClientId());
            client.setPassword(updatedClient.getPassword());
            client.setState(updatedClient.getState());
            return clientRepository.save(client);
        }).orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        clientRepository.delete(client);
    }
}
