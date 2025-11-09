/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.model;
import java.util.Calendar;
/**
 *
 * @author enzo
 */
public class Locacao {
    private long id;
    private int dias;
    private double valor;
    private Calendar data;
    private Cliente cliente;
    private Veiculo veiculo;
    
    public Locacao(int dias, double valor, Calendar data, Cliente cliente, Veiculo veiculo) {
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public double getValorTotal() {
        return valor;
    }
    
    public Calendar getDataLocacao() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public int getDias() {
        return dias;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
