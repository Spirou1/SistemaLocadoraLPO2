/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.model;

import static com.suaempresa.locadora.model.Categoria.INTERMEDIARIO;
import static com.suaempresa.locadora.model.Categoria.LUXO;
import static com.suaempresa.locadora.model.Categoria.POPULAR;

/**
 *
 * @author enzo
 */
public class Van extends Veiculo{
    private ModeloVan modelo;
    
    public Van(Marca marca, Estado estado, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloVan modelo) {
        super(marca, estado, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }
    
    public ModeloVan getModelo() {
        return modelo;
    }
    
    @Override
    public double getValorDiariaLocacao() {
        switch (this.categoria) {
            case POPULAR:
                return 200.00;
            case INTERMEDIARIO:
                return 400.00;
            case LUXO:
                return 600.00;
            default:
                return 0.0;
        }
    }
}
