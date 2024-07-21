-- Banco de Dados
CREATE DATABASE IF NOT EXISTS lab_4_imobiliaria;
USE lab_4_imobiliaria;

-- Tabela Cliente
CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    telefone VARCHAR(12),
    email VARCHAR(100),
    dt_nascimento DATE
);

-- Tabela Imovel
CREATE TABLE IF NOT EXISTS Imovel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_proprietario INT NOT NULL,
    logradouro VARCHAR(200),
    bairro VARCHAR(45),
    cep VARCHAR(10),
    metragem INT,
    dormitorios TINYINT,
    banheiros TINYINT,
    suites TINYINT,
    vagas_garagem TINYINT,
    valor_aluguel_sugerido DECIMAL(10,2),
    obs TEXT
);

-- Tabela Profissional
CREATE TABLE IF NOT EXISTS Profissional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    profissao VARCHAR(45),
    telefone1 VARCHAR(12),
    telefone2 VARCHAR(12),
    valor_hora DECIMAL(10,2),
    obs TEXT
);

-- Tabela Locacao
CREATE TABLE IF NOT EXISTS Locacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_imovel INT NOT NULL,
    id_inquilino INT NOT NULL,
    valor_aluguel DECIMAL(10,4),
    percentual_multa DECIMAL(5,2),
    dia_vencimento TINYINT,
    data_inicio DATE,
    data_fim DATE,
    ativo BOOLEAN,
    obs TEXT,
    FOREIGN KEY (id_imovel) REFERENCES Imovel(id),
    FOREIGN KEY (id_inquilino) REFERENCES Cliente(id)
);

-- Tabela ServicoImovel
CREATE TABLE IF NOT EXISTS ServicoImovel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_profissional INT NOT NULL,
    id_imovel INT NOT NULL,
    data_servico DATE,
    valor_total DECIMAL(10,2),
    obs TEXT,
    FOREIGN KEY (id_profissional) REFERENCES Profissional(id),
    FOREIGN KEY (id_imovel) REFERENCES Imovel(id)
);

-- Tabela Aluguel
CREATE TABLE IF NOT EXISTS Aluguel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_locacao INT NOT NULL,
    data_vencimento DATE,
    valor_pago DECIMAL(10,2),
    data_pagamento DATE,
    obs TEXT,
    FOREIGN KEY (id_locacao) REFERENCES Locacao(id)
);

-- Tabela Cliente
INSERT INTO Cliente (nome, cpf, telefone, email, dt_nascimento) VALUES
('João Silva', '12345678901', '999999999', 'joao.silva@example.com', '1985-01-01'),
('Maria Souza', '98765432100', '999888777', 'maria.souza@example.com', '1990-02-02'),
('Carlos Pereira', '11223344556', '999111222', 'carlos.pereira@example.com', '1975-03-03');

-- Tabela Imovel
INSERT INTO Imovel (id_proprietario, logradouro, bairro, cep, metragem, dormitorios, banheiros, suites, vagas_garagem, valor_aluguel_sugerido, obs) VALUES
(1, 'Rua A', 'Centro', '12345678', 100, 3, 2, 1, 2, 1500.00, 'Imóvel bem localizado.'),
(1, 'Rua B', 'Centro', '87654321', 80, 2, 1, 1, 1, 1200.00, 'Imóvel novo.'),
(1, 'Rua C', 'Centro', '11223344', 120, 3, 2, 1, 2, 2000.00, 'Imóvel espaçoso.');

-- Tabela Profissional
INSERT INTO Profissional (nome, profissao, telefone1, valor_hora, obs) VALUES
('Ana Martins', 'Eletricista', '999123456', 50.00, 'Profissional com 5 anos de experiência.'),
('Bruno Lima', 'Pintor', '999765432', 30.00, 'Profissional com 3 anos de experiência.');

-- Tabela Locacao
INSERT INTO Locacao (id_imovel, id_inquilino, valor_aluguel, percentual_multa, dia_vencimento, data_inicio, ativo, obs) VALUES
(1, 1, 1500.00, 0.33, 5, '2023-01-01', TRUE, 'Primeira locação'),
(2, 2, 1200.00, 0.33, 10, '2023-02-01', TRUE, 'Segunda locação'),
(3, 3, 2000.00, 0.33, 15, '2023-03-01', TRUE, 'Terceira locação');

-- Tabela ServicoImovel
INSERT INTO ServicoImovel (id_profissional, id_imovel, data_servico, valor_total, obs) VALUES
(1, 1, '2023-01-15', 500.00, 'Instalação elétrica completa.'),
(2, 2, '2023-02-20', 700.00, 'Pintura interna e externa.');

-- Tabela Aluguel
INSERT INTO Aluguel (id_locacao, data_vencimento, valor_pago, data_pagamento, obs) VALUES
(1, '2023-01-05', 1500.00, '2023-01-05', 'Pagamento em dia.'),
(2, '2023-02-10', 1200.00, '2023-02-12', 'Pagamento com atraso.'),
(3, '2023-03-15', 2000.00, '2023-03-15', 'Pagamento em dia.');
