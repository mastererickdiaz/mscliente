package com.intercorp.mscliente.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import com.intercorp.mscliente.model.entity.Cliente;
import com.intercorp.mscliente.repository.ClienteRepository;
import com.intercorp.mscliente.util.Utils;
import com.intercorp.mscliente.web.ClienteDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public void crearCliente(ClienteDto cliente) {
        Cliente entity = new Cliente();
        entity.setNombre(cliente.getNombre());
        entity.setApellido(cliente.getApellido());
        entity.setEdad(cliente.getEdad());
        entity.setFechaNacimiento(new java.sql.Date(cliente.getFechaNacimiento().getTime()));
        clienteRepository.save(entity);
    }

    @Override
    public KPICliente kpiClientes() {
        KPICliente kpi = new KPICliente();
        List<Cliente> list = new ArrayList<>();
        Iterable<Cliente> clientes = clienteRepository.findAll();
        clientes.forEach(list::add);
        Integer count = list.size();
        int v[] = new int[count];

        int i = 0;        
        for (Cliente c : list) {
            v[i] = c.getEdad();
            i++;
        }

        double prom = Utils.promedio(v);
        kpi.setPromedioEdad(prom);
        double desv = Utils.desviacion(v);
        kpi.setDesviacionEstandar(desv);
        return kpi;
    }

    @Override
    public Iterable<Cliente> listClientes() {
        Iterable<Cliente> entities = clienteRepository.findAll();
        for (Cliente cliente : entities) {
            Calendar c = Calendar.getInstance();
            c.setTime(cliente.getFechaNacimiento());

            c.add(Calendar.YEAR, Utils.generateRandomIntIntRange(40, 60));
            c.add(Calendar.MONTH, Utils.generateRandomIntIntRange(36, 108));
            c.add(Calendar.DATE, Utils.generateRandomIntIntRange(360, 3240));

            cliente.setFechaMuerte(c.getTime());
        }
        clienteRepository.saveAll(entities);
        return entities;
    }

}
