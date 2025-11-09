-- Inserção de dados de teste para o sistema de locadora

-- Clientes de teste
INSERT INTO cliente (nome, sobrenome, cpf, rg, endereco) VALUES
('João', 'Silva', '111.111.111-11', '11.111.111-1', 'Rua A, 123, Cidade X'),
('Maria', 'Souza', '222.222.222-22', '22.222.222-2', 'Avenida B, 456, Cidade Y'),
('Pedro', 'Santos', '333.333.333-33', '33.333.333-3', 'Travessa C, 789, Cidade Z'),
('Ana', 'Oliveira', '444.444.444-44', '44.444.444-4', 'Estrada D, 101, Cidade W');

-- Veículos de teste (todos DISPONIVEL inicialmente)

-- Automóveis
INSERT INTO veiculo (placa, ano, valor_compra, tipo_veiculo, marca, estado, categoria) VALUES
('ABC-1234', 2020, 50000.00, 'AUTOMOVEL', 'FIAT', 'DISPONIVEL', 'POPULAR');
INSERT INTO automovel (id, modelo) VALUES ((SELECT id FROM veiculo WHERE placa = 'ABC-1234'), 'Palio');

INSERT INTO veiculo (placa, ano, valor_compra, tipo_veiculo, marca, estado, categoria) VALUES
('DEF-5678', 2022, 80000.00, 'AUTOMOVEL', 'VW', 'DISPONIVEL', 'INTERMEDIARIO');
INSERT INTO automovel (id, modelo) VALUES ((SELECT id FROM veiculo WHERE placa = 'DEF-5678'), 'Gol');

-- Motocicletas
INSERT INTO veiculo (placa, ano, valor_compra, tipo_veiculo, marca, estado, categoria) VALUES
('GHI-9012', 2021, 15000.00, 'MOTOCICLETA', 'HONDA', 'DISPONIVEL', 'POPULAR');
INSERT INTO motocicleta (id, modelo) VALUES ((SELECT id FROM veiculo WHERE placa = 'GHI-9012'), 'CG125');

INSERT INTO veiculo (placa, ano, valor_compra, tipo_veiculo, marca, estado, categoria) VALUES
('JKL-3456', 2023, 30000.00, 'MOTOCICLETA', 'YAMAHA', 'DISPONIVEL', 'INTERMEDIARIO');
INSERT INTO motocicleta (id, modelo) VALUES ((SELECT id FROM veiculo WHERE placa = 'JKL-3456'), 'FAZER_250');

-- Vans
INSERT INTO veiculo (placa, ano, valor_compra, tipo_veiculo, marca, estado, categoria) VALUES
('MNO-7890', 2019, 120000.00, 'VAN', 'FIAT', 'DISPONIVEL', 'LUXO');
INSERT INTO van (id, modelo) VALUES ((SELECT id FROM veiculo WHERE placa = 'MNO-7890'), 'DUCATO');

INSERT INTO veiculo (placa, ano, valor_compra, tipo_veiculo, marca, estado, categoria) VALUES
('PQR-1122', 2020, 90000.00, 'VAN', 'RENAULT', 'DISPONIVEL', 'INTERMEDIARIO');
INSERT INTO van (id, modelo) VALUES ((SELECT id FROM veiculo WHERE placa = 'PQR-1122'), 'MASTER');
