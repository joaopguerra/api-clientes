package com.guerra.apiclientes.service;

import com.guerra.apiclientes.exception.ClienteNotFound;
import com.guerra.apiclientes.exception.ConflictException;
import com.guerra.apiclientes.model.Cliente;
import com.guerra.apiclientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClienteServiceTest {

    private final ClienteRepository clienteRepository = mock(ClienteRepository.class);

    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    void findAll() {
        Cliente cliente = Cliente.builder().build();

        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(cliente));

        clienteService.findAll();

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void findOne() {
        Cliente cliente = Cliente.builder().id(1L).build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        clienteService.findOne(1L);

        verify(clienteRepository, times(1)).findById(1L);
        assertEquals(1L, cliente.getId());
    }

    @Test
    void createClienteSuccess() {
        Cliente cliente1 = Cliente.builder().id(1L).cnpj("1234").build();
        Cliente cliente2 = Cliente.builder().cnpj("6789").build();

        when(clienteRepository.findByCnpj(cliente1.getCnpj())).thenReturn(Optional.of(cliente1));

        when(clienteRepository.save(cliente2)).thenReturn(cliente2);

        clienteService.createCliente(cliente2);

        verify(clienteRepository, times(1)).save(cliente2);
        assertNotEquals(cliente1, cliente2);

    }

    @Test
    void createClienteFail() {
        Cliente cliente1 = Cliente.builder().id(1L).cnpj("1234").build();
        Cliente cliente2 = Cliente.builder().id(2L).cnpj("1234").build();

        when(clienteRepository.findByCnpj(cliente1.getCnpj())).thenReturn(Optional.of(cliente2));

        ConflictException conflictException = assertThrows(ConflictException.class,
                () -> clienteService.createCliente(cliente2));


        verify(clienteRepository, times(0)).save(cliente2);
        assertEquals("CNPJ já existente!", conflictException.getMessage());

    }

    @Test
    void updateSuccess() {
        Cliente cliente = Cliente.builder().id(1L).build();
        Cliente updatedCliente = Cliente.builder().id(1L).build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        clienteService.update(cliente.getId(), updatedCliente);

        verify(clienteRepository, times(1)).save(cliente);
        assertEquals(updatedCliente.getId(), cliente.getId());
    }

    @Test
    void updateFailWhenClienteNotFound() {
        Cliente cliente1 = Cliente.builder().id(1L).build();
        Cliente cliente2 = Cliente.builder().build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ClienteNotFound clienteNotFound = assertThrows(ClienteNotFound.class,
                () -> clienteService.update(cliente1.getId(), cliente2));

        verify(clienteRepository, times(0)).save(cliente1);
        assertEquals("Cliente não encontrado.", clienteNotFound.getMessage());
    }

    @Test
    void deleteClienteSuccess() {
        Cliente cliente = Cliente.builder().id(1L).ativo(true).build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        clienteService.deleteCliente(1L);

        verify(clienteRepository, times(1)).save(cliente);
        assertFalse(cliente.isAtivo());
    }

    @Test
    void deleteClienteFailWhenClienteNotFound() {
        Cliente cliente = Cliente.builder().id(1L).ativo(false).build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ConflictException conflictException = assertThrows(ConflictException.class,
                () -> clienteService.deleteCliente(cliente.getId()));

        verify(clienteRepository, times(0)).save(cliente);
        assertFalse(cliente.isAtivo());
        assertEquals("Cliente desativado.", conflictException.getMessage());

    }

    @Test
    void deleteClienteFailWhenClienteIsAlreadyDeactivated() {
        Cliente cliente = Cliente.builder().id(1L).ativo(true).build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ClienteNotFound clienteNotFound = assertThrows(ClienteNotFound.class,
                () -> clienteService.deleteCliente(cliente.getId()));

        verify(clienteRepository, times(0)).save(cliente);
        assertEquals("Cliente não encontrado.", clienteNotFound.getMessage());
    }
}