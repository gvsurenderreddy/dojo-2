---
layout: post-alpha
title: "Cláusula Group By"
author: "Edenir Norberto Anschau"
published: true
partof: sql
num: 2
---

## Introdução
Para agrupar resultados de um consulta SQL usamos funções de grupo juntatamente com cláusula `GROUP BY` no comando
`SELECT`.  Com isso podemos agrupar as linhas do resultado obtido com base nos valores de determinadas colunas, dessa
forma nossa não vamos trabalhar com pesquisas em todas as linhas de uma tabela, mas em grupos inferiores.

A sintaxe SQL apresentada abaixo foi utilizada para bancos de dados MySQL, mas com poucas alterações podem ser usadas em outros bancos
de dados SQL.

## Sintaxe:


		select COLUNAS, FUNÇÃO_DE_GUPO
		from NOME_TABELA
		group by NOME_COLUNA;

Onde: 
Colunas: é lista das colunas que vai ser agrupadas. 
Funções de grupo:  `COUNT`, `SUM`, `AVG`, `MIN`, `MAX` e demais funções de grupo: http://dev.mysql.com/doc/refman/5.0/en/group-by-functions.html


Obs.: `GROUP BY` deve vir sempre depois do `WHERE`  e antes de `ORDER BY`, casa haja necessidade de usá-los.


###Passo-a-passo
Para o nosso exemplo, usaremos as tabelas ALUNO e FATURA:

ALUNO

		+--------+-------------+
		| Coluna | Tipo        |
		+--------+-------------+
		| ID     | int         |
		| nome   | varchar     |
		+--------+-------------+


FATURA:

		+------------+---------+
		| Coluna     | Tipo    |
		+------------+---------+
		| ID         | int     |
		| ALUNO_ID   | int     |
		| VALOR      | double  |
		| VENCIMENTO | date    |
		+------------+---------+


Temos os seguintes nomes cadastrados na tabela `ALUNO`: AlunoA, AlunoB e AlunoC.
Nesse momento queremos saber quantas faturas(vencidas ou não) esses alunos tem cadastradas. Para isso usaremos a consulta abaixo:


		select ALUNO.NOME , COUNT(*) as FATURAS
		from  FACULDADE.FATURA

		join ALUNO
		on FATURA.ALUNO_ID  = ALUNO.ID

		group by 
		ALUNO.ID

__Nota__: quando for manipular consultas SQL, use sempre o caminho absoluto para se referir a uma tabela ou coluna. No comando `select`
quando for especificar a tabela em `from` insira também o nome do banco de dados antes do nome da tabela, por exemplo:
`select * from MEU_BANCO.MINHA_TABELA` no lugar de `select * from MINHA_TABELA`. O mesmo vale para colunas, use o nome da tabela o nome
da coluna para qual vai ser usada na consulta: `where ALUNO.ID = 1` em vez de `where ID = 1`. Isso evita muitos problemas, já que é comum 
tabelas distindas usadas na mesma consulta terem nome de colunas iguais. 


Nosso resultado:

		+---------+---------+
		| NOME    | FATURAS |
		+---------+---------+
		| AlunoA  |       4 |
		| AlunoB  |       3 |
		| AlunoC  |       3 |
		+---------+---------+


Agora precisamos saber do número de faturas pertencentes a cada aluno e a soma total das faturas de cada aluno:

		select ALUNO.NOME ,  COUNT(*) as FATURAS,  SUM(FATURA.VALOR) as VALOR_TOTAL
		from  FACULDADE.

		join ALUNO
		on FATURA.ALUNO_ID  = ALUNO.ID

		group by 
		ALUNO.ID


Resultado: 

		+---------+---------+-------------+
		| NOME    | FATURAS | VALOR_TOTAL |
		+---------+---------+-------------+
		| AlunoA  |       4 |      2082.1 |
		| AlunoB  |       3 |      1681.6 |
		| AlunoC  |       3 |      1581.6 |
		+---------+---------+-------------+


## GROUP BY com a cláusula WHERE
Caso quisermos apenas o resultado de um determinado aluno, utilizamos a cláusula `where` antes
do `group by`:

		select ALUNO.* ,  COUNT(*) as FATURAS,  SUM(FATURA.VALOR) as VALOR_TOTAL
		from  FACULDADE.FATURA

		join ALUNO
		on FATURA.ALUNO_ID  = ALUNO.ID

		where ALUNO.ID = 1
		and  FATURA.VENCIMENTO < '2013-09-13'

		group by 
		ALUNO.ID


Resultado:

		+----+---------+---------+-------------+
		| ID | NOME    | FATURAS | VALOR_TOTAL |
		+----+---------+---------+-------------+
		|  1 | AlunoA  |       2 |      1081.1 |
		+----+---------+---------+-------------+ 


##GROUP BY com a cláusula ORDER BY
Podemos também ordernar o resultado agrupado de uma consulta, para isso usamos a cláusula `order by` depois de `group by`.

		select ALUNO.NOME ,  COUNT(*) as FATURAS,  SUM(VALOR) as VALOR_TOTAL
		from  FACULDADE.FATURA

		join ALUNO
		on FATURA.ALUNO_ID  = ALUNO.ID

		group by 
		ALUNO.ID

		order by
		FATURA.VENCIMENTO

Resultado:

		+---------+---------+-------------+
		| NOME    | FATURAS | VALOR_TOTAL |
		+---------+---------+-------------+
		| AlunoB  |       3 |      1681.6 |
		| AlunoC  |       3 |      1581.6 |
		| AlunoA  |       4 |      2082.1 |
		+---------+---------+-------------+
