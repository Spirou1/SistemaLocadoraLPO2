package com.suaempresa.locadora.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author enzo
 */
public class GerenciadorClientes { 
    
    private List<Cliente> clientes;
    
    public GerenciadorClientes() {
        this.clientes = new ArrayList<>();
    }
    
    public void adicionarCliente(Cliente cliente) {
        if (buscarClientePorCpf(cliente.getCpf()) == null) { 
            this.clientes.add(cliente);
            System.out.println("Cliente " + cliente.getNome() + " adicionado.");
        } else {
            System.out.println("Erro: Cliente com CPF " + cliente.getCpf() + " já existe.");
        }
    }
    
    public void atualizarCliente(Cliente clienteAtualizado) {
        for(int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(clienteAtualizado.getCpf())) {
                clientes.set(i, clienteAtualizado);
                System.out.println("Cliente " + clienteAtualizado.getNome() + " atualizado.");
                return;
            }
        }
        System.out.println("Cliente com CPF " + clienteAtualizado.getCpf() + " não encontrado para atualização.");
    }

    public boolean excluirCliente(String cpf, GerenciadorVeiculos gerenciadorVeiculos) {
        Cliente clienteParaExcluir = buscarClientePorCpf(cpf); 

        if (clienteParaExcluir != null) {
            if (gerenciadorVeiculos.getVeiculosLocadosPorCliente(clienteParaExcluir).isEmpty()) {
                if (clientes.remove(clienteParaExcluir)) {
                    System.out.println("Cliente " + clienteParaExcluir.getNome() + " excluído.");
                    return true;
                } else {
                    System.out.println("Erro ao tentar remover o cliente da lista.");
                    return false;
                }
            } else {
                System.out.println("Cliente não pode ser excluído, pois possui veículos locados.");
                return false;
            }
        } else {
            System.out.println("Cliente com CPF " + cpf + " não encontrado.");
            return false;
        }
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
    
    public List<Cliente> buscarClientePorNomeSobrenome(String nome, String sobrenome) { 
        List<Cliente> clientesEncontrados = new ArrayList<>();
        
        String nomeBuscaLower = nome.toLowerCase();
        String sobrenomeBuscaLower = sobrenome.toLowerCase();

        for (Cliente cliente : clientes) {
            String clienteNomeLower = cliente.getNome().toLowerCase();
            String clienteSobrenomeLower = cliente.getSobrenome().toLowerCase();

            if (clienteNomeLower.contains(nomeBuscaLower) &&
                clienteSobrenomeLower.contains(sobrenomeBuscaLower)) {
                clientesEncontrados.add(cliente);
            }
        }
        return clientesEncontrados;
    }
    
    public List<Cliente> listarTodosClientes() {
        return new ArrayList<>(clientes);
    }
}