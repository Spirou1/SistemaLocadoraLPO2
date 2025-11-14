/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.suaempresa.locadora.view;

import com.suaempresa.locadora.controller.LocacaoController;
import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.Veiculo;
import com.suaempresa.locadora.model.Estado; 
import com.suaempresa.locadora.ui.tables.ClienteTableModel;
import com.suaempresa.locadora.ui.tables.VeiculoTableModel;
import java.util.Calendar; 
import java.util.Collections; 
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Component; 
import javax.swing.Box; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author enzo
 */
public class VeiculoLocacaoPanel extends javax.swing.JPanel {

    private ClienteTableModel clienteTableModel;
    private VeiculoTableModel veiculoTableModel;
    private LocacaoController locacaoController;

    private Cliente clienteSelecionado;
    private Veiculo veiculoSelecionado;
    
    private javax.swing.JPanel panelBuscaCliente; 
    private javax.swing.JPanel panelBuscaVeiculo; 
    private javax.swing.JPanel panelLocacaoCampos;
    
    
    
    /**
     * Creates new form VeiculoLocacaoPanel
     */
    public VeiculoLocacaoPanel() {
        
        initComponents(); 
        this.clienteTableModel = new ClienteTableModel(new ArrayList<>());
        this.veiculoTableModel = new VeiculoTableModel(new ArrayList<>(), true);
        this.locacaoController = new LocacaoController(this);
        this.locacaoController.initController();
        this.setBorder(new EmptyBorder(30, 50, 30, 50));

        
        this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS)); 
        this.removeAll(); 

        this.add(Box.createVerticalStrut(20)); 

        
        if (jLabel3 != null) { 
            jLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(jLabel3);
        }
        this.add(Box.createVerticalStrut(5));

        if (jLabel2 != null) { 
            jLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(jLabel2);
        }
        this.add(Box.createVerticalStrut(30)); 

        
        panelBuscaCliente = new JPanel(); 
        panelBuscaCliente.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5)); 
        panelBuscaCliente.setOpaque(false);
        if (jLabel1 != null) panelBuscaCliente.add(jLabel1); 
        if (txtCPFCliente != null) {
            txtCPFCliente.setPreferredSize(new java.awt.Dimension(200, 30)); 
            panelBuscaCliente.add(txtCPFCliente);
        }
        if (btnBuscarCliente != null) panelBuscaCliente.add(btnBuscarCliente);
        panelBuscaCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelBuscaCliente);
        this.add(Box.createVerticalStrut(10)); 

        // Tabela de Clientes
        if (jScrollPane1 != null) { 
            jScrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT); 
            jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 150));
            this.add(jScrollPane1);
        }
        this.add(Box.createVerticalStrut(20)); 

        
        panelBuscaVeiculo = new JPanel(); 
        panelBuscaVeiculo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5)); 
        panelBuscaVeiculo.setOpaque(false);
        if (jLabel5 != null) panelBuscaVeiculo.add(jLabel5); 
        if (txtPlaca != null) {
            txtPlaca.setPreferredSize(new java.awt.Dimension(200, 30)); 
            panelBuscaVeiculo.add(txtPlaca);
        }
        if (btnBuscarVeiculo != null) panelBuscaVeiculo.add(btnBuscarVeiculo);
        panelBuscaVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelBuscaVeiculo);
        this.add(Box.createVerticalStrut(10));

        
        if (jScrollPane2 != null) { 
            jScrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT); 
            jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 150)); 
            this.add(jScrollPane2);
        }
        this.add(Box.createVerticalStrut(20));

        
        panelLocacaoCampos = new JPanel(); 
        panelLocacaoCampos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5)); 
        panelLocacaoCampos.setOpaque(false);
        if (jLabel4 != null) panelLocacaoCampos.add(jLabel4); 
        if (txtDiasLocacao != null) {
            txtDiasLocacao.setPreferredSize(new java.awt.Dimension(100, 30)); 
            panelLocacaoCampos.add(txtDiasLocacao);
        }
        if (btnLocarVeiculo != null) panelLocacaoCampos.add(btnLocarVeiculo);
        panelLocacaoCampos.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelLocacaoCampos);
        this.add(Box.createVerticalStrut(20));


        this.add(Box.createVerticalGlue()); 

        this.revalidate(); 
        this.repaint();    

        
      
        jTableClientes.setModel(clienteTableModel);
        jTableClientes.setAutoCreateRowSorter(true);

      
        jTableVeiculos.setModel(veiculoTableModel);
        jTableVeiculos.setAutoCreateRowSorter(true);
        addTableSelectionListeners();
      
    }
    
    
    
    private void addTableSelectionListeners() {
        jTableClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && jTableClientes.getSelectedRow() != -1) {
                    int selectedRow = jTableClientes.getSelectedRow();
                    int modelRow = jTableClientes.convertRowIndexToModel(selectedRow); 
                    clienteSelecionado = clienteTableModel.getClienteAt(modelRow); 
                    
                    
                } else {
                    clienteSelecionado = null; 
                    
                }
            }
        });

        jTableVeiculos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && jTableVeiculos.getSelectedRow() != -1) {
                    int selectedRow = jTableVeiculos.getSelectedRow();
                    int modelRow = jTableVeiculos.convertRowIndexToModel(selectedRow); 
                    veiculoSelecionado = veiculoTableModel.getVeiculoAt(modelRow); 
                    
                } else {
                    veiculoSelecionado = null; 
                    
                }
            }
        });
    }
    
    public void setClientesTableData(List<Cliente> clientes) {
        clienteTableModel.setClientes(clientes);
    }
    
    public void setVeiculosTableData(List<Veiculo> veiculos) {
        veiculoTableModel.setVeiculos(veiculos);
    }
    
    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
    
    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }
    
    public javax.swing.JButton getBtnBuscarCliente() {
        return btnBuscarCliente;
    }
    
    public javax.swing.JButton getBtnBuscarVeiculo() {
        return btnBuscarVeiculo;
    }
    
    public String getCpfCliente() {
        return txtCPFCliente.getText();
    }
    
    public String getPlacaVeiculo() {
        return txtPlaca.getText();
    }
    
    public int getDiasLocacao() {
        try {
            return Integer.parseInt(txtDiasLocacao.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public javax.swing.JButton getBtnLocar() {
        return btnLocarVeiculo;
    }
    
    public void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void limparCampos() {
        txtCPFCliente.setText("");
        txtPlaca.setText("");
        txtDiasLocacao.setText("");
        clienteSelecionado = null;
        veiculoSelecionado = null;
        jTableClientes.clearSelection();
        jTableVeiculos.clearSelection();
        clienteTableModel.setClientes(new ArrayList<>()); // Clear table
        veiculoTableModel.setVeiculos(new ArrayList<>()); // Clear table
    }
    
    public void refreshTable() {
        if (locacaoController != null) {
            locacaoController.loadInitialData();
        }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLocarVeiculo = new javax.swing.JButton();
        txtDiasLocacao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVeiculos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        btnBuscarCliente = new javax.swing.JButton();
        btnBuscarVeiculo = new javax.swing.JButton();
        txtPlaca = new javax.swing.JTextField();
        txtCPFCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        btnLocarVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnLocarVeiculo.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnLocarVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnLocarVeiculo.setText("Locar Veículo");
        btnLocarVeiculo.setPreferredSize(new java.awt.Dimension(200, 50));
        btnLocarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocarVeiculoActionPerformed(evt);
            }
        });

        txtDiasLocacao.setBackground(new java.awt.Color(14, 20, 30));
        txtDiasLocacao.setForeground(new java.awt.Color(255, 255, 255));
        txtDiasLocacao.setPreferredSize(new java.awt.Dimension(70, 40));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Placa Veículo:");

        jScrollPane2.setBackground(new java.awt.Color(14, 20, 30));

        jTableVeiculos.setBackground(new java.awt.Color(14, 20, 30));
        jTableVeiculos.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jTableVeiculos.setForeground(new java.awt.Color(255, 255, 255));
        jTableVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableVeiculos.setGridColor(new java.awt.Color(14, 20, 30));
        jTableVeiculos.setRowHeight(35);
        jScrollPane2.setViewportView(jTableVeiculos);

        jScrollPane1.setBackground(new java.awt.Color(14, 20, 30));

        jTableClientes.setBackground(new java.awt.Color(14, 20, 30));
        jTableClientes.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jTableClientes.setForeground(new java.awt.Color(255, 255, 255));
        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClientes.setGridColor(new java.awt.Color(14, 20, 30));
        jTableClientes.setRowHeight(35);
        jScrollPane1.setViewportView(jTableClientes);

        btnBuscarCliente.setBackground(new java.awt.Color(14, 20, 30));
        btnBuscarCliente.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCliente.setText("Buscar Cliente");
        btnBuscarCliente.setPreferredSize(new java.awt.Dimension(200, 50));

        btnBuscarVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnBuscarVeiculo.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnBuscarVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarVeiculo.setText("Buscar Veículo");
        btnBuscarVeiculo.setPreferredSize(new java.awt.Dimension(200, 50));
        btnBuscarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVeiculoActionPerformed(evt);
            }
        });

        txtPlaca.setBackground(new java.awt.Color(14, 20, 30));
        txtPlaca.setForeground(new java.awt.Color(255, 255, 255));
        txtPlaca.setPreferredSize(new java.awt.Dimension(70, 40));

        txtCPFCliente.setBackground(new java.awt.Color(14, 20, 30));
        txtCPFCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtCPFCliente.setPreferredSize(new java.awt.Dimension(70, 40));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dias de Locação:");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Sistema Locadora");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Locacao Veículo");

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("CPF Cliente:");

        setBackground(new java.awt.Color(240, 238, 230));
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        if (locacaoController != null) {
            locacaoController.buscarCliente();
        }
    }                                                

    private void btnBuscarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVeiculoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnBuscarVeiculoActionPerformed

    private void btnLocarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocarVeiculoActionPerformed
        
    }//GEN-LAST:event_btnLocarVeiculoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarVeiculo;
    private javax.swing.JButton btnLocarVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableVeiculos;
    private javax.swing.JTextField txtCPFCliente;
    private javax.swing.JTextField txtDiasLocacao;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
