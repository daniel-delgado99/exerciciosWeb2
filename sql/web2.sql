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
cidade_cliente varchar(100),
uf_cliente char(2)
);

insert into tb_cliente(cpf_cliente, email_cliente, nome_cliente, data_cliente, rua_cliente, nr_cliente, cep_cliente, cidade_cliente, uf_cliente) values
("12345678910", "braian@braian", "Braian Viana", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "daniel@daniel", "Daniel Delgado", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "gustavo@gustavo", "Gustavo Lara", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "matheus@matheus", "Matheus Fernandes", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "rafael@rafael", "Rafael Damiani", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "joao@joao", "Joao Silva", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "maria@maria", "Maria Souza", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "jose@jose", "José Carlos", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "geraldo@geraldo", "Geraldo Martins", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR"),
("12345678910", "outro@outro", "Outro nome", "2018-01-01 00:00", "Rua 1", 1, "8000000", "Curitiba", "PR");

insert into tb_usuario(login_usuario, senha_usuario, nome_usuario) values
("braian", "braian123", "Braian Viana"),
("daniel", "daniel123", "Daniel Delgado"),
("gustavo", "gustavo123", "Gustavo Lara"),
("matheus", "matheus123", "Matheus Fernandes"),
("rafael", "rafael123", "Rafael Damiani");

select * from tb_usuario;
select * from tb_cliente;
