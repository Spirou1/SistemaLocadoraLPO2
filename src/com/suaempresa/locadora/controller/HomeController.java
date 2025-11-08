/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.controller;

import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.Estado;
import com.suaempresa.locadora.model.Veiculo;
import com.suaempresa.locadora.model.dao.ClienteDAO;
import com.suaempresa.locadora.model.dao.DaoFactory;
import com.suaempresa.locadora.model.dao.DaoType;
import com.suaempresa.locadora.model.dao.VeiculoDAO;
import com.suaempresa.locadora.view.HomePanel;
import java.util.List;

/**
 *
 * @author enzo
 */
public class HomeController {
    private final HomePanel view;
    private final ClienteDAO clienteDAO;
    private final VeiculoDAO veiculoDAO;
    
    public HomeController(HomePanel view) {
        this.view = view;
        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoType.SQL);
        this.clienteDAO = daoFactory.getClienteDAO();
        this.veiculoDAO = daoFactory.getVeiculoDAO();
    }
    
    public void loadData() {
        try {
            List<Cliente> clientes = clienteDAO.getAll();
            List<Veiculo> veiculos = veiculoDAO.getAll();
            
            int totalClientes = clientes.size();
            int totalVeiculos = veiculos.size();
            long veiculosDisponiveis = veiculos.stream().filter(v -> v.getEstado() == Estado.DISPONIVEL).count();
            long veiculosLocados = veiculos.stream().filter(v -> v.getEstado() == Estado.LOCADO).count();
            
            view.setTotalClientes(totalClientes);
            view.setTotalVeiculos(totalVeiculos);
            view.setVeiculosDisponiveis(veiculosDisponiveis);
            view.setVeiculosLocados(veiculosLocados);
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados para a home panel");
            e.printStackTrace();
        }
    }
    
    
}
