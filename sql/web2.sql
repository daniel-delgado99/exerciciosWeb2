drop database web2;
create database web2;

use web2;

create table tb_usuario(
id_usuario int primary key auto_increment,
login_usuario varchar(50),
senha_usuario varchar(50),
nome_usuario varchar(100)
);

create table tb_cliente (
id_cliente int primary key auto_increment,
cpf_cliente char(11),
nome_cliente varchar(100),
email_cliente varchar(100),
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

ALTER TABLE tb_cliente ADD FOREIGN KEY (id_cidade) REFERENCES tb_cidade(id_cidade);
ALTER TABLE tb_cidade ADD FOREIGN KEY (id_estado) REFERENCES tb_estado(id_estado);

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
("12345678910", "daniel@daniel", "Daniel Delgado", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "gustavo@gustavo", "Gustavo Lara", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "matheus@matheus", "Matheus Fernandes", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "rafael@rafael", "Rafael Damiani", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "joao@joao", "Joao Silva", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "maria@maria", "Maria Souza", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "jose@jose", "JosÃ© Carlos", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "geraldo@geraldo", "Geraldo Martins", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1),
("12345678910", "outro@outro", "Outro nome", "2018-01-01 00:00", "Rua 1", 1, "8000000", 1);

insert into tb_usuario(login_usuario, senha_usuario, nome_usuario) values
("braian", "braian123", "Braian Viana"),
("daniel", "daniel123", "Daniel Delgado"),
("gustavo", "gustavo123", "Gustavo Lara"),
("matheus", "matheus123", "Matheus Fernandes"),
("rafael", "rafael123", "Rafael Damiani"),
("a", "a", "a");

select * from tb_usuario;
select * from tb_cliente;
select * from tb_cidade;