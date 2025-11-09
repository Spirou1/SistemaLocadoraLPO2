package com.suaempresa.locadora.model.dao;

import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.Locacao;
import com.suaempresa.locadora.model.Veiculo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocacaoDAOSQL implements LocacaoDAO {

    private static final String INSERT = "INSERT INTO locacao (dias, valor_total, data_locacao, cliente_id, veiculo_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT l.id, l.dias, l.valor_total, l.data_locacao, l.cliente_id, l.veiculo_id, " +
                                            "c.nome AS cliente_nome, c.sobrenome AS cliente_sobrenome, c.cpf AS cliente_cpf, c.rg AS cliente_rg, c.endereco AS cliente_endereco, " +
                                            "v.placa AS veiculo_placa " + // Assuming placa is enough for vehicle identification
                                            "FROM locacao l " +
                                            "JOIN cliente c ON l.cliente_id = c.id " +
                                            "JOIN veiculo v ON l.veiculo_id = v.id";
    private static final String SELECT_BY_ID = "SELECT l.id, l.dias, l.valor_total, l.data_locacao, l.cliente_id, l.veiculo_id, " +
                                            "c.nome AS cliente_nome, c.sobrenome AS cliente_sobrenome, c.cpf AS cliente_cpf, c.rg AS cliente_rg, c.endereco AS cliente_endereco, " +
                                            "v.placa AS veiculo_placa " +
                                            "FROM locacao l " +
                                            "JOIN cliente c ON l.cliente_id = c.id " +
                                            "JOIN veiculo v ON l.veiculo_id = v.id " +
                                            "WHERE l.id = ?";
    private static final String UPDATE = "UPDATE locacao SET dias=?, valor_total=?, data_locacao=?, cliente_id=?, veiculo_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM locacao WHERE id=?";
    private static final String TRUNCATE = "TRUNCATE TABLE locacao RESTART IDENTITY";

    @Override
    public void insert(Locacao locacao) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, locacao.getDias());
            stmt.setDouble(2, locacao.getValorTotal());
            stmt.setDate(3, new java.sql.Date(locacao.getDataLocacao().getTimeInMillis()));
            stmt.setLong(4, locacao.getCliente().getId());
            stmt.setLong(5, locacao.getVeiculo().getId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    locacao.setId(rs.getLong(1));
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao inserir locação: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Locacao> getAll() {
        List<Locacao> locacoes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                locacoes.add(extractLocacaoFromResultSet(rs));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao buscar todas as locações: " + e.getMessage(), e);
        }
        return locacoes;
    }

    @Override
    public Locacao getById(long id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractLocacaoFromResultSet(rs);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao buscar locação por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void update(Locacao locacao) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            stmt.setInt(1, locacao.getDias());
            stmt.setDouble(2, locacao.getValorTotal());
            stmt.setDate(3, new java.sql.Date(locacao.getDataLocacao().getTimeInMillis()));
            stmt.setLong(4, locacao.getCliente().getId());
            stmt.setLong(5, locacao.getVeiculo().getId());
            stmt.setLong(6, locacao.getId());

            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao atualizar locação: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Locacao locacao) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {

            stmt.setLong(1, locacao.getId());
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao deletar locação: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void deleteAll() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(TRUNCATE)) {
            
            stmt.executeUpdate();
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao limpar a tabela locacao: " + e.getMessage(), e);
        }
    }

    private Locacao extractLocacaoFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        int dias = rs.getInt("dias");
        double valorTotal = rs.getDouble("valor_total");
        
        Calendar dataLocacao = Calendar.getInstance();
        dataLocacao.setTime(rs.getDate("data_locacao"));

        long clienteId = rs.getLong("cliente_id");
        String clienteNome = rs.getString("cliente_nome");
        String clienteSobrenome = rs.getString("cliente_sobrenome");
        String clienteCpf = rs.getString("cliente_cpf");
        String clienteRg = rs.getString("cliente_rg");
        String clienteEndereco = rs.getString("cliente_endereco");
        Cliente cliente = new Cliente(clienteNome, clienteSobrenome, clienteCpf, clienteRg, clienteEndereco);
        cliente.setId(clienteId);
        
        long veiculoId = rs.getLong("veiculo_id");
        String veiculoPlaca = rs.getString("veiculo_placa");
    
        Veiculo veiculo = new Veiculo(null, null, null, 0, veiculoPlaca, 0) {
            @Override
            public double getValorDiariaLocacao() {
                throw new UnsupportedOperationException("Not supported yet."); 
            }
        };
        veiculo.setId(veiculoId);

        Locacao locacao = new Locacao(dias, valorTotal, dataLocacao, cliente, veiculo);
        locacao.setId(id);
        return locacao;
    }
}
