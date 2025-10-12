package com.suaempresa.locadora.model.dao;

import com.suaempresa.locadora.model.Cliente;

public interface ClienteDAO extends Dao<Cliente> {
    
    Cliente getByCpf(String cpf);
}