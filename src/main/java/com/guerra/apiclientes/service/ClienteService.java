package com.guerra.apiclientes.service;

import com.guerra.apiclientes.exception.ClienteNotFound;
import com.guerra.apiclientes.exception.ConflictException;
import com.guerra.apiclientes.model.Cliente;
import org.springframework.stereotype.Service;
import com.guerra.apiclientes.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findOne(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFound("Cliente não encontrado."));
    }

    public Cliente createCliente(Cliente cliente) {
        Cliente newCliente = Cliente.builder()
                .razaoSocial(cliente.getRazaoSocial())
                .cnpj(cliente.getCnpj())
                .regimeTributario(cliente.getRegimeTributario())
                .email(cliente.getEmail())
                .ativo(cliente.isAtivo())
                .build();

        Optional<Cliente> optionalCliente = clienteRepository.findByCnpj(newCliente.getCnpj());

        if(optionalCliente.isPresent()) {
            throw new ConflictException("CNPJ já existente!");
        }

        return clienteRepository.save(newCliente);
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente optionalCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFound("Cliente não encontrado."));

        Cliente updatedCliente = Cliente.builder()
                .id(optionalCliente.getId())
                .razaoSocial(cliente.getRazaoSocial())
                .cnpj(cliente.getCnpj())
                .regimeTributario(cliente.getRegimeTributario())
                .email(cliente.getEmail())
                .ativo(cliente.isAtivo())
                .build();

        return clienteRepository.save(updatedCliente);
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFound("Cliente não encontrado."));

        if(!cliente.isAtivo()){
            throw new ConflictException("Cliente desativado.");
        }

        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

}
