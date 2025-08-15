/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.suaempresa.locadora.ui;

import com.suaempresa.locadora.model.Automovel; // 
import com.suaempresa.locadora.model.Categoria; // 
import com.suaempresa.locadora.model.Estado; // 
import com.suaempresa.locadora.model.GerenciadorVeiculos;
import com.suaempresa.locadora.model.Marca; // 
import com.suaempresa.locadora.model.ModeloAutomovel; // 
import com.suaempresa.locadora.model.ModeloMotocicleta; // 
import com.suaempresa.locadora.model.ModeloVan; // 
import com.suaempresa.locadora.model.Motocicleta; // 
import com.suaempresa.locadora.model.Van; // 
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author enzo
 */
public class VeiculoCadastroPanel extends javax.swing.JPanel {

    private GerenciadorVeiculos gerenciadorVeiculos;
    
    private JPanel panelTipoVeiculo; 
    private JPanel panelMarcaEstado; 
    private JPanel panelCategoriaModelo; 
    private JPanel panelValorPlacaAno;
  
 
    /**
     * Creates new form VeiculoCadastroPanel
     */
    public VeiculoCadastroPanel(GerenciadorVeiculos gv) {
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

        
        panelTipoVeiculo = new JPanel();
        panelTipoVeiculo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5)); 
        panelTipoVeiculo.setOpaque(false);

        if (jLabel3 != null) panelTipoVeiculo.add(jLabel3); 
        if (radioAutomovel != null) panelTipoVeiculo.add(radioAutomovel);
        if (radioMotocicleta != null) panelTipoVeiculo.add(radioMotocicleta);
        if (radioVan != null) panelTipoVeiculo.add(radioVan);

        panelTipoVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelTipoVeiculo);
        this.add(Box.createVerticalStrut(15)); 

        
        
        panelMarcaEstado = new JPanel();
        panelMarcaEstado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10)); 
        panelMarcaEstado.setOpaque(false);
        panelMarcaEstado.add(createComboBoxPair(jLabel4, cmbMarca)); 
        panelMarcaEstado.add(createComboBoxPair(jLabel5, cmbEstado)); 
        panelMarcaEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelMarcaEstado);
        this.add(Box.createVerticalStrut(10)); 

        
        panelCategoriaModelo = new JPanel();
        panelCategoriaModelo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        panelCategoriaModelo.setOpaque(false);
        panelCategoriaModelo.add(createComboBoxPair(jLabel6, cmbCategoria)); 
        panelCategoriaModelo.add(createComboBoxPair(jLabel7, cmbModelo)); 
        panelCategoriaModelo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelCategoriaModelo);
        this.add(Box.createVerticalStrut(20)); 

        
        panelValorPlacaAno = new JPanel();
        panelValorPlacaAno.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        panelValorPlacaAno.setOpaque(false);
        panelValorPlacaAno.add(createFieldPair(jLabel8, txtValorDeCompra)); 
        panelValorPlacaAno.add(createFieldPair(jLabel9, txtPlaca)); 
        panelValorPlacaAno.add(createFieldPair(jLabel10, txtAno)); 
        panelValorPlacaAno.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelValorPlacaAno);
        this.add(Box.createVerticalStrut(30)); 

        
        if (btnIncluirVeiculo != null) {
            btnIncluirVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(btnIncluirVeiculo);
        }
        this.add(Box.createVerticalStrut(20)); 

        this.add(Box.createVerticalGlue()); 

        this.revalidate(); 
        this.repaint();    

        configurarComboBoxesEListeners(); 
        radioAutomovel.setSelected(true);
        radioTipoVeiculoActionPerformed(null);
    }
    
    private JPanel createComboBoxPair(JLabel label, javax.swing.JComboBox<?> comboBox) {
        JPanel pairPanel = new JPanel();
        pairPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        pairPanel.setOpaque(false);

        if (label != null) {
            pairPanel.add(label);
        }
        if (comboBox != null) {
            comboBox.setPreferredSize(new java.awt.Dimension(200, 30)); 
            pairPanel.add(comboBox);
        }
        return pairPanel;
    }
    
    private JPanel createFieldPair(JLabel label, javax.swing.JTextField textField) {
        JPanel pairPanel = new JPanel();
        pairPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        pairPanel.setOpaque(false);

        if (label != null) {
            pairPanel.add(label);
        }
        if (textField != null) {
            textField.setPreferredSize(new java.awt.Dimension(200, 30)); 
            pairPanel.add(textField);
        }
        return pairPanel;
    }
    
    private void configurarComboBoxesEListeners() {
        // Configura JComboBoxes de Enums
        cmbMarca.setModel(new DefaultComboBoxModel<>(Marca.values()));
        cmbEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));
        cmbCategoria.setModel(new DefaultComboBoxModel<>(Categoria.values()));

        
        radioAutomovel.addActionListener(e -> radioTipoVeiculoActionPerformed(e));
        radioMotocicleta.addActionListener(e -> radioTipoVeiculoActionPerformed(e));
        radioVan.addActionListener(e -> radioTipoVeiculoActionPerformed(e));

        
        cmbModelo.removeAllItems();
        cmbModelo.addItem("Selecione o tipo de veículo");
        cmbModelo.setEnabled(false); 
    }
    
    private void radioTipoVeiculoActionPerformed(java.awt.event.ActionEvent evt) {
        cmbModelo.removeAllItems(); 
        cmbModelo.setEnabled(true); 

        if (radioAutomovel.isSelected()) {
            for (ModeloAutomovel modelo : ModeloAutomovel.values()) {
                cmbModelo.addItem(modelo.name());
            }
        } else if (radioMotocicleta.isSelected()) {
            for (ModeloMotocicleta modelo : ModeloMotocicleta.values()) {
                cmbModelo.addItem(modelo.name());
            }
        } else if (radioVan.isSelected()) {
            for (ModeloVan modelo : ModeloVan.values()) {
                cmbModelo.addItem(modelo.name());
            }
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

        grupoTipoVeiculo = new javax.swing.ButtonGroup();
        btnIncluirVeiculo = new javax.swing.JButton();
        txtAno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtValorDeCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbModelo = new javax.swing.JComboBox<>();
        cmbCategoria = new javax.swing.JComboBox<>();
        cmbEstado = new javax.swing.JComboBox<>();
        cmbMarca = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        radioVan = new javax.swing.JRadioButton();
        radioMotocicleta = new javax.swing.JRadioButton();
        radioAutomovel = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 238, 230));
        setPreferredSize(new java.awt.Dimension(843, 613));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        btnIncluirVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnIncluirVeiculo.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnIncluirVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnIncluirVeiculo.setText("Incluir Veiculo");
        btnIncluirVeiculo.setPreferredSize(new java.awt.Dimension(120, 50));
        btnIncluirVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirVeiculoActionPerformed(evt);
            }
        });
        add(btnIncluirVeiculo);

        txtAno.setBackground(new java.awt.Color(14, 20, 30));
        txtAno.setForeground(new java.awt.Color(255, 255, 255));
        txtAno.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtAno);

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ano:");
        add(jLabel10);

        txtPlaca.setBackground(new java.awt.Color(14, 20, 30));
        txtPlaca.setForeground(new java.awt.Color(255, 255, 255));
        txtPlaca.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtPlaca);

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Placa:");
        add(jLabel9);

        txtValorDeCompra.setBackground(new java.awt.Color(14, 20, 30));
        txtValorDeCompra.setForeground(new java.awt.Color(255, 255, 255));
        txtValorDeCompra.setPreferredSize(new java.awt.Dimension(70, 40));
        add(txtValorDeCompra);

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Valor de Compra:");
        add(jLabel8);

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Modelo:");
        add(jLabel7);

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Categoria:");
        add(jLabel6);

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Estado:");
        add(jLabel5);

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Marca:");
        add(jLabel4);

        cmbModelo.setBackground(new java.awt.Color(14, 20, 30));
        cmbModelo.setForeground(new java.awt.Color(255, 255, 255));
        cmbModelo.setPreferredSize(new java.awt.Dimension(80, 40));
        add(cmbModelo);

        cmbCategoria.setBackground(new java.awt.Color(14, 20, 30));
        cmbCategoria.setForeground(new java.awt.Color(255, 255, 255));
        cmbCategoria.setPreferredSize(new java.awt.Dimension(80, 40));
        add(cmbCategoria);

        cmbEstado.setBackground(new java.awt.Color(14, 20, 30));
        cmbEstado.setForeground(new java.awt.Color(255, 255, 255));
        cmbEstado.setMinimumSize(new java.awt.Dimension(80, 40));
        add(cmbEstado);

        cmbMarca.setBackground(new java.awt.Color(14, 20, 30));
        cmbMarca.setForeground(new java.awt.Color(255, 255, 255));
        cmbMarca.setMinimumSize(new java.awt.Dimension(80, 40));
        add(cmbMarca);

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tipo do veículo:");
        add(jLabel3);

        grupoTipoVeiculo.add(radioVan);
        radioVan.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        radioVan.setForeground(new java.awt.Color(0, 0, 0));
        radioVan.setText("Van");
        add(radioVan);

        grupoTipoVeiculo.add(radioMotocicleta);
        radioMotocicleta.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        radioMotocicleta.setForeground(new java.awt.Color(0, 0, 0));
        radioMotocicleta.setText("Motocicleta");
        add(radioMotocicleta);

        grupoTipoVeiculo.add(radioAutomovel);
        radioAutomovel.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        radioAutomovel.setForeground(new java.awt.Color(0, 0, 0));
        radioAutomovel.setSelected(true);
        radioAutomovel.setText("Automovel");
        add(radioAutomovel);

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Sistema Locadora");
        add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Cadastro de Veículos");
        add(jLabel2);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnIncluirVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirVeiculoActionPerformed
        // TODO add your handling code here:
    Marca marca = (Marca) cmbMarca.getSelectedItem();
    Estado estado = (Estado) cmbEstado.getSelectedItem();
    Categoria categoria = (Categoria) cmbCategoria.getSelectedItem();
    String placa = txtPlaca.getText();
    String anoStr = txtAno.getText();
    String valorDeCompraStr = txtValorDeCompra.getText();

   
    if (marca == null || estado == null || categoria == null || placa.isEmpty() || anoStr.isEmpty() || valorDeCompraStr.isEmpty() || cmbModelo.getSelectedItem() == null || cmbModelo.getSelectedItem().equals("Selecione o tipo de veículo")) {
        JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos ou selecionados.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int ano;
    double valorDeCompra;
    try {
        ano = Integer.parseInt(anoStr);
        String cleanValorStr = valorDeCompraStr.replaceAll("[^\\d,]", "");
        cleanValorStr = cleanValorStr.replace(",", ".");
        valorDeCompra = Double.parseDouble(cleanValorStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ano ou Valor de Compra inválidos. Use apenas números. Para o valor, use vírgula como separador decimal (ex: 10000,50).", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
    return;
    } 

   
    if (!placa.matches("[A-Z]{3}-\\d{4}")) {
        JOptionPane.showMessageDialog(this, "Formato de placa inválido. Use XXX-0000 (ex: ABC-1234).", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        return;
    }

   
    String modeloSelecionadoString = (String) cmbModelo.getSelectedItem();
    com.suaempresa.locadora.model.Veiculo novoVeiculo = null;

    if (radioAutomovel.isSelected()) {
        ModeloAutomovel modelo = ModeloAutomovel.valueOf(modeloSelecionadoString);
        novoVeiculo = new Automovel(marca, estado, categoria, valorDeCompra, placa, ano, modelo); // 
    } else if (radioMotocicleta.isSelected()) {
        ModeloMotocicleta modelo = ModeloMotocicleta.valueOf(modeloSelecionadoString);
        novoVeiculo = new Motocicleta(marca, estado, categoria, valorDeCompra, placa, ano, modelo); // 
    } else if (radioVan.isSelected()) {
        ModeloVan modelo = ModeloVan.valueOf(modeloSelecionadoString);
        novoVeiculo = new Van(marca, estado, categoria, valorDeCompra, placa, ano, modelo); // 
    }

    
    if (novoVeiculo != null) {
        gerenciadorVeiculos.adicionarVeiculo(novoVeiculo);
        JOptionPane.showMessageDialog(this, "Veículo com placa " + placa + " cadastrado com sucesso!");
        limparCamposVeiculo(); 
    } else {
        JOptionPane.showMessageDialog(this, "Erro: Selecione um tipo de veículo.", "Erro de Lógica", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnIncluirVeiculoActionPerformed

    private void limparCamposVeiculo() {
   
    txtValorDeCompra.setText("");
    txtPlaca.setText("");
    txtAno.setText("");

    
    cmbMarca.setSelectedIndex(0);
    cmbEstado.setSelectedIndex(0);
    cmbCategoria.setSelectedIndex(0);

    
    grupoTipoVeiculo.clearSelection(); 
    radioAutomovel.setSelected(true);
    radioTipoVeiculoActionPerformed(null); 
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIncluirVeiculo;
    private javax.swing.JComboBox<Categoria> cmbCategoria;
    private javax.swing.JComboBox<Estado> cmbEstado;
    private javax.swing.JComboBox<Marca> cmbMarca;
    private javax.swing.JComboBox<String> cmbModelo;
    private javax.swing.ButtonGroup grupoTipoVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton radioAutomovel;
    private javax.swing.JRadioButton radioMotocicleta;
    private javax.swing.JRadioButton radioVan;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtValorDeCompra;
    // End of variables declaration//GEN-END:variables
}
