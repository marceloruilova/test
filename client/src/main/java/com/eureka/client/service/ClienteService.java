package com.eureka.client.service;

import com.eureka.client.model.Cliente;
import com.eureka.client.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveOrUpdateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente not found"));
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente updateCliente(Long id, Cliente updatedCliente) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(updatedCliente.getNombre());
            cliente.setGenero(updatedCliente.getGenero());
            cliente.setEdad(updatedCliente.getEdad());
            cliente.setIdentificacion(updatedCliente.getIdentificacion());
            cliente.setDireccion(updatedCliente.getDireccion());
            cliente.setTelefono(updatedCliente.getTelefono());
            cliente.setClienteId(updatedCliente.getClienteId());
            cliente.setContraseña(updatedCliente.getContraseña());
            cliente.setEstado(updatedCliente.getEstado());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new EntityNotFoundException("Cliente not found"));
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente not found"));
        clienteRepository.delete(cliente);
    }
}
