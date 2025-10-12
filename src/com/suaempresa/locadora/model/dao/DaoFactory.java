package com.suaempresa.locadora.model.dao;

import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.Veiculo;

public abstract class DaoFactory {
    
    public abstract ClienteDAO getClienteDAO();
    public abstract VeiculoDAO getVeiculoDAO();
    
    public static DaoFactory getDaoFactory(DaoType type) {
        if (type == DaoType.SQL) {
            return DaoFactorySQL.getInstance(); 
        } 
        throw new IllegalArgumentException("Tipo de DAO não suportado: " + type);
    }
}