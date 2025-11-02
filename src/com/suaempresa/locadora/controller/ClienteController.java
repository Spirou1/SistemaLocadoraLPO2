/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.controller;

import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.dao.ClienteDAO;
import com.suaempresa.locadora.model.dao.DaoFactory;
import com.suaempresa.locadora.model.dao.DaoType;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = DaoFactory.getDaoFactory(DaoType.SQL).getClienteDAO();
    }

    public boolean incluirCliente(Cliente cliente) {
        try {
            if (cliente == null || cliente.getNome() == null || cliente.getNome().isEmpty()) {
                System.err.println("Cliente ou nome do cliente inválido.");
                return false;
            }
            clienteDAO.insert(cliente);
            System.out.println("Cliente " + cliente.getNome() + " incluído com sucesso!");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao incluir cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public java.util.List<Cliente> getAllClientes() {
        try {
            return clienteDAO.getAll();
        } catch (Exception e) {
            System.err.println("Erro ao buscar clientes: " + e.getMessage());
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}
