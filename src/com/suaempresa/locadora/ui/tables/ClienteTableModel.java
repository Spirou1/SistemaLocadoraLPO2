package com.suaempresa.locadora.ui.tables; 

import com.suaempresa.locadora.model.Cliente; 
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {

    private List<Cliente> clientes;
    private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Endereço"}; 

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = new ArrayList<>(clientes); 
    }

    
    public void setClientes(List<Cliente> clientes) {
        this.clientes = new ArrayList<>(clientes);
        fireTableDataChanged(); 
    }

    @Override
    public int getRowCount() {
        return clientes.size(); 
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column]; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex); 

        switch (columnIndex) {
            case 0: return cliente.getNome();
            case 1: return cliente.getSobrenome();
            case 2: return cliente.getRg();
            case 3: return cliente.getCpf();
            case 4: return cliente.getEndereco();
            default: return null; 
        }
    }

   
    public Cliente getClienteAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < clientes.size()) {
            return clientes.get(rowIndex);
        }
        return null;
    }
    
    public void addRow(Cliente c) {
        this.clientes.add(c);
    }

}