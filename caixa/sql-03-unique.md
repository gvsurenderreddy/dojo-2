---
layout: post-alpha
title: "Unique"
author: "Edenir Norberto Anschau"
published: true
date: "2013-10-08"
partof: sql
num: 3
outof: 4
---

## Introdução
É comum a necessidade de garantir a unicidade de um determinado registro na tabela que representa uma entidade no banco de dados.

Tomanos como exemplo a entidade `Logradouro` abaixo:

    public interface Logradouro {
    
      int getId();
    
      CEP getCEP();
    
      String getLogradouro();
    
    }

Nesse caso o CEP não pode se repetir, pois um CEP não pode pertencer a mais de um logradouro.  
_Obs: Um logradouro pode ter mais de um CEP mas com sufixos diferentes, em nosso exemplo estamos trantando o CEP como um código único._

## Erros comuns
Ao definirmos um campo como `UNIQUE` de uma tabela é comum cometermos alguns erros, especificamente ao definir duplas de colunas como `UNIQUE`, por exemplo:

    create table LOGRADOURO (
    
     ID int not null auto_increment,
     CEP char(8) not null,
     NOME varchar(100) not null,
    
     primary key (ID),
     unique(CEP, NOME)
    
    ) engine=InnoDB default charset=utf8

A idea foi querer definir a unicidade entre em um CEP e o nome do logradouro. Só que com essa condição será permitido inserirmos registros na tabela tais como:

	+----+----------+-------+
	| ID | CEP      | NOME  |
	+----+----------+-------+
	|  1 | 12345678 | Rua A |
	|  2 | 12345678 | Rua B |
	+----+----------+-------+

A única `constraint` aplicada nessa tabela é que não será permitido CEP _12345-678_ estar vinculado mais de uma vez para a _Rua A_, por exemplo. 

Além disso, nossa tabela não garante nenhum tipo de integridade, pois um mesmo CEP pertencendo a dois endereços é algo inexistente no mundo real.

## A utilização correta
Agora que sabemos o problema criado com a utilização incorreta da `constraint` `UNIQUE`, devemos entender primeiro o que deve ser único.
Em nosso exemplo, além de termos como `UNIQUE` a coluna `ID` por ser chave-primária, não queremos registros repetidos na coluna `CEP`, então a `constraint` `UNIQUE` aplica-se 
somente para a coluna `CEP`. 

	create table LOGRADOURO (

	 ID int not null auto_increment,
	 CEP char(8) not null,
	 NOME varchar(100) not null,

	 primary key (ID),
	 unique(CEP)

	) engine=InnoDB default charset=utf8

Com isso garantimos que não existirá na tabela `LOGRADOURO` dois CEPs para o mesmo logradouro.
