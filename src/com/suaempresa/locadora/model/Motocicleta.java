/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.model;

/**
 *
 * @author enzo
 */
public class Motocicleta extends Veiculo{
    private ModeloMotocicleta modelo;
    
    public Motocicleta(Marca marca, Estado estado, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloMotocicleta modelo) {
        super(marca, estado, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }
    
    public ModeloMotocicleta getModelo() {
        return modelo;
    }
    
    @Override
    public double getValorDiariaLocacao() {
        switch (this.categoria) {
            case POPULAR:
                return 70.00;
            case INTERMEDIARIO:
                return 200.00;
            case LUXO:
                return 350.00;
            default:
                return 0.0;
        }
    }
}
