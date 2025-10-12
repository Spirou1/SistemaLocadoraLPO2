package com.suaempresa.locadora.model.dao;


public class DaoFactorySQL extends DaoFactory {

    private static DaoFactorySQL instance;
    private ClienteDAO clienteDAO;
    private VeiculoDAO veiculoDAO;
    

  
    private DaoFactorySQL() {
       
        this.clienteDAO = new ClienteDAOSQL();
        this.veiculoDAO = new VeiculoDAOSQL();
       
    }

   
    public static synchronized DaoFactorySQL getInstance() {
        if (instance == null) {
            instance = new DaoFactorySQL();
        }
        return instance;
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    @Override
    public VeiculoDAO getVeiculoDAO() {
        return veiculoDAO;
    }
    
}