package com.suaempresa.locadora.model.dao;

import com.suaempresa.locadora.model.Veiculo;
import com.suaempresa.locadora.model.Estado;
import com.suaempresa.locadora.model.Marca;
import com.suaempresa.locadora.model.Categoria;
import java.util.List;

public interface VeiculoDAO extends Dao<Veiculo> {
    
    Veiculo getByPlaca(String placa);
    
    List<Veiculo> getByEstado(Estado estado);
    
    List<Veiculo> getByTipo(String tipo);
    
    List<Veiculo> filtrarVeiculos(String tipo, Marca marca, Categoria categoria);
}