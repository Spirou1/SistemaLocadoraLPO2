package com.suaempresa.locadora.controller;

import com.suaempresa.locadora.model.Cliente;
import com.suaempresa.locadora.model.Locacao;
import com.suaempresa.locadora.model.Veiculo;
import com.suaempresa.locadora.model.dao.ClienteDAO;
import com.suaempresa.locadora.model.dao.DaoFactory;
import com.suaempresa.locadora.model.dao.DaoType;
import com.suaempresa.locadora.model.dao.LocacaoDAO;
import com.suaempresa.locadora.model.dao.VeiculoDAO;
import com.suaempresa.locadora.view.VeiculoLocacaoPanel;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class LocacaoController {

    private final VeiculoLocacaoPanel view;
    private final LocacaoDAO locacaoDAO;
    private final ClienteDAO clienteDAO;
    private final VeiculoDAO veiculoDAO;

    public LocacaoController(VeiculoLocacaoPanel view) {
        this.view = view;
        DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoType.SQL);
        this.locacaoDAO = daoFactory.getLocacaoDAO();
        this.clienteDAO = daoFactory.getClienteDAO();
        this.veiculoDAO = daoFactory.getVeiculoDAO();
    }

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LocacaoController.class.getName());

    public void initController() {
        view.getBtnLocar().addActionListener(e -> locarVeiculo());
        view.getBtnBuscarCliente().addActionListener(e -> buscarCliente());
        view.getBtnBuscarVeiculo().addActionListener(e -> buscarVeiculo());
        loadInitialData(); // Load data when controller is initialized
    }
    
    public void loadInitialData() {
        loadAllClients();
        loadAllAvailableVehicles();
    }

    private void loadAllClients() {
        try {
            List<Cliente> clientes = clienteDAO.getAll();
            view.setClientesTableData(clientes);
        } catch (Exception e) {
            view.showErrorMessage("Erro ao carregar todos os clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void loadAllAvailableVehicles() {
        try {
            List<Veiculo> veiculos = veiculoDAO.getAll();
            logger.info("Total vehicles retrieved from DAO: " + veiculos.size());
            List<Veiculo> availableVehicles = new java.util.ArrayList<>();
            for (Veiculo v : veiculos) {
                if (v.getEstado() == com.suaempresa.locadora.model.Estado.DISPONIVEL) {
                    availableVehicles.add(v);
                }
            }
            logger.info("Available vehicles after filtering: " + availableVehicles.size());
            view.setVeiculosTableData(availableVehicles);
        } catch (Exception e) {
            view.showErrorMessage("Erro ao carregar veículos disponíveis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void buscarCliente() {
        String cpf = view.getCpfCliente();
        logger.info("Searching for client with CPF: " + cpf);
        if (cpf.isEmpty()) {
            loadAllClients(); // If search field is empty, show all clients
            return;
        }
        try {
            Cliente cliente = clienteDAO.getByCpf(cpf);
            logger.info("Client found by CPF: " + (cliente != null ? cliente.getNome() : "null"));
            if (cliente != null) {
                view.setClientesTableData(java.util.Arrays.asList(cliente));
            } else {
                view.setClientesTableData(new java.util.ArrayList<>());
                view.showWarningMessage("Cliente com CPF " + cpf + " não encontrado.");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erro ao buscar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void buscarVeiculo() {
        String placa = view.getPlacaVeiculo();
        if (placa.isEmpty()) {
            loadAllAvailableVehicles(); // If search field is empty, show all available vehicles
            return;
        }
        try {
            Veiculo veiculo = veiculoDAO.getByPlaca(placa);
            if (veiculo != null && veiculo.getEstado() == com.suaempresa.locadora.model.Estado.DISPONIVEL) {
                view.setVeiculosTableData(java.util.Arrays.asList(veiculo));
            } else {
                view.setVeiculosTableData(new java.util.ArrayList<>());
                view.showWarningMessage("Veículo com placa " + placa + " não encontrado ou não disponível.");
            }
        } catch (Exception e) {
            view.showErrorMessage("Erro ao buscar veículo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void locarVeiculo() {
        try {
            Cliente cliente = view.getClienteSelecionado();
            Veiculo veiculo = view.getVeiculoSelecionado();
            int diasLocacao = view.getDiasLocacao();

            if (diasLocacao <= 0) {
                view.showWarningMessage("Os dias de locação devem ser maiores que zero.");
                return;
            }

         
            if (cliente == null) {
                String cpfCliente = view.getCpfCliente();
                if (cpfCliente.isEmpty()) {
                    view.showWarningMessage("Selecione um cliente na tabela ou digite o CPF para buscar.");
                    return;
                }
                cliente = clienteDAO.getByCpf(cpfCliente);
                if (cliente == null) {
                    view.showErrorMessage("Cliente com CPF " + cpfCliente + " não encontrado.");
                    return;
                }
            }

 
            if (veiculo == null) {
                String placaVeiculo = view.getPlacaVeiculo();
                if (placaVeiculo.isEmpty()) {
                    view.showWarningMessage("Selecione um veículo na tabela ou digite a placa para buscar.");
                    return;
                }
                veiculo = veiculoDAO.getByPlaca(placaVeiculo);
                if (veiculo == null) {
                    view.showErrorMessage("Veículo com placa " + placaVeiculo + " não encontrado.");
                    return;
                }
            }
            
            if (veiculo.getEstado() != com.suaempresa.locadora.model.Estado.DISPONIVEL) {
                view.showErrorMessage("Veículo com placa " + veiculo.getPlaca() + " não está disponível para locação. Estado atual: " + veiculo.getEstado());
                return;
            }

            Calendar dataLocacao = Calendar.getInstance();
            veiculo.locar(diasLocacao, dataLocacao, cliente); 

         
            locacaoDAO.insert(veiculo.getLocacao());
            veiculoDAO.update(veiculo); 

            view.showSuccessMessage("Veículo locado com sucesso para o cliente " + cliente.getNome() + " " + cliente.getSobrenome() + "!");
            view.limparCampos();
            loadInitialData();
        } catch (Exception e) {
            view.showErrorMessage("Erro ao locar veículo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
