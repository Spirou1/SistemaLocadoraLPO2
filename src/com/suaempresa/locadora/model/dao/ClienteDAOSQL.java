package com.suaempresa.locadora.model.dao;

import com.suaempresa.locadora.model.Cliente;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAOSQL implements ClienteDAO {
    
    private static final String INSERT = "INSERT INTO cliente (nome, sobrenome, cpf, rg, endereco) VALUES (?, ?, ?, ?, ?)";
    
    private static final String UPDATE = "UPDATE cliente SET nome=?, sobrenome=?, rg=?, endereco=? WHERE cpf=?";
    
    private static final String DELETE = "DELETE FROM cliente WHERE cpf=?";
    
    private static final String SELECT_ALL = "SELECT id, nome, sobrenome, cpf, rg, endereco FROM cliente ORDER BY nome";
    
    private static final String SELECT_BY_ID = "SELECT id, nome, sobrenome, cpf, rg, endereco FROM cliente WHERE id=?";
    
    private static final String SELECT_BY_CPF = "SELECT id, nome, sobrenome, cpf, rg, endereco FROM cliente WHERE cpf=?";
    
    private static final String TRUNCATE = "TRUNCATE TABLE cliente RESTART IDENTITY";
    

    private static ClienteDAOSQL instance;
    
    public ClienteDAOSQL() {}
    
    public static ClienteDAOSQL getInstance() {
        if (instance == null) {
            instance = new ClienteDAOSQL();
        }
        return instance;
    }
    
 
    public void insert(Cliente cliente) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtAdiciona = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            
            stmtAdiciona.setString(1, cliente.getNome());
            stmtAdiciona.setString(2, cliente.getSobrenome());
            stmtAdiciona.setString(3, cliente.getCpf());
            stmtAdiciona.setString(4, cliente.getRg());
            stmtAdiciona.setString(5, cliente.getEndereco());

            stmtAdiciona.executeUpdate();
            
            try (ResultSet rs = stmtAdiciona.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getLong(1));
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage(), e);
        }          
    }
    
    
    @Override
    public void update(Cliente cliente) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtAtualiza = connection.prepareStatement(UPDATE)) {

            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getSobrenome());
            stmtAtualiza.setString(3, cliente.getRg());
            stmtAtualiza.setString(4, cliente.getEndereco());
            
            stmtAtualiza.setString(5, cliente.getCpf()); 

            stmtAtualiza.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void delete(Cliente cliente) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(DELETE)) {

            
            stmtExcluir.setString(1, cliente.getCpf());

            stmtExcluir.executeUpdate();
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = stmtLista.executeQuery()) {
            
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String endereco = rs.getString("endereco");
                
              
                Cliente cliente = new Cliente(nome, sobrenome, cpf, rg, endereco);
                cliente.setId(id); 
                
                clientes.add(cliente);
            }
            return clientes;
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Cliente getById(long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtBusca = connection.prepareStatement(SELECT_BY_ID)) {
            
            stmtBusca.setLong(1, id);
            
            try (ResultSet rs = stmtBusca.executeQuery()) {
                if (rs.next()) {
                
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String cpf = rs.getString("cpf");
                    String rg = rs.getString("rg");
                    String endereco = rs.getString("endereco");
                    
                    Cliente cliente = new Cliente(nome, sobrenome, cpf, rg, endereco);
                    cliente.setId(rs.getLong("id"));
                    return cliente;
                }
            }
            return null;
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Cliente getByCpf(String cpf) {
        // Normalize CPF: remove non-digit characters and trim spaces
        String normalizedCpf = cpf.replaceAll("[^0-9]", "").trim();
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtBusca = connection.prepareStatement(SELECT_BY_CPF)) {
            
            stmtBusca.setString(1, normalizedCpf);
            
            try (ResultSet rs = stmtBusca.executeQuery()) {
                if (rs.next()) {
                    
                    long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String rg = rs.getString("rg");
                    String endereco = rs.getString("endereco");
                    
                    Cliente cliente = new Cliente(nome, sobrenome, normalizedCpf, rg, endereco);
                    cliente.setId(id);
                    return cliente;
                }
            }
            return null;
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao buscar cliente por CPF: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(TRUNCATE)) {
            
            stmt.executeUpdate();
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao limpar a tabela cliente: " + e.getMessage(), e);
        }
    }              
}