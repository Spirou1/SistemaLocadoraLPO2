/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.suaempresa.locadora.ui;

import com.suaempresa.locadora.model.Automovel;
import com.suaempresa.locadora.model.Categoria;
import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.Estado;
import com.suaempresa.locadora.model.GerenciadorClientes; 
import com.suaempresa.locadora.model.GerenciadorVeiculos;
import com.suaempresa.locadora.model.Marca;
import com.suaempresa.locadora.model.ModeloAutomovel;
import com.suaempresa.locadora.model.ModeloMotocicleta;
import com.suaempresa.locadora.model.Motocicleta;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

/**
 *
 * @author enzo
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());
    
    private GerenciadorClientes gerenciadorClientes;
    private GerenciadorVeiculos gerenciadorVeiculos;
    private CardLayout cardLayout;
    
    private HomePanel homePanelInstancia;
    private ClientesPanel clientesPanelInstancia;
    private VeiculoCadastroPanel veiculoCadastroInstancia; 
    private VeiculoLocacaoPanel veiculoLocacaoInstancia; 
    private VeiculoDevolucaoPanel veiculoDevolucaoPanelInstancia;
    private VeiculoVendaPanel veiculoVendaPanelInstancia;
    

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal(GerenciadorClientes gc, GerenciadorVeiculos gv) {
        this.gerenciadorClientes = gc;
        this.gerenciadorVeiculos = gv;
        
        initComponents();
        
        cardLayout = (CardLayout) parentPanel.getLayout();
        
        this.setLocationRelativeTo(null);
        pack();
        this.setMinimumSize(new java.awt.Dimension(1200, 700));


        jPanel2.removeAll(); 

        jPanel2.add(Box.createVerticalGlue()); 

        if (jLabel4 != null) { 
             jLabel4.setAlignmentX(Component.CENTER_ALIGNMENT); 
             jPanel2.add(jLabel4);
        }
        jPanel2.add(Box.createVerticalStrut(20)); 

        if (jLabel15 != null) { 
             jLabel15.setAlignmentX(Component.CENTER_ALIGNMENT);
             jPanel2.add(jLabel15);
        }
        jPanel2.add(Box.createVerticalStrut(10));  

        if (jLabel16 != null) { 
             jLabel16.setAlignmentX(Component.CENTER_ALIGNMENT);
             jPanel2.add(jLabel16);
        }
        jPanel2.add(Box.createVerticalStrut(50)); 

        int buttonSpacing = 25; 

        btnHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel2.add(btnHome);
        jPanel2.add(Box.createVerticalStrut(buttonSpacing));
        
        btnGerenciarClientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel2.add(btnGerenciarClientes);
        jPanel2.add(Box.createVerticalStrut(buttonSpacing));
        
        btnCadastrarVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel2.add(btnCadastrarVeiculo);
        jPanel2.add(Box.createVerticalStrut(buttonSpacing));
        
        btnLocarVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel2.add(btnLocarVeiculo);
        jPanel2.add(Box.createVerticalStrut(buttonSpacing));
        
        btnDevolverVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel2.add(btnDevolverVeiculo);
        jPanel2.add(Box.createVerticalStrut(buttonSpacing));
        
        btnVenderVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel2.add(btnVenderVeiculo);

        jPanel2.add(Box.createVerticalGlue()); 
        
        jPanel2.revalidate(); 
        jPanel2.repaint();    

        this.homePanelInstancia = new HomePanel(this.gerenciadorClientes, this.gerenciadorVeiculos);
        parentPanel.add(new JScrollPane(homePanelInstancia), "card4"); 

        this.clientesPanelInstancia = new ClientesPanel(this.gerenciadorClientes, this.gerenciadorVeiculos);
        parentPanel.add(new JScrollPane(clientesPanelInstancia), "card3"); 
        
        this.veiculoCadastroInstancia = new VeiculoCadastroPanel(this.gerenciadorVeiculos);
        parentPanel.add(new JScrollPane(veiculoCadastroInstancia), "card2");
        
        this.veiculoLocacaoInstancia = new VeiculoLocacaoPanel(this.gerenciadorClientes, this.gerenciadorVeiculos);
        parentPanel.add(new JScrollPane(veiculoLocacaoInstancia), "card5"); 
        
        this.veiculoDevolucaoPanelInstancia = new VeiculoDevolucaoPanel(this.gerenciadorVeiculos);
        parentPanel.add(new JScrollPane(veiculoDevolucaoPanelInstancia), "card6");
        
        this.veiculoVendaPanelInstancia = new VeiculoVendaPanel(this.gerenciadorVeiculos);
        parentPanel.add(new JScrollPane(veiculoVendaPanelInstancia), "card7");

        cardLayout.show(parentPanel, "card4"); 
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
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        btnGerenciarClientes = new javax.swing.JButton();
        btnCadastrarVeiculo = new javax.swing.JButton();
        btnLocarVeiculo = new javax.swing.JButton();
        btnDevolverVeiculo = new javax.swing.JButton();
        btnVenderVeiculo = new javax.swing.JButton();
        parentPanel = new javax.swing.JPanel();
        panelGerenciarClientes = new javax.swing.JPanel();
        panelCadastrarVeiculo = new javax.swing.JPanel();
        panelLocarVeiculo = new javax.swing.JPanel();
        panelDevolverVeiculo = new javax.swing.JPanel();
        panelVenderVeiculo = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Locadora 1.0");

        jPanel2.setBackground(new java.awt.Color(14, 20, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 355));
        jPanel2.setVerifyInputWhenFocusTarget(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jLabel4.setIcon(new javax.swing.ImageIcon("/home/enzo/LPO1Trabalho/SistemaLocadoraVeiculos/car-128.png")); // NOI18N
        jPanel2.add(jLabel4);

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Sistema Locadora 1.0");
        jPanel2.add(jLabel15);

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("LPOO1-2025/1");
        jPanel2.add(jLabel16);

        btnHome.setBackground(new java.awt.Color(14, 20, 30));
        btnHome.setFont(new java.awt.Font("Poppins ExtraLight", 1, 20)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText(" Home");
        btnHome.setToolTipText("");
        btnHome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(255, 255, 255)));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel2.add(btnHome);

        btnGerenciarClientes.setBackground(new java.awt.Color(14, 20, 30));
        btnGerenciarClientes.setFont(new java.awt.Font("Poppins ExtraLight", 1, 20)); // NOI18N
        btnGerenciarClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnGerenciarClientes.setText(" Gerenciar Clientes");
        btnGerenciarClientes.setToolTipText("");
        btnGerenciarClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(255, 255, 255)));
        btnGerenciarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarClientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnGerenciarClientes);

        btnCadastrarVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnCadastrarVeiculo.setFont(new java.awt.Font("Poppins ExtraLight", 1, 20)); // NOI18N
        btnCadastrarVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrarVeiculo.setText(" Cadastrar Veículo");
        btnCadastrarVeiculo.setToolTipText("");
        btnCadastrarVeiculo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(255, 255, 255)));
        btnCadastrarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarVeiculoActionPerformed(evt);
            }
        });
        jPanel2.add(btnCadastrarVeiculo);

        btnLocarVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnLocarVeiculo.setFont(new java.awt.Font("Poppins ExtraLight", 1, 20)); // NOI18N
        btnLocarVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnLocarVeiculo.setText(" Locar Veículo");
        btnLocarVeiculo.setToolTipText("");
        btnLocarVeiculo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(255, 255, 255)));
        btnLocarVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocarVeiculoActionPerformed(evt);
            }
        });
        jPanel2.add(btnLocarVeiculo);

        btnDevolverVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnDevolverVeiculo.setFont(new java.awt.Font("Poppins ExtraLight", 1, 20)); // NOI18N
        btnDevolverVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnDevolverVeiculo.setText(" Devolver Veículo");
        btnDevolverVeiculo.setToolTipText("");
        btnDevolverVeiculo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(255, 255, 255)));
        btnDevolverVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverVeiculoActionPerformed(evt);
            }
        });
        jPanel2.add(btnDevolverVeiculo);

        btnVenderVeiculo.setBackground(new java.awt.Color(14, 20, 30));
        btnVenderVeiculo.setFont(new java.awt.Font("Poppins ExtraLight", 1, 20)); // NOI18N
        btnVenderVeiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnVenderVeiculo.setText(" Vender Veículo");
        btnVenderVeiculo.setToolTipText("");
        btnVenderVeiculo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(255, 255, 255)));
        btnVenderVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderVeiculoActionPerformed(evt);
            }
        });
        jPanel2.add(btnVenderVeiculo);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        parentPanel.setBackground(new java.awt.Color(240, 242, 240));
        parentPanel.setLayout(new java.awt.CardLayout());

        panelGerenciarClientes.setBackground(new java.awt.Color(240, 238, 230));

        javax.swing.GroupLayout panelGerenciarClientesLayout = new javax.swing.GroupLayout(panelGerenciarClientes);
        panelGerenciarClientes.setLayout(panelGerenciarClientesLayout);
        panelGerenciarClientesLayout.setHorizontalGroup(
            panelGerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGerenciarClientesLayout.setVerticalGroup(
            panelGerenciarClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        parentPanel.add(panelGerenciarClientes, "card3");

        panelCadastrarVeiculo.setBackground(new java.awt.Color(240, 238, 230));

        javax.swing.GroupLayout panelCadastrarVeiculoLayout = new javax.swing.GroupLayout(panelCadastrarVeiculo);
        panelCadastrarVeiculo.setLayout(panelCadastrarVeiculoLayout);
        panelCadastrarVeiculoLayout.setHorizontalGroup(
            panelCadastrarVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCadastrarVeiculoLayout.setVerticalGroup(
            panelCadastrarVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        parentPanel.add(panelCadastrarVeiculo, "card2");

        panelLocarVeiculo.setBackground(new java.awt.Color(240, 238, 230));
        panelLocarVeiculo.setForeground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout panelLocarVeiculoLayout = new javax.swing.GroupLayout(panelLocarVeiculo);
        panelLocarVeiculo.setLayout(panelLocarVeiculoLayout);
        panelLocarVeiculoLayout.setHorizontalGroup(
            panelLocarVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLocarVeiculoLayout.setVerticalGroup(
            panelLocarVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        parentPanel.add(panelLocarVeiculo, "card5");

        panelDevolverVeiculo.setBackground(new java.awt.Color(240, 238, 230));
        panelDevolverVeiculo.setForeground(new java.awt.Color(204, 0, 204));

        javax.swing.GroupLayout panelDevolverVeiculoLayout = new javax.swing.GroupLayout(panelDevolverVeiculo);
        panelDevolverVeiculo.setLayout(panelDevolverVeiculoLayout);
        panelDevolverVeiculoLayout.setHorizontalGroup(
            panelDevolverVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDevolverVeiculoLayout.setVerticalGroup(
            panelDevolverVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        parentPanel.add(panelDevolverVeiculo, "card6");

        panelVenderVeiculo.setBackground(new java.awt.Color(240, 238, 230));

        javax.swing.GroupLayout panelVenderVeiculoLayout = new javax.swing.GroupLayout(panelVenderVeiculo);
        panelVenderVeiculo.setLayout(panelVenderVeiculoLayout);
        panelVenderVeiculoLayout.setHorizontalGroup(
            panelVenderVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelVenderVeiculoLayout.setVerticalGroup(
            panelVenderVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        parentPanel.add(panelVenderVeiculo, "card7");

        panelHome.setBackground(new java.awt.Color(240, 238, 230));

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        parentPanel.add(panelHome, "card4");

        getContentPane().add(parentPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        cardLayout.show(parentPanel, "card4");
        if (homePanelInstancia != null) {
        homePanelInstancia.carregarEstatisticas();
    }
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnGerenciarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarClientesActionPerformed
        // TODO add your handling code here:
        cardLayout.show(parentPanel, "card3");
    }//GEN-LAST:event_btnGerenciarClientesActionPerformed

    private void btnCadastrarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarVeiculoActionPerformed
        // TODO add your handling code here:
        cardLayout.show(parentPanel, "card2");
    }//GEN-LAST:event_btnCadastrarVeiculoActionPerformed

    private void btnLocarVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocarVeiculoActionPerformed
        // TODO add your handling code here:
        cardLayout.show(parentPanel, "card5");
    }//GEN-LAST:event_btnLocarVeiculoActionPerformed

    private void btnDevolverVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverVeiculoActionPerformed
        // TODO add your handling code here:
        cardLayout.show(parentPanel, "card6");
        if (veiculoDevolucaoPanelInstancia != null) {
            veiculoDevolucaoPanelInstancia.carregarTabelaVeiculosLocados();
        } 
    }//GEN-LAST:event_btnDevolverVeiculoActionPerformed

    private void btnVenderVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderVeiculoActionPerformed
        // TODO add your handling code here:
        cardLayout.show(parentPanel, "card7");
        if (veiculoVendaPanelInstancia != null) {
        veiculoVendaPanelInstancia.carregarTabelaVeiculosParaVenda();
        } 
    }//GEN-LAST:event_btnVenderVeiculoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorVeiculos gv = new GerenciadorVeiculos();
        
        
        Cliente cliente1 = new Cliente("Joao", "Silva", "123456789", "111.222.333-44", "Rua A, 100");
        gc.adicionarCliente(cliente1);

        Cliente cliente2 = new Cliente("Maria", "Souza", "987654321", "555.666.777-88", "Av. B, 200");
        gc.adicionarCliente(cliente2);

        Cliente cliente3 = new Cliente("Pedro", "Almeida", "456789123", "999.888.777-66", "Travessa C, 300");
        gc.adicionarCliente(cliente3);
        
        Automovel carro1 = new Automovel(
            Marca.FIAT,
            Estado.DISPONIVEL, 
            Categoria.POPULAR,
            30000.0, "ABC-1234", 2020, ModeloAutomovel.Civic
        );
        gv.adicionarVeiculo(carro1);
        
        Automovel carro2 = new Automovel(
            Marca.VW,
            Estado.DISPONIVEL, 
            Categoria.INTERMEDIARIO,
            55000.0, "XYZ-5678", 2022, ModeloAutomovel.Gol
        );
        gv.adicionarVeiculo(carro2);
        
        Motocicleta moto1 = new Motocicleta(
            Marca.HONDA,
            Estado.NOVO, 
            Categoria.LUXO,
            25000.0, "MOTO-9012", 2023, ModeloMotocicleta.CBR500
        );
        gv.adicionarVeiculo(moto1);
        
        Automovel carro5 = new Automovel(
        Marca.HONDA,
        Estado.DISPONIVEL,
        Categoria.INTERMEDIARIO,
        80000.0, "AAA-1234", 2022, ModeloAutomovel.Civic);
        gv.adicionarVeiculo(carro5);
        
        TelaPrincipal frame = new TelaPrincipal(gc, gv);
        frame.setVisible(true);
        
        if (frame.homePanelInstancia != null) { 
            frame.homePanelInstancia.carregarEstatisticas();
        } 
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrarVeiculo;
    private javax.swing.JButton btnDevolverVeiculo;
    private javax.swing.JButton btnGerenciarClientes;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLocarVeiculo;
    private javax.swing.JButton btnVenderVeiculo;
    private javax.swing.ButtonGroup grupoTipoVeiculo;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelCadastrarVeiculo;
    private javax.swing.JPanel panelDevolverVeiculo;
    private javax.swing.JPanel panelGerenciarClientes;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLocarVeiculo;
    private javax.swing.JPanel panelVenderVeiculo;
    private javax.swing.JPanel parentPanel;
    // End of variables declaration//GEN-END:variables
}
