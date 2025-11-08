/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.controller;

import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.dao.ClienteDAO;
import com.suaempresa.locadora.model.dao.DaoFactory;
import com.suaempresa.locadora.model.dao.DaoType;
import com.suaempresa.locadora.view.ClientesPanel;

public class ClienteController {

    private final ClientesPanel view;
    private final ClienteDAO clienteDAO;

    public ClienteController(ClientesPanel view) {
        this.view = view;
        this.clienteDAO = DaoFactory.getDaoFactory(DaoType.SQL).getClienteDAO();
    }

    public void initController() {
        view.getBtnIncluir().addActionListener(e -> incluirCliente());
        view.getBtnAtualizar().addActionListener(e -> atualizarCliente());
        view.getBtnExcluir().addActionListener(e -> excluirCliente());
        refreshTable();
    }

    private void incluirCliente() {
        try {
            Cliente cliente = view.getClienteFromForm();
            if (cliente.getNome().isEmpty() || cliente.getSobrenome().isEmpty() || cliente.getRg().isEmpty() || cliente.getCpf().isEmpty() || cliente.getEndereco().isEmpty()) {
                view.showWarningMessage("Todos os campos devem ser preenchidos.");
                return;
            }
            
            if (clienteDAO.getByCpf(cliente.getCpf()) != null) {
                view.showErrorMessage("Já existe um cliente com este CPF.");
                return;
            }

            clienteDAO.insert(cliente);
            view.showSuccessMessage("Cliente incluído com sucesso!");
            view.limparCamposCliente();
            refreshTable();
        } catch (Exception e) {
            view.showErrorMessage("Erro ao incluir cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void atualizarCliente() {
        try {
            Cliente cliente = view.getClienteFromForm();
             if (cliente.getCpf().isEmpty()) {
                view.showWarningMessage("Selecione um cliente na tabela para atualizar.");
                return;
            }
            
            clienteDAO.update(cliente);
            view.showSuccessMessage("Cliente atualizado com sucesso!");
            view.limparCamposCliente();
            refreshTable();
        } catch (Exception e) {
            view.showErrorMessage("Erro ao atualizar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void excluirCliente() {
        try {
            Cliente cliente = view.getClienteFromForm();
            if (cliente.getCpf().isEmpty()) {
                view.showWarningMessage("Selecione um cliente na tabela para excluir.");
                return;
            }
            
            int response = view.showConfirmDialog("Tem certeza que deseja excluir o cliente " + cliente.getNome() + "?", "Confirmação de Exclusão");
            if (response == javax.swing.JOptionPane.YES_OPTION) {
                clienteDAO.delete(cliente);
                view.showSuccessMessage("Cliente excluído com sucesso!");
                view.limparCamposCliente();
                refreshTable();
            }
        } catch (Exception e) {
            view.showErrorMessage("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        try {
            java.util.List<Cliente> clientes = clienteDAO.getAll();
            view.getClienteTableModel().setClientes(clientes);
        } catch (Exception e) {
            view.showErrorMessage("Erro ao carregar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
