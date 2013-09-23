use DOJO;

drop table if exists ALUNO;

create table ALUNO (
 ID int not null auto_increment,
 NOME varchar(60) not null,
 MATRICULA varchar(140) not null,
 
 primary key(ID)
) engine=InnoDB default charset=utf8;