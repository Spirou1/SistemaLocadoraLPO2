/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.suaempresa.locadora.ui;

import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.GerenciadorClientes;
import com.suaempresa.locadora.model.GerenciadorVeiculos;
import com.suaempresa.locadora.ui.tables.ClienteTableModel;
import java.awt.Component;
import java.util.List;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author enzo
 */
public class ClientesPanel extends javax.swing.JPanel {

    private GerenciadorClientes gerenciadorClientes; 
    private GerenciadorVeiculos gerenciadorVeiculos;
    private ClienteTableModel clienteTableModel;

    
    private javax.swing.JPanel panelFormulario;       
    private javax.swing.JPanel panelNomeSobrenome;    
    private javax.swing.JPanel panelRgCpf;            
    private javax.swing.JPanel panelEndereco;        
    private javax.swing.JPanel panelBotoes;
    /**
     * Creates new form ClientesPanel
     */
    public ClientesPanel(GerenciadorClientes gc, GerenciadorVeiculos gv) {
        this.gerenciadorClientes = gc;
        this.gerenciadorVeiculos = gv;
        initComponents();
        this.setBorder(new EmptyBorder(30, 50, 30, 50));
        

        
        this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        this.removeAll(); 

        this.add(Box.createVerticalStrut(20)); 

        
        if (jLabel1 != null) { 
            jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT); 
            this.add(jLabel1);
        }
        this.add(Box.createVerticalStrut(5)); 

        if (jLabel2 != null) { 
            jLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(jLabel2);
        }
        this.add(Box.createVerticalStrut(30)); 

        

      
        panelNomeSobrenome = new JPanel(); 
       
        panelNomeSobrenome.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        panelNomeSobrenome.setOpaque(false); 
        panelNomeSobrenome.add(createFieldPanel(jLabel3, txtNome));
        panelNomeSobrenome.add(createFieldPanel(jLabel4, txtSobrenome)); 
        panelNomeSobrenome.setAlignmentX(Component.CENTER_ALIGNMENT); 
        this.add(panelNomeSobrenome);
        this.add(Box.createVerticalStrut(10)); 

        
        panelRgCpf = new JPanel(); 
        panelRgCpf.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        panelRgCpf.setOpaque(false);
        panelRgCpf.add(createFieldPanel(jLabel5, txtRG)); 
        panelRgCpf.add(createFieldPanel(jLabel6, txtCPF)); 
        panelRgCpf.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelRgCpf);
        this.add(Box.createVerticalStrut(10));

        
        panelEndereco = new JPanel(); 
        panelEndereco.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        panelEndereco.setOpaque(false);
        panelEndereco.add(createFieldPanel(jLabel7, txtEndereco)); 
        panelEndereco.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelEndereco);
        this.add(Box.createVerticalStrut(20));

        
        panelBotoes = new JPanel(); 
        panelBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 0)); 
        panelBotoes.setOpaque(false);

        if (btnIncluir != null) panelBotoes.add(btnIncluir);
        if (btnAtualizar != null) panelBotoes.add(btnAtualizar);
        if (btnExcluir != null) panelBotoes.add(btnExcluir);

        panelBotoes.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelBotoes);
        this.add(Box.createVerticalStrut(50));

       
        if (jLabel8 != null) { 
            jLabel8.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(jLabel8);
        }
        this.add(Box.createVerticalStrut(10));

        if (jScrollPane2 != null) { 
            jScrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT); 
            jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 250)); 
            this.add(jScrollPane2);
        }
        this.add(Box.createVerticalStrut(50));

        this.add(Box.createVerticalGlue());

        this.revalidate(); 
        this.repaint();    

        carregarTabelaClientes();

        
        jTableClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTableClientes.getSelectedRow() != -1) {
                int selectedRow = jTableClientes.getSelectedRow();
                int modelRow = jTableClientes.convertRowIndexToModel(selectedRow);
                Cliente cliente = clienteTableModel.getClienteAt(modelRow);
                if (cliente != null) {
                    txtNome.setText(cliente.getNome());
                    txtSobrenome.setText(cliente.getSobrenome());
                    txtRG.setText(cliente.getRg());
                    txtCPF.setText(cliente.getCpf());
                    txtEndereco.setText(cliente.getEndereco());
                    txtCPF.setEnabled(false);
                }
            } else {
                limparCamposCliente();
                txtCPF.setEnabled(true);
            }
        });
    }
    
    private JPanel createFieldPanel(JLabel label, javax.swing.JTextField textField) {
        JPanel pairPanel = new JPanel();
        pairPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0)); //
        pairPanel.setOpaque(false); 

        if (label != null) {
            pairPanel.add(label);
        }
        if (textField != null) {
            textField.setPreferredSize(new java.awt.Dimension(200, 30)); //
            pairPanel.add(textField);
        }
        return pairPanel;
    }
    
    public void carregarTabelaClientes() {
        List<Cliente> clientes = gerenciadorClientes.listarTodosClientes();
        
        if (clienteTableModel == null) {
            clienteTableModel = new ClienteTableModel(clientes);
            jTableClientes.setModel(clienteTableModel);
        } else {
            clienteTableModel.setClientes(clientes);
        }
        jTableClientes.setAutoCreateRowSorter(true);
    }
    
    private void limparCamposCliente() {
    txtNome.setText("");
    txtSobrenome.setText("");
    txtRG.setText("");
    txtCPF.setText("");
    txtEndereco.setText("");
    txtCPF.setEnabled(true);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        txtNome = new javax.swing.JTextField();
        txtSobrenome = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        txtRG = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        btnIncluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 238, 230));
        setPreferredSize(new java.awt.Dimension(843, 613));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jTableClientes.setBackground(new java.awt.Color(14, 20, 30));
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
        jTableClientes.setRowHeight(35);
        jTableClientes.setSelectionBackground(new java.awt.Color(14, 20, 30));
        jTableClientes.setSelectionForeground(new java.awt.Color(153, 255, 255));
        jScrollPane2.setViewportView(jTableClientes);

        add(jScrollPane2);

        txtNome.setBackground(new java.awt.Color(14, 20, 30));
        txtNome.setForeground(new java.awt.Color(255, 255, 255));
        txtNome.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtNome);

        txtSobrenome.setBackground(new java.awt.Color(14, 20, 30));
        txtSobrenome.setForeground(new java.awt.Color(255, 255, 255));
        txtSobrenome.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtSobrenome);

        txtCPF.setBackground(new java.awt.Color(14, 20, 30));
        txtCPF.setForeground(new java.awt.Color(255, 255, 255));
        txtCPF.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtCPF);

        txtRG.setBackground(new java.awt.Color(14, 20, 30));
        txtRG.setForeground(new java.awt.Color(255, 255, 255));
        txtRG.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtRG);

        txtEndereco.setBackground(new java.awt.Color(14, 20, 30));
        txtEndereco.setForeground(new java.awt.Color(255, 255, 255));
        txtEndereco.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtEndereco);

        btnIncluir.setBackground(new java.awt.Color(14, 20, 30));
        btnIncluir.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnIncluir.setForeground(new java.awt.Color(255, 255, 255));
        btnIncluir.setText("Incluir");
        btnIncluir.setPreferredSize(new java.awt.Dimension(120, 50));
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        add(btnIncluir);

        btnAtualizar.setBackground(new java.awt.Color(14, 20, 30));
        btnAtualizar.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setPreferredSize(new java.awt.Dimension(120, 50));
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });
        add(btnAtualizar);

        btnExcluir.setBackground(new java.awt.Color(14, 20, 30));
        btnExcluir.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("Excluir");
        btnExcluir.setPreferredSize(new java.awt.Dimension(120, 50));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        add(btnExcluir);

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Sistema Locadora");
        add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Painel Clientes");
        add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nome:");
        add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Sobrenome:");
        add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("RG:");
        add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("CPF:");
        add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Endereco:");
        add(jLabel7);

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Visualizar Clientes:");
        add(jLabel8);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        // TODO add your handling code here:
        String nome = txtNome.getText(); 
    String sobrenome = txtSobrenome.getText();
    String rg = txtRG.getText();
    String cpf = txtCPF.getText();
    String endereco = txtEndereco.getText();

    if (nome.isEmpty() || nome.equals("jTextField1") ||
        sobrenome.isEmpty() || sobrenome.equals("jTextField1") ||
        rg.isEmpty() || rg.equals("jTextField1") ||
        cpf.isEmpty() || cpf.equals("jTextField1") ||
        endereco.isEmpty() || endereco.equals("jTextField1")) {
        JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos corretamente.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Cliente novoCliente = new Cliente(nome, sobrenome, rg, cpf, endereco);
    gerenciadorClientes.adicionarCliente(novoCliente);
    JOptionPane.showMessageDialog(this, "Cliente " + nome + " cadastrado com sucesso!");
    limparCamposCliente(); 
    carregarTabelaClientes(); 
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // TODO add your handling code here:
        String cpf = txtCPF.getText(); 

    
    if (cpf.isEmpty() || txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() ||
        txtRG.getText().isEmpty() || txtEndereco.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela e preencha todos os campos para atualizar.", "Erro de Atualização", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Cliente clienteAtualizado = new Cliente(txtNome.getText(), txtSobrenome.getText(), txtRG.getText(), cpf, txtEndereco.getText());

    gerenciadorClientes.atualizarCliente(clienteAtualizado);
    JOptionPane.showMessageDialog(this, "Cliente " + clienteAtualizado.getNome() + " atualizado com sucesso!");
    limparCamposCliente();
    txtCPF.setEnabled(true); 
    carregarTabelaClientes();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTableClientes.getSelectedRow(); 
    if (selectedRow >= 0) { 
        
        int modelRow = jTableClientes.convertRowIndexToModel(selectedRow);
        Cliente clienteParaExcluir = clienteTableModel.getClienteAt(modelRow);

        if (clienteParaExcluir != null) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir o cliente " + clienteParaExcluir.getNome() + " " + clienteParaExcluir.getSobrenome() + "?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                
                boolean excluido = gerenciadorClientes.excluirCliente(clienteParaExcluir.getCpf(), gerenciadorVeiculos);
                if (excluido) {
                    JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                    limparCamposCliente(); 
                    txtCPF.setEnabled(true); 
                    carregarTabelaClientes();
                } 
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnExcluirActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRG;
    private javax.swing.JTextField txtSobrenome;
    // End of variables declaration//GEN-END:variables
}
