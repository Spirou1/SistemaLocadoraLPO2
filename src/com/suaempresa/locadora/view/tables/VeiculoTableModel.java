package com.suaempresa.locadora.ui.tables;

import com.suaempresa.locadora.model.Automovel; 
import com.suaempresa.locadora.model.Motocicleta;
import com.suaempresa.locadora.model.Van;
import com.suaempresa.locadora.model.Veiculo; 
import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale; 

public class VeiculoTableModel extends AbstractTableModel {

    private List<Veiculo> veiculos;
    
    private String[] colunas;
    private NumberFormat currencyFormat;

    
    public VeiculoTableModel(List<Veiculo> veiculos, boolean paraLocacao) {
        this.veiculos = new ArrayList<>(veiculos);
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        if (paraLocacao) {
            this.colunas = new String[]{"Placa", "Marca", "Modelo", "Ano", "Preço da Diária"};
        } else { 
            this.colunas = new String[]{"Placa", "Marca", "Modelo", "Ano", "Preço para Venda"};
        }
    }

    
    public VeiculoTableModel(List<Veiculo> veiculos, String tipoTabela) {
         this.veiculos = new ArrayList<>(veiculos);
         this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

         if ("DEVOLUCAO".equals(tipoTabela)) {
             this.colunas = new String[]{"Nome do Cliente", "Placa", "Marca", "Modelo", "Ano", "Data Locação", "Preço Diária", "Quantidade de dias locado", "Valor Locação"};
         } else { 
             this.colunas = new String[]{"Placa", "Marca", "Modelo", "Ano", "Preço"};
         }
    }


    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = new ArrayList<>(veiculos);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return veiculos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = veiculos.get(rowIndex);

        switch (colunas[columnIndex]) { 
            case "Placa": return veiculo.getPlaca();
            case "Marca": return veiculo.getMarca().name();
            case "Modelo":
                if (veiculo instanceof Automovel) {
                    return ((Automovel) veiculo).getModelo().name();
                } else if (veiculo instanceof Motocicleta) {
                    return ((Motocicleta) veiculo).getModelo().name();
                } else if (veiculo instanceof Van) {
                    return ((Van) veiculo).getModelo().name();
                }
                return ""; 
            case "Ano": return String.format("%04d", veiculo.getAno()); 
            case "Preço da Diária": return currencyFormat.format(veiculo.getValorDiariaLocacao()); 
            case "Preço para Venda": return currencyFormat.format(veiculo.getValorParaVenda());
            case "Nome do Cliente":
                return veiculo.getLocacao() != null ? veiculo.getLocacao().getCliente().getNome() + " " + veiculo.getLocacao().getCliente().getSobrenome() : "";
            case "Data Locação":
                return veiculo.getLocacao() != null ? String.format("%02d/%02d/%d", 
                                                                    veiculo.getLocacao().getData().get(Calendar.DAY_OF_MONTH),
                                                                    veiculo.getLocacao().getData().get(Calendar.MONTH) + 1, 
                                                                    veiculo.getLocacao().getData().get(Calendar.YEAR)) : "";
            case "Quantidade de dias locado":
                return veiculo.getLocacao() != null ? veiculo.getLocacao().getDias() : 0;
            case "Valor Locação":
                return veiculo.getLocacao() != null ? currencyFormat.format(veiculo.getLocacao().getValor()) : "";

            default: return null;
        }
    }

    public Veiculo getVeiculoAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < veiculos.size()) {
            return veiculos.get(rowIndex);
        }
        return null;
    }
}