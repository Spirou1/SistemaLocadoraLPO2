public static void main(String args[]) {
    

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorVeiculos gv = new GerenciadorVeiculos();
        
        
        Cliente cliente1 = new Cliente("Joao", "Silva", "123456789", "111.222.333-44", "Rua A, 100");
        gc.adicionarCliente(cliente1);

        Cliente cliente2 = new Cliente("Maria", "Souza", "987654321", "555.666.777-88", "Av. B, 200");
        gc.adicionarCliente(cliente2);

        Cliente cliente3 = new Cliente("Pedro", "Almeida", "456789123", "999.888.777-66", "Travessa C, 300");
        gc.adicionarCliente(cliente3);
        
        Automovel carro1 = new Automovel(
            Marca.FIAT,
            Estado.DISPONIVEL, 
            Categoria.POPULAR,
            30000.0, "ABC-1234", 2020, ModeloAutomovel.Civic
        );
        gv.adicionarVeiculo(carro1);
        
        Automovel carro2 = new Automovel(
            Marca.VW,
            Estado.DISPONIVEL, 
            Categoria.INTERMEDIARIO,
            55000.0, "XYZ-5678", 2022, ModeloAutomovel.Gol
        );
        gv.adicionarVeiculo(carro2);
        
        Motocicleta moto1 = new Motocicleta(
            Marca.HONDA,
            Estado.NOVO, 
            Categoria.LUXO,
            25000.0, "MOTO-9012", 2023, ModeloMotocicleta.CBR500
        );
        gv.adicionarVeiculo(moto1);
        
        Automovel carro5 = new Automovel(
        Marca.HONDA,
        Estado.DISPONIVEL,
        Categoria.INTERMEDIARIO,
        80000.0, "AAA-1234", 2022, ModeloAutomovel.Civic);
        gv.adicionarVeiculo(carro5);
        
        TelaPrincipal frame = new TelaPrincipal(gc, gv);
        frame.setVisible(true);
        
        if (frame.homePanelInstancia != null) { 
            frame.homePanelInstancia.carregarEstatisticas();
        } 
    });
}