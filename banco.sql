/**
 Treino Database
 Autor: Vitor de Jesus ❣🚀
 Data: 19/10/2023
 */

 CREATE DATABASE db_contatos;

 USE db_contatos;


 CREATE TABLE contatos (
 id int NOT NULL auto_increment,
 nome varchar (45) NOT NULL,
 telefone long,
 email varchar (30),
 favorito boolean,
 foto varchar (200) NOT NULL,
 PRIMARY KEY (id)
 );


 INSERT INTO contatos(id, nome, telefone, email, favorito, foto)values
 (0,"Maria da Silva",11970707070, "maria@teste.com", false,"https://images.pexels.com/photos/4006576/pexels-photo-4006576.jpeg?auto=compress&cs=tinysrgb&w=1600"),
 (0, "Jose Maria", 11950505050,  "jose@teste.com", false,  "https://images.pexels.com/photos/7533347/pexels-photo-7533347.jpeg?auto=compress&cs=tinysrgb&w=1600"),
 (0, "Maria Jose", 11920202020, "maria@teste.com", false, "https://images.pexels.com/photos/1848565/pexels-photo-1848565.jpeg?auto=compress&cs=tinysrgb&w=1600");


 SELECT * from contatos;


 #SELECT * FROM funcionario;

 # DELETE FROM funcionario WHERE nome = "Maria";
