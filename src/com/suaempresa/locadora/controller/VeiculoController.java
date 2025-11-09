/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.controller;

import com.suaempresa.locadora.model.Automovel;
import com.suaempresa.locadora.model.Categoria;
import com.suaempresa.locadora.model.Estado;
import com.suaempresa.locadora.model.Locacao;
import com.suaempresa.locadora.model.Marca;
import com.suaempresa.locadora.model.ModeloAutomovel;
import com.suaempresa.locadora.model.ModeloMotocicleta;
import com.suaempresa.locadora.model.ModeloVan;
import com.suaempresa.locadora.model.Motocicleta;
import com.suaempresa.locadora.model.Van;
import com.suaempresa.locadora.model.Veiculo;
import com.suaempresa.locadora.model.dao.DaoFactory;
import com.suaempresa.locadora.model.dao.DaoType;
import com.suaempresa.locadora.model.dao.VeiculoDAO;
import com.suaempresa.locadora.model.dao.LocacaoDAO; // Import LocacaoDAO
import com.suaempresa.locadora.view.VeiculoCadastroPanel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author enzo
 */
public class VeiculoController {
    private VeiculoCadastroPanel view;
    private VeiculoDAO veiculoDAO;
    private LocacaoDAO locacaoDAO; 

    public VeiculoController(VeiculoCadastroPanel view) {
        this.view = view;
        this.veiculoDAO = DaoFactory.getDaoFactory(DaoType.SQL).getVeiculoDAO();
        this.locacaoDAO = DaoFactory.getDaoFactory(DaoType.SQL).getLocacaoDAO(); 
    }
    
    public VeiculoController() {
        this.veiculoDAO = DaoFactory.getDaoFactory(DaoType.SQL).getVeiculoDAO();
        this.locacaoDAO = DaoFactory.getDaoFactory(DaoType.SQL).getLocacaoDAO();
    }
    
    public void initController() {
        view.getBtnIncluirVeiculo().addActionListener(e -> incluirVeiculo());
    }
    
    public void incluirVeiculo() {
        try {
            Veiculo veiculo = view.getVeiculoFromForm();
            if (veiculo.getPlaca().isEmpty() || veiculo.getAno() == 0 || veiculo.getValorParaVenda() == 0.0) {
                view.showWarningMessage("Todos os campos devem ser preenchidos.");
                return;
            }
            
            if (veiculoDAO.getByPlaca(veiculo.getPlaca()) != null) {
                view.showErrorMessage("Já existe um veículo com esta placa.");
                return;
            }

            veiculoDAO.insert(veiculo);
            view.showSuccessMessage("Veículo incluído com sucesso!");
            view.limparCamposVeiculo();
        } catch (Exception e) {
            view.showErrorMessage("Erro ao incluir veículo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<Veiculo> getVeiculosLocados() {
        try {
            return veiculoDAO.getByEstado(Estado.LOCADO);
        } catch (Exception e) {
            System.err.println("Erro ao buscar veículos locados: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public void devolverVeiculo(Veiculo veiculo) {
        try {
            if (veiculo == null || veiculo.getLocacao() == null) {
                throw new IllegalArgumentException("Veículo ou locação inválida para devolução.");
            }
            
           
            Locacao locacaoParaDeletar = veiculo.getLocacao();
                     
            veiculo.devolver();
                      
            veiculoDAO.update(veiculo);
            
            locacaoDAO.delete(locacaoParaDeletar);
            
        } catch (Exception e) {
            System.err.println("Erro ao devolver veículo: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao devolver veículo: " + e.getMessage(), e);
        }
    }
    public List<Veiculo> getVeiculosDisponiveis() {
        try {
            return veiculoDAO.getByEstado(Estado.DISPONIVEL);
        } catch (Exception e) {
            System.err.println("Erro ao buscar veículos disponíveis: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Veiculo> filtrarVeiculos(String tipo, Marca marca, Categoria categoria) {
        try {
            return veiculoDAO.filtrarVeiculos(tipo, marca, categoria);
        } catch (Exception e) {
            System.err.println("Erro ao filtrar veículos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public void venderVeiculo(Veiculo veiculo) {
        try {
            if (veiculo == null) {
                throw new IllegalArgumentException("Veículo inválido para venda.");
            }
            
            veiculo.vender();
            veiculoDAO.update(veiculo);
            
        } catch (Exception e) {
            System.err.println("Erro ao vender veículo: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao vender veículo: " + e.getMessage(), e);
        }
    }
}