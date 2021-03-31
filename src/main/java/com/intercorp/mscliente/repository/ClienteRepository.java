package com.intercorp.mscliente.repository;

import com.intercorp.mscliente.model.entity.Cliente;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
