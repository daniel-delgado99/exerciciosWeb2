drop database web2;
create database web2;

use web2;

create table tb_tipo_usuario (
id_tipo_usuario int primary key AUTO_INCREMENT,
nome_tipo_usuario varchar(50)
);

create table tb_usuario(
id_usuario int primary key AUTO_INCREMENT,
nome_usuario varchar(100),
cpf_usuario char(14) unique,
email_usuario varchar(100) unique,
senha_usuario varchar(50),
data_usuario datetime, -- corrigir no java
rua_usuario varchar(100),
nr_usuario integer,
cep_usuario char(10),
id_cidade int,
id_tipo_usuario int
);

-- create table tb_cliente (
-- id_cliente int primary key AUTO_INCREMENT,
-- id_usuario int
-- );

-- create table tb_funcionario (
-- id_funcionario int primary key AUTO_INCREMENT,
-- id_usuario int
-- );

-- create table tb_gerente (
-- id_gerente int primary key AUTO_INCREMENT,
-- id_usuario int
-- );

create table tb_cidade(
id_cidade int primary key AUTO_INCREMENT,
nome_cidade varchar(50),
id_estado int
);

create table tb_estado(
id_estado int primary key AUTO_INCREMENT,
nome_estado varchar(50),
sigla_estado char(2)
);

create table tb_categoria_produto (
id_categoria_produto int primary key AUTO_INCREMENT,
nome_categoria_produto varchar(50)
);

create table tb_produto (
id_produto int primary key AUTO_INCREMENT,
nome_produto varchar(100),
desc_produto varchar(255),
peso_produto int,
id_categoria_produto int
);

create table tb_tipo_atendimento (
id_tipo_atendimento int primary key AUTO_INCREMENT,
nome_tipo_atendimento varchar(50)
);

create table tb_atendimento (
id_atendimento int primary key AUTO_INCREMENT,
dt_hr_atendimento char(20),
desc_atendimento varchar(255),
res_atendimento char(1),
solucao_atendimento varchar(255),
id_produto int,
id_tipo_atendimento int,
id_cliente int
);

-- Alter tables
ALTER TABLE tb_usuario ADD FOREIGN KEY (id_tipo_usuario) REFERENCES tb_tipo_usuario(id_tipo_usuario);

-- ALTER TABLE tb_cliente ADD FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario);
-- ALTER TABLE tb_funcionario ADD FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario);
-- ALTER TABLE tb_gerente ADD FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario);

ALTER TABLE tb_usuario ADD FOREIGN KEY (id_cidade) REFERENCES tb_cidade(id_cidade);
ALTER TABLE tb_cidade ADD FOREIGN KEY (id_estado) REFERENCES tb_estado(id_estado);

ALTER TABLE tb_produto ADD FOREIGN KEY (id_categoria_produto) REFERENCES tb_categoria_produto(id_categoria_produto) ON DELETE CASCADE;

ALTER TABLE tb_atendimento ADD FOREIGN KEY (id_produto) REFERENCES tb_produto(id_produto) ON DELETE CASCADE;
ALTER TABLE tb_atendimento ADD FOREIGN KEY (id_tipo_atendimento) REFERENCES tb_tipo_atendimento(id_tipo_atendimento);
ALTER TABLE tb_atendimento ADD FOREIGN KEY (id_cliente) REFERENCES tb_usuario(id_usuario) ON DELETE CASCADE;

-- inserts
insert into tb_tipo_atendimento (nome_tipo_atendimento) values
("Reclamação"), 
("Sugestão"),
("Elogio"),
("Dúvida");

insert into tb_tipo_usuario (nome_tipo_usuario) values
("Cliente"),
("Funcionário"),
("Gerente");

insert into tb_categoria_produto (nome_categoria_produto) values
("Comida"),
("Louça"),
("Eletrodomésticos"),
("Cama, mesa e banho"),
("Jogos"),
("Material de escritório");

insert into tb_produto (nome_produto, desc_produto, peso_produto, id_categoria_produto) values
("Laranja",         "Laranja bem fresquinha", 300, 1),
("Abridor de latas","Ele abre latas, eu acho", 100, 2),
("Geladeira",       "Para esfriar as coisas", 60000, 3),
("Crédito no LOL",  "Para comprar aquela skinzinha da Miss Fortune Agente Secreta", 0, 5),
("Cortina",         "Porque tem coisas que os vizinhos não podem ver", 5000, 4),
("Toddy",           "Para te dar energia no seu dia-a-dia", 500, 1),
("Caneta",          "Serve para mandar aquela cartinha de amor pra morena", 100, 6),
("Penal",           "Para guardar a caneta", 300, 6),
("Prato",           "Para comer a laranja", 250, 2),
("Copo",            "Para tomar o suco da laranja", 180, 2),
("Fruta do conde",  "Eu juro que não sei nem o que é", 400, 1);

insert into tb_estado(nome_estado, sigla_estado) VALUES
("Paraná", "PR"),
("Pará", "PA"),
("Tocantins", "TO"),
("Bahia", "BA"),
("São Paulo", "SP"),
("Rio de Janeiro", "RJ"),
("Amazonas", "AM"),
("Roraima", "RR"),
("Santa Catarina", "SC"),
("Rio Grande de Sul", "RS");

insert into tb_cidade(nome_cidade, id_estado) VALUES 
("Curitiba", 1),
("Colombo", 1),
("Rolândia", 1),
("Adrianópolis", 1),
("Pinhais", 1),
("Belém", 2),
("Palmas", 3),
("Salvador", 4),
("São Paulo", 5),
("Rio de Janeiro", 6),
("Manaus", 7),
("Boa Vista", 8),
("Florianópolis", 9),
("Porto Alegre", 10);

insert into tb_usuario (
    cpf_usuario, nome_usuario, email_usuario,
    senha_usuario, data_usuario, rua_usuario, nr_usuario,
    cep_usuario, id_cidade, id_tipo_usuario
) values 
("12345678910", "Braian Viana",     "braian@braian",    "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 1),
("12345678911", "Daniel Delgado",   "daniel@daniel",    "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 2),
("12345678912", "Gustavo Lara",     "gustavo@gustavo",  "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 3),
("12345678913", "Matheus Fernandes","matheus@matheus",  "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 1),
("12345678914", "Rafael Damiani",   "rafael@rafael",    "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 2),
("12345678915", "Joao Silva",       "joao@joao",        "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 3),
("12345678916", "Maria Souza",      "maria@maria",      "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 1),
("12345678917", "Jose Carlos",      "jose@jose",        "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 2),
("12345678918", "Geraldo Martins",  "geraldo@geraldo",  "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 3),
("12345678919", "Outro nome",       "outro@outro",      "senha", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 1),
("12345678920", "Poderoso A",       "a@a",              "a",     "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 1),
("12345678921", "Poderoso B",       "b@b",              "b",     "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 2),
("12345678922", "Poderoso C",       "c@c",              "c",     "2018-01-01 00:00", "Rua 1", 1, "8000000", 1, 3);

insert into tb_atendimento(dt_hr_atendimento, desc_atendimento, res_atendimento, solucao_atendimento, id_produto, id_tipo_atendimento, id_cliente) values
('2018-01-01 12:00:00', "Um atendimento muito legal", 'S', "Obrigado", 1, 3, 1),
('2018-01-01 12:00:00', "Não gostei, achei meio bosta", 'S', "Ninguém liga pra sua opinião", 4, 1, 4),
('2018-01-01 12:00:00', "Acho que vcs podiam fazer outra coisa", 'S', "Quem sabe um dia", 6, 2, 7),
('2018-01-01 12:00:00', "Oq vcs fazem?", 'N', null, 3, 4, 10);

-- select * from tb_categoria_produto;
-- select * from tb_produto;
-- select * from tb_usuario;
-- select * from tb_cidade;
-- select * from tb_atendimento;