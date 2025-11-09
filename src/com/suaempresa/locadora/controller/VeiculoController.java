/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.controller;

import com.suaempresa.locadora.model.Automovel;
import com.suaempresa.locadora.model.Categoria;
import com.suaempresa.locadora.model.Estado;
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
import com.suaempresa.locadora.view.VeiculoCadastroPanel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author enzo
 */
public class VeiculoController {
    private VeiculoCadastroPanel view;
    private VeiculoDAO veiculoDAO;

    public VeiculoController(VeiculoCadastroPanel view) {
        this.view = view;
        this.veiculoDAO = DaoFactory.getDaoFactory(DaoType.SQL).getVeiculoDAO();
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
}