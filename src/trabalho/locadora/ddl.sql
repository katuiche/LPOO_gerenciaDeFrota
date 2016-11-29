/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Belniak
 * Created: 29/11/2016
 */

CREATE TABLE cliente(
    id serial primary key,
    nome varchar(100),
    sobrenome varchar(100),
    rg varchar(15),
    cpf varchar(15),
    endereco varchar(200)
);

CREATE table veiculo(
    id serial primary key,
    marca varchar(50),
    estado varchar(50),
    categoria varchar(50),
    placa varchar(50),
    locacao varchar(50)
);

CREATE TABLE automovel(
    id_veiculo integer primary key,
    modelo varchar(50)
);

CREATE TABLE van(
    id_veiculo integer primary key,
    modelo varchar(50)
);

CREATE TABLE moto(
    id_veiculo integer primary key,
    modelo varchar(50)
);

CREATE TABLE locacao(
    id serial primary key,
    dias integer,
    valor real,
    data date,
    cliente integer

);