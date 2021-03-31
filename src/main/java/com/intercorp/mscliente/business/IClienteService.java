package com.intercorp.mscliente.business;

import com.intercorp.mscliente.model.entity.Cliente;
import com.intercorp.mscliente.web.ClienteDto;

public interface IClienteService {

    public void crearCliente(ClienteDto cliente);

    public KPICliente kpiClientes();

    public Iterable<Cliente> listClientes();

}
