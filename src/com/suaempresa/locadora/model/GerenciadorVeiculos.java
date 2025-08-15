/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.suaempresa.locadora.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;

/**
 *
 * @author enzo
 */
public class GerenciadorVeiculos {
    private List<Veiculo> veiculos;
    
    public enum TipoVeiculo {
        AUTOMOVEL, MOTOCICLETA, VAN, TODOS
    }

    public GerenciadorVeiculos() {
        this.veiculos = new ArrayList<>();
    }
    
    public void adicionarVeiculo(Veiculo veiculo) {
        if (buscarVeiculoPorPlaca(veiculo.getPlaca()) == null) {
            this.veiculos.add(veiculo);
            System.out.println("Veículo placa " + veiculo.getPlaca() + " adicionado.");
        } else {
            System.out.println("Erro: Veículo com placa " + veiculo.getPlaca() + " já existe.");
        }
    }
    
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }
     
    public List<Veiculo> listarTodosVeiculos() {
        return new ArrayList<>(veiculos);
    }
    
    public List<Veiculo> listarVeiculosDisponiveisParaLocacao() {
        List<Veiculo> disponiveis = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getEstado() == Estado.DISPONIVEL) {
                disponiveis.add(v);
            }
        }
        return disponiveis;
    }
    
    
    public List<Veiculo> listarVeiculosLocados() {
        List<Veiculo> locados = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getEstado() == Estado.LOCADO) {
                locados.add(v);
            }
        }
        return locados;
    }

    public List<Veiculo> listarVeiculosDisponiveisParaVenda() {
        List<Veiculo> disponiveisParaVenda = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getEstado() == Estado.DISPONIVEL) {
                disponiveisParaVenda.add(v);
            }
        }
        return disponiveisParaVenda;
    }
    
    public List<Veiculo> filtrarVeiculos(TipoVeiculo tipo, Marca marca, Categoria categoria) {
        List<Veiculo> veiculosFiltrados = new ArrayList<>();

        for (Veiculo v : veiculos) {
            boolean matchesType = true;
            if (tipo != null && tipo != TipoVeiculo.TODOS) {
                if (tipo == TipoVeiculo.AUTOMOVEL && !(v instanceof Automovel)) {
                    matchesType = false;
                } else if (tipo == TipoVeiculo.MOTOCICLETA && !(v instanceof Motocicleta)) {
                    matchesType = false;
                } else if (tipo == TipoVeiculo.VAN && !(v instanceof Van)) {
                    matchesType = false;
                }
            }

            boolean matchesMarca = (marca == null || v.getMarca() == marca);
            boolean matchesCategoria = (categoria == null || v.getCategoria() == categoria);

            if (matchesType && matchesMarca && matchesCategoria) {
                veiculosFiltrados.add(v);
            }
        }
        return veiculosFiltrados;
    }

    public List<Veiculo> getVeiculosLocadosPorCliente(Cliente cliente) {
        List<Veiculo> locadosPorCliente = new ArrayList<>();
        for (Veiculo v : veiculos) {
            
            if (v.getEstado() == Estado.LOCADO && v.getLocacao() != null && v.getLocacao().getCliente().equals(cliente)) {
                locadosPorCliente.add(v);
            }
        }
        return locadosPorCliente;
    }
    
    public boolean locarVeiculo(String placa, int dias, Calendar data, Cliente cliente) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculo.locar(dias, data, cliente);
            return true;
        }
        System.out.println("Veículo com placa " + placa + " não encontrado para locação.");
        return false;
    }
    
    public boolean devolverVeiculo(String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculo.devolver();
            return true;
        }
        System.out.println("Veículo com placa " + placa + " não encontrado para devolução.");
        return false;
    }

    public boolean venderVeiculo(String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculo.vender();
            return true;
        }
        System.out.println("Veículo com placa " + placa + " não encontrado para venda.");
        return false;
    }
}
