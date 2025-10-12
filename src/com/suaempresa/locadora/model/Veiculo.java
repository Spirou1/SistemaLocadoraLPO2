package com.suaempresa.locadora.model;

import com.suaempresa.locadora.model.Marca;
import com.suaempresa.locadora.model.Estado;
import com.suaempresa.locadora.model.Categoria;
import java.util.Calendar;

/**
 *
 * @author enzo
 */
public abstract class Veiculo implements VeiculoI {
    
    protected long id; 

    protected Marca marca;
    protected Estado estado;
    protected Locacao locacao; 
    protected Categoria categoria;
    public double valorDeCompra; 
    protected String placa; 
    protected int ano; 

    public Veiculo(Marca marca, Estado estado, Categoria categoria, double valorDeCompra, String placa, int ano) { 
        this.marca = marca;
        this.estado = estado;
        this.categoria = categoria;
        this.valorDeCompra = valorDeCompra;
        this.placa = placa;
        this.ano = ano;
        this.locacao = null;
    }

    
    public void locar(int dias, Calendar data, Cliente cliente) { 
        if (this.estado == Estado.DISPONIVEL) { 
            double valorDiaria = getValorDiariaLocacao();
            double valorTotal = valorDiaria * dias;
            this.locacao = new Locacao(dias, valorTotal, data, cliente); 
            this.estado = Estado.LOCADO;
            System.out.println("Veículo " + this.placa + " locado com sucesso!");
        } else {
            System.out.println("Veículo " + this.placa + " não está DISPONIVEL para locação. Estado atual: " + this.estado);
        }
    }

    
    public void vender() { 
        if (this.estado != Estado.VENDIDO) { 
            this.estado = Estado.VENDIDO; 
            this.locacao = null; 
            System.out.println("Veículo " + this.placa + " vendido.");
        } else {
            System.out.println("Veículo " + this.placa + " já está vendido.");
        }
    }

    
    public void devolver() { 
        if (this.estado == Estado.LOCADO) { 
            this.estado = Estado.DISPONIVEL; 
            this.locacao = null; 
            System.out.println("Veículo " + this.placa + " devolvido. Estado atual: " + this.estado);
        } else {
            System.out.println("Veículo " + this.placa + " não está LOCADO para ser devolvido. Estado atual: " + this.estado);
        }
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Estado getEstado() { 
        return estado;
    }

    
    public Marca getMarca() { 
        return marca;
    }

   
    public Categoria getCategoria() { 
        return categoria;
    }

  
    public Locacao getLocacao() { 
        return locacao;
    }

    public String getPlaca() { 
        return placa;
    }

    public int getAno() {
        return ano;
    }

    public double getValorParaVenda() { 
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int idadeVeiculoEmAnos = anoAtual - this.ano;

        double valorVenda = this.valorDeCompra - (idadeVeiculoEmAnos * 0.15 * this.valorDeCompra); 

        if (valorVenda < (this.valorDeCompra * 0.1) || valorVenda < 0) { 
            return this.valorDeCompra * 0.1; 
        }
        return valorVenda;
    }

    public abstract double getValorDiariaLocacao();
}