package com.guerra.apiclientes.controller;

import com.guerra.apiclientes.model.Cliente;
import com.guerra.apiclientes.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    Logger log = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente findOne(@PathVariable Long id) {

        log.info("ClienteController: Cliente ID {} encontrado!", id);
        return clienteService.findOne(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody Cliente cliente) {

        log.info("ClienteController: Cliente ID {} criado com sucesso!", cliente.getId());
        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {

        log.info("ClienteController: Cliente ID {} atualizado com sucesso!", id);
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) {

        log.info("ClienteController: Cliente ID {} deletado com sucesso!", id);
        clienteService.deleteCliente(id);
    }
}
