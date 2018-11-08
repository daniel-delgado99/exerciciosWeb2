drop database web2;
create database web2;

use web2;

create table tb_usuario(
id_usuario int primary key auto_increment,
login_usuario varchar(50) unique,
senha_usuario varchar(50),
nome_usuario varchar(100)
);

create table tb_cliente (
id_cliente int primary key auto_increment,
cpf_cliente char(11) unique,
nome_cliente varchar(100),
email_cliente varchar(100) unique,
data_cliente datetime,
rua_cliente varchar(100),
nr_cliente integer,
cep_cliente char(8),
id_cidade int
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

create table tb_produto (
id_produto int primary key AUTO_INCREMENT,
nome_produto varchar(100)
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
id_produto int,
id_tipo_atendimento int,
id_usuario int,
id_cliente int
);

ALTER TABLE tb_cliente ADD FOREIGN KEY (id_cidade) REFERENCES tb_cidade(id_cidade);
ALTER TABLE tb_cidade ADD FOREIGN KEY (id_estado) REFERENCES tb_estado(id_estado);

ALTER TABLE tb_atendimento ADD FOREIGN KEY (id_produto) REFERENCES tb_produto(id_produto);
ALTER TABLE tb_atendimento ADD FOREIGN KEY (id_tipo_atendimento) REFERENCES tb_tipo_atendimento(id_tipo_atendimento);
ALTER TABLE tb_atendimento ADD FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario);
ALTER TABLE tb_atendimento ADD FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id_cliente);

insert into tb_tipo_atendimento (nome_tipo_atendimento) values
("Reclamação"), 
("Sugestão"),
("Elogio"),
("Dúvida");

insert into tb_produto (nome_produto) values
("Laranja"),
("Abridor de latas"),
("Fusível"),
("Crédito no LOL"),
("Cortina"),
("Toddy"),
("Caneta"),
("Penal"),
("Prato"),
("Copo"),
("Fruta do conde");

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

insert into tb_cliente(cpf_cliente, email_cliente, nome_cliente, data_cliente, rua_cliente, nr_cliente, cep_cliente, id_cidade) values
("12345678910", "braian@braian", "Braian Viana", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678911", "daniel@daniel", "Daniel Delgado", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678912", "gustavo@gustavo", "Gustavo Lara", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678913", "matheus@matheus", "Matheus Fernandes", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678914", "rafael@rafael", "Rafael Damiani", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678915", "joao@joao", "Joao Silva", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678916", "maria@maria", "Maria Souza", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678917", "jose@jose", "JosÃ© Carlos", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678918", "geraldo@geraldo", "Geraldo Martins", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678919", "outro@outro", "Outro nome", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1);

insert into tb_usuario(login_usuario, senha_usuario, nome_usuario) values
("braian", "braian123", "Braian Viana"),
("daniel", "daniel123", "Daniel Delgado"),
("gustavo", "gustavo123", "Gustavo Lara"),
("matheus", "matheus123", "Matheus Fernandes"),
("rafael", "rafael123", "Rafael Damiani"),
("a", "a", "a");

insert into tb_atendimento(dt_hr_atendimento, desc_atendimento, res_atendimento, id_produto, id_tipo_atendimento, id_usuario, id_cliente) values
('2018-01-01 12:00:00', "Um atendimento muito legal", 'S', 1, 3, 1, 3),
('2018-01-01 12:00:00', "Não gostei, achei meio bosta", 'S', 4, 1, 1, 5),
('2018-01-01 12:00:00', "Acho que vcs podiam fazer outra coisa", 'S', 6, 2, 2, 6),
('2018-01-01 12:00:00', "Oq vcs fazem?", 'N', 3, 4, 2, 7);

select * from tb_usuario;
select * from tb_cliente;
select * from tb_cidade;
select * from tb_atendimento;