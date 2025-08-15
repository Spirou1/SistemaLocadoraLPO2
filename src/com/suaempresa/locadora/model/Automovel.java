/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.model;

/**
 *
 * @author enzo
 */
public class Automovel extends Veiculo {
    private ModeloAutomovel modelo;
    
    public Automovel(Marca marca, Estado estado, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloAutomovel modelo) {
        super(marca, estado, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }
    
    public ModeloAutomovel getModelo() {
        return modelo;
    }
    
    @Override
    public double getValorDiariaLocacao() {
        switch (this.categoria) {
            case POPULAR:
                return 100.00;
            case INTERMEDIARIO:
                return 300.00;
            case LUXO:
                return 450.00;
            default:
                return 0.0;
        }
    }
}
