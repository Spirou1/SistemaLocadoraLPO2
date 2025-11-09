/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.suaempresa.locadora.view;



import com.suaempresa.locadora.controller.VeiculoController;
import com.suaempresa.locadora.model.Veiculo;
import com.suaempresa.locadora.model.Marca;
import com.suaempresa.locadora.model.Categoria;
import com.suaempresa.locadora.ui.tables.VeiculoTableModel; 
import javax.swing.DefaultComboBoxModel; 
import javax.swing.JOptionPane;
import javax.swing.JScrollPane; 
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.NumberFormat; 
import java.util.Locale; 
import java.util.List;
import com.suaempresa.locadora.model.Estado;
import com.suaempresa.locadora.model.TipoVeiculo;


import java.awt.Component; 
import javax.swing.Box; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.JComboBox; 
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author enzo
 */
public class VeiculoVendaPanel extends javax.swing.JPanel {

   
    private VeiculoTableModel veiculoTableModel;
    private VeiculoController veiculoController;
    private Veiculo veiculoSelecionado;
    /**
     * Creates new form VeiculoVendaPanel
     */
    public VeiculoVendaPanel() {
        
        initComponents(); 
        this.veiculoTableModel = new VeiculoTableModel(new ArrayList<>(), false);
        this.veiculoController = new VeiculoController(); // Initialize the controller
        this.setBorder(new EmptyBorder(30, 50, 30, 50));

        // Populate combo boxes
        cmbMarcaFiltro.setModel(new DefaultComboBoxModel<>(Marca.values()));
        cmbCategoriaFiltro.setModel(new DefaultComboBoxModel<>(Categoria.values()));
        cmbTipoVeiculoFiltro.setModel(new DefaultComboBoxModel<>(TipoVeiculo.values()));

        
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

        
        
        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10)); 
        panelFiltros.setOpaque(false);

        panelFiltros.add(createComboBoxFilterPair(jLabel5, cmbTipoVeiculoFiltro)); 
        panelFiltros.add(createComboBoxFilterPair(jLabel3, cmbMarcaFiltro)); 
        panelFiltros.add(createComboBoxFilterPair(jLabel6, cmbCategoriaFiltro)); 

        panelFiltros.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelFiltros);
        this.add(Box.createVerticalStrut(20)); 

        
        if (btnFiltrarVeiculos != null) {
            btnFiltrarVeiculos.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnFiltrarVeiculos.setPreferredSize(new java.awt.Dimension(150, 35)); 
            this.add(btnFiltrarVeiculos);
        }
        this.add(Box.createVerticalStrut(30)); 

       
        if (jLabel4 != null) { 
            jLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(jLabel4);
        }
        this.add(Box.createVerticalStrut(10)); 

        if (jScrollPane1 != null) { 
            jScrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
            jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 250)); 
            this.add(jScrollPane1);
        }
        this.add(Box.createVerticalStrut(30));

        
        if (btnVenderVeiculo != null) {
            btnVenderVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnVenderVeiculo.setPreferredSize(new java.awt.Dimension(200, 40)); 
            this.add(btnVenderVeiculo);
        }
        this.add(Box.createVerticalStrut(20)); 

        this.add(Box.createVerticalGlue());

        this.revalidate();
        this.repaint();   

        
        
       
        jTableVeiculosParaVenda.setModel(veiculoTableModel);
        jTableVeiculosParaVenda.setAutoCreateRowSorter(true); 
        addTableSelectionListener(); 
      
    }
    
    private JPanel createComboBoxFilterPair(JLabel label, javax.swing.JComboBox<?> comboBox) {
        JPanel pairPanel = new JPanel();
        pairPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0)); 
        pairPanel.setOpaque(false); 

        if (label != null) {
            pairPanel.add(label);
        }
        if (comboBox != null) {
            comboBox.setPreferredSize(new java.awt.Dimension(150, 30)); 
            pairPanel.add(comboBox);
        }
        return pairPanel;
    }
    
    
    
    

    private void addTableSelectionListener() {
    jTableVeiculosParaVenda.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting() && jTableVeiculosParaVenda.getSelectedRow() != -1) {
                int selectedRow = jTableVeiculosParaVenda.getSelectedRow();
                int modelRow = jTableVeiculosParaVenda.convertRowIndexToModel(selectedRow);
                veiculoSelecionado = veiculoTableModel.getVeiculoAt(modelRow);
                System.out.println("DEBUG: Veículo selecionado para venda: " + veiculoSelecionado.getPlaca());
            } else {
                veiculoSelecionado = null;
            }
        }
    });
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVeiculosParaVenda = new javax.swing.JTable();
        btnFiltrarVeiculos = new javax.swing.JButton();
        cmbCategoriaFiltro = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmbMarcaFiltro = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoVeiculoFiltro = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnVenderVeiculo = new javax.swing.JButton();

        jTableVeiculosParaVenda.setBackground(new java.awt.Color(14, 20, 30));
        jTableVeiculosParaVenda.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jTableVeiculosParaVenda.setForeground(new java.awt.Color(255, 255, 255));
        jTableVeiculosParaVenda.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableVeiculosParaVenda.setRowHeight(35);
        jScrollPane1.setViewportView(jTableVeiculosParaVenda);

        btnFiltrarVeiculos.setBackground(new java.awt.Color(14, 20, 30));
        btnFiltrarVeiculos.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnFiltrarVeiculos.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrarVeiculos.setText("Filtrar Veículos");
        btnFiltrarVeiculos.setPreferredSize(new java.awt.Dimension(120, 50));
        btnFiltrarVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarVeiculosActionPerformed(evt);
            }
        });

        cmbCategoriaFiltro.setBackground(new java.awt.Color(14, 20, 30));
        cmbCategoriaFiltro.setForeground(new java.awt.Color(255, 255, 255));
        cmbCategoriaFiltro.setPreferredSize(new java.awt.Dimension(80, 40));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Categoria:");

        cmbMarcaFiltro.setBackground(new java.awt.Color(14, 20, 30));
        cmbMarcaFiltro.setForeground(new java.awt.Color(255, 255, 255));
        cmbMarcaFiltro.setPreferredSize(new java.awt.Dimension(80, 40));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tipo:");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Veículos para venda:");

        cmbTipoVeiculoFiltro.setBackground(new java.awt.Color(14, 20, 30));
        cmbTipoVeiculoFiltro.setForeground(new java.awt.Color(255, 255, 255));
        cmbTipoVeiculoFiltro.setMinimumSize(new java.awt.Dimension(80, 40));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Marca:");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Vender Veículo");

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Sistema Locadora");

        btnVenderVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnVenderVeiculo.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnVenderVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnVenderVeiculo.setText("Vender Veículo");
        btnVenderVeiculo.setPreferredSize(new java.awt.Dimension(120, 50));
        btnVenderVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderVeiculoActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(240, 238, 230));
        setPreferredSize(new java.awt.Dimension(843, 613));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarVeiculosActionPerformed
        try {
            TipoVeiculo tipo = (TipoVeiculo) cmbTipoVeiculoFiltro.getSelectedItem();
            Marca marca = (Marca) cmbMarcaFiltro.getSelectedItem();
            Categoria categoria = (Categoria) cmbCategoriaFiltro.getSelectedItem();
            
            // Pass null if "Todos" or no selection is desired for a filter
            String tipoStr = (tipo != null) ? tipo.name() : null;
            
            List<Veiculo> veiculosFiltrados = veiculoController.filtrarVeiculos(tipoStr, marca, categoria);
            veiculoTableModel.setVeiculos(veiculosFiltrados);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao filtrar veículos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnFiltrarVeiculosActionPerformed

    private void btnVenderVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderVeiculoActionPerformed
        if (veiculoSelecionado != null) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                                                        "Tem certeza que deseja vender o veículo " + veiculoSelecionado.getPlaca() + "?", 
                                                        "Confirmar Venda", 
                                                        JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    veiculoController.venderVeiculo(veiculoSelecionado);
                    JOptionPane.showMessageDialog(this, "Veículo vendido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    refreshTable(); // Refresh the table after selling
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao vender veículo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para vender.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnVenderVeiculoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrarVeiculos;
    private javax.swing.JButton btnVenderVeiculo;
    private javax.swing.JComboBox<Categoria> cmbCategoriaFiltro;
    private javax.swing.JComboBox<Marca> cmbMarcaFiltro;
    private javax.swing.JComboBox<TipoVeiculo> cmbTipoVeiculoFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVeiculosParaVenda;
    // End of variables declaration//GEN-END:variables

    public void refreshTable() {
        List<Veiculo> veiculosDisponiveis = veiculoController.getVeiculosDisponiveis();
        if (veiculosDisponiveis != null) {
            veiculoTableModel.setVeiculos(veiculosDisponiveis);
        }
    }
}
