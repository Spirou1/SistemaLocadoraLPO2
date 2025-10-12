package com.suaempresa.locadora.model.dao;

import com.suaempresa.locadora.model.Veiculo;
import com.suaempresa.locadora.model.Estado;
import com.suaempresa.locadora.model.Marca;
import com.suaempresa.locadora.model.Categoria;
import com.suaempresa.locadora.model.Automovel;
import com.suaempresa.locadora.model.Motocicleta;
import com.suaempresa.locadora.model.Van;
import com.suaempresa.locadora.model.ModeloAutomovel;
import com.suaempresa.locadora.model.ModeloMotocicleta;
import com.suaempresa.locadora.model.ModeloVan;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAOSQL implements VeiculoDAO {

  
    private static final String INSERT_VEICULO =
        "INSERT INTO veiculo (placa, ano, valor_compra, tipo_veiculo, marca, estado, categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_VEICULO =
        "UPDATE veiculo SET ano=?, valor_compra=?, estado=?, categoria=? WHERE id=?";
    private static final String DELETE_VEICULO =
        "DELETE FROM veiculo WHERE id=?";
    private static final String SELECT_ALL_BASE =
        "SELECT v.id, v.placa, v.ano, v.valor_compra, v.tipo_veiculo, v.marca, v.estado, v.categoria, " +
        "a.modelo AS modelo_auto, m.modelo AS modelo_moto, va.modelo AS modelo_van " +
        "FROM veiculo v " +
        "LEFT JOIN automovel a ON v.id = a.id " +
        "LEFT JOIN motocicleta m ON v.id = m.id " +
        "LEFT JOIN van va ON v.id = va.id";
    private static final String SELECT_BY_ID = SELECT_ALL_BASE + " WHERE v.id=?";
    private static final String SELECT_BY_PLACA = SELECT_ALL_BASE + " WHERE v.placa=?";
    private static final String TRUNCATE_VEICULO = "TRUNCATE TABLE veiculo CASCADE"; // CASCADE para limpar subclasses
    
  
    private static final String INSERT_AUTOMOVEL = "INSERT INTO automovel (id, modelo) VALUES (?, ?)";
    private static final String INSERT_MOTOCICLETA = "INSERT INTO motocicleta (id, modelo) VALUES (?, ?)";
    private static final String INSERT_VAN = "INSERT INTO van (id, modelo) VALUES (?, ?)";
    
    
    public static VeiculoDAOSQL instance;

    public VeiculoDAOSQL() {}
    
    public static VeiculoDAOSQL getInstance() {
        if (instance == null) {
            instance = new VeiculoDAOSQL();
        }
        return instance;
    }
    

  
    private Veiculo mapResultSetToVeiculo(ResultSet rs) throws SQLException {
       
        long id = rs.getLong("id");
        String placa = rs.getString("placa");
        int ano = rs.getInt("ano");
        double valorCompra = rs.getDouble("valor_compra");
        
       
        Marca marca = Marca.valueOf(rs.getString("marca"));
        Estado estado = Estado.valueOf(rs.getString("estado"));
        Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
        String tipoVeiculo = rs.getString("tipo_veiculo");
        
        Veiculo veiculo = null;

       
        switch (tipoVeiculo) {
            case "AUTOMOVEL":
                ModeloAutomovel modeloAuto = ModeloAutomovel.valueOf(rs.getString("modelo_auto"));
                veiculo = new Automovel(marca, estado, categoria, valorCompra, placa, ano, modeloAuto);
                break;
            case "MOTOCICLETA":
                ModeloMotocicleta modeloMoto = ModeloMotocicleta.valueOf(rs.getString("modelo_moto"));
                veiculo = new Motocicleta(marca, estado, categoria, valorCompra, placa, ano, modeloMoto);
                break;
            case "VAN":
                ModeloVan modeloVan = ModeloVan.valueOf(rs.getString("modelo_van"));
                veiculo = new Van(marca, estado, categoria, valorCompra, placa, ano, modeloVan);
                break;
            default:
               
                throw new SQLException("Tipo de veículo desconhecido no banco: " + tipoVeiculo);
        }
        
       
        
        return veiculo;
    }
    
    
 

    @Override
    public void insert(Veiculo veiculo) {
    Connection connection = null;
    try {
        connection = ConnectionFactory.getConnection();
        connection.setAutoCommit(false); 

       
        try (PreparedStatement stmtBase = connection.prepareStatement(INSERT_VEICULO, Statement.RETURN_GENERATED_KEYS)) {
            
            stmtBase.setString(1, veiculo.getPlaca());
            stmtBase.setInt(2, veiculo.getAno());
            stmtBase.setDouble(3, veiculo.valorDeCompra);

            String tipo = veiculo.getClass().getSimpleName().toUpperCase();
            stmtBase.setString(4, tipo);

            stmtBase.setString(5, veiculo.getMarca().name());
            stmtBase.setString(6, veiculo.getEstado().name());
            stmtBase.setString(7, veiculo.getCategoria().name());

            stmtBase.executeUpdate();

           
            long veiculoId;
            try (ResultSet rs = stmtBase.getGeneratedKeys()) {
                if (rs.next()) {
                    veiculoId = rs.getLong(1);
                  
                    veiculo.setId(veiculoId); 
                } else {
                    throw new SQLException("Falha ao obter ID do veículo.");
                }
            }

            
            if (veiculo instanceof Automovel) {
                Automovel auto = (Automovel) veiculo;
               
                try (PreparedStatement stmtSubclass = connection.prepareStatement(INSERT_AUTOMOVEL)) {
                    stmtSubclass.setLong(1, veiculoId);
                    stmtSubclass.setString(2, auto.getModelo().name());
                    stmtSubclass.executeUpdate();
                }
            } else if (veiculo instanceof Motocicleta) {
                Motocicleta moto = (Motocicleta) veiculo;
                try (PreparedStatement stmtSubclass = connection.prepareStatement(INSERT_MOTOCICLETA)) {
                    stmtSubclass.setLong(1, veiculoId);
                    stmtSubclass.setString(2, moto.getModelo().name());
                    stmtSubclass.executeUpdate();
                }
            } else if (veiculo instanceof Van) {
                Van van = (Van) veiculo;
                try (PreparedStatement stmtSubclass = connection.prepareStatement(INSERT_VAN)) {
                    stmtSubclass.setLong(1, veiculoId);
                    stmtSubclass.setString(2, van.getModelo().name());
                    stmtSubclass.executeUpdate();
                }
            }
        } 
        
        connection.commit(); 

    } catch (SQLException | IOException e) {
        
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fazer rollback: " + ex.getMessage(), ex);
            }
        }
        throw new RuntimeException("Erro ao inserir veículo: " + e.getMessage(), e);
    } finally {
       
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar conexão: " + ex.getMessage(), ex);
            }
        }
    }
}

    @Override
    public List<Veiculo> getAll() {
        List<Veiculo> veiculos = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(SELECT_ALL_BASE);
             ResultSet rs = stmtLista.executeQuery()) {
            
            while (rs.next()) {
                veiculos.add(mapResultSetToVeiculo(rs));
            }
            return veiculos;
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao listar veículos: " + e.getMessage(), e);
        }
    }

    @Override
    public Veiculo getById(long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtBusca = connection.prepareStatement(SELECT_BY_ID)) {
            
            stmtBusca.setLong(1, id);
            
            try (ResultSet rs = stmtBusca.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToVeiculo(rs);
                }
            }
            return null;
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao buscar veículo por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public Veiculo getByPlaca(String placa) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtBusca = connection.prepareStatement(SELECT_BY_PLACA)) {
            
            stmtBusca.setString(1, placa);
            
            try (ResultSet rs = stmtBusca.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToVeiculo(rs);
                }
            }
            return null;
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao buscar veículo por placa: " + e.getMessage(), e);
        }
    }
    
   

    @Override
    public void update(Veiculo veiculo) {
         throw new UnsupportedOperationException("Update de Veículo não implementado.");
    }
    
    @Override
    public void delete(Veiculo veiculo) {
        throw new UnsupportedOperationException("Delete de Veículo não implementado.");
    }
    
    @Override
    public void deleteAll() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(TRUNCATE_VEICULO)) {
            
            stmt.executeUpdate();
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Erro ao limpar a tabela veiculo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Veiculo> getByEstado(Estado estado) {
        throw new UnsupportedOperationException("Busca por estado não implementada.");
    }
    
    @Override
    public List<Veiculo> getByTipo(String tipo) {
        throw new UnsupportedOperationException("Busca por tipo não implementada.");
    }

    @Override
    public List<Veiculo> filtrarVeiculos(String tipo, Marca marca, Categoria categoria) {
        throw new UnsupportedOperationException("Filtro complexo de veículos não implementado.");
    }
}