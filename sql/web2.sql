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
data_usuario date,
rua_usuario varchar(100),
nr_usuario integer,
cep_usuario char(10),
id_cidade int,
id_tipo_usuario int
);

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
dt_hr_atendimento datetime,
desc_atendimento varchar(255),
res_atendimento char(1),
solucao_atendimento varchar(255),
id_produto int,
id_tipo_atendimento int,
id_cliente int
);

-- Alter tables
ALTER TABLE tb_usuario ADD FOREIGN KEY (id_tipo_usuario) REFERENCES tb_tipo_usuario(id_tipo_usuario);

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
("123.456.789-10", "Braian Viana",     "braian@braian",    "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 1),
("123.456.789-11", "Daniel Delgado",   "daniel@daniel",    "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 2),
("123.456.789-12", "Gustavo Lara",     "gustavo@gustavo",  "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 3),
("123.456.789-13", "Matheus Fernandes","matheus@matheus",  "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 1),
("123.456.789-14", "Rafael Damiani",   "rafael@rafael",    "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 2),
("123.456.789-15", "Joao Silva",       "joao@joao",        "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 3),
("123.456.789-16", "Maria Souza",      "maria@maria",      "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 1),
("123.456.789-17", "Jose Carlos",      "jose@jose",        "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 2),
("123.456.789-18", "Geraldo Martins",  "geraldo@geraldo",  "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 3),
("123.456.789-19", "Outro nome",       "outro@outro",      "E8D95A51F3AF4A3B134BF6BB680A213A", "2018-01-01", "Rua 1", 1, "8000-000", 1, 1),
("123.456.789-20", "Poderoso A",       "a@a",              "0CC175B9C0F1B6A831C399E269772661", "2018-01-01", "Rua 1", 1, "8000-000", 1, 1),
("123.456.789-21", "Poderoso B",       "b@b",              "92EB5FFEE6AE2FEC3AD71C777531578F", "2018-01-01", "Rua 1", 1, "8000-000", 1, 2),
("123.456.789-22", "Poderoso C",       "c@c",              "4A8A08F09D37B73795649038408B5F33", "2018-01-01", "Rua 1", 1, "8000-000", 1, 3);

insert into tb_atendimento(dt_hr_atendimento, desc_atendimento, res_atendimento, solucao_atendimento, id_produto, id_tipo_atendimento, id_cliente) values
('2018-01-01 12:00:00', "Um atendimento muito legal", 'S', "Obrigado", 1, 3, 1),
('2018-01-01 12:00:00', "Não gostei, achei meio ruim", 'S', "Ninguém liga pra sua opinião", 4, 1, 4),
('2018-01-01 12:00:00', "Acho que vcs podiam fazer outra coisa", 'S', "Quem sabe um dia", 6, 2, 7),
('2018-01-01 12:00:00', "Oq vcs fazem?", 'N', null, 3, 4, 10);

-- select * from tb_categoria_produto;
-- select * from tb_produto;
-- select * from tb_usuario;
-- select * from tb_cidade;
-- select * from tb_atendimento;

-- relatorio produtos mais reclamados
-- select p.nome_produto, count(p.id_produto) as qtd 
-- from tb_atendimento as a join tb_produto as p on p.id_produto = a.id_produto where a.id_tipo_atendimento = 1
-- group by p.id_produto order by count(p.id_produto) desc limit 3;

-- relatorio atendimentos em aberto
-- select a.dt_hr_atendimento, u.nome_usuario, u.cpf_usuario, u.email_usuario, a.desc_atendimento
-- from tb_atendimento as a inner join tb_usuario as u on a.id_cliente = u.id_usuario where res_atendimento = 'N';
-- and dt_hr_atendimento > xxxxxxx

-- relatorio reclamações
-- select dt_hr_atendimento, desc_atendimento, res_atendimento, solucao_atendimento from tb_atendimento where id_tipo_atendimento = 1 order by res_atendimento;