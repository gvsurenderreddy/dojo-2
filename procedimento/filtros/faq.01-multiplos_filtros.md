---
layout: post-alpha
title: "Estado/comportamento de objetos nos filtros"
author: "Edenir Norberto Anschau"
date: "2012-09-30"
published: true 
partof: faq-filtro
num: 0
---

## Introdução
Para facilitar a usabilidade de uma aplicação, é comum disponibilizarmos um [`dropdown-list`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select) para o usuário filtrar o resultado de uma consulta. 
Por exemplo, o usuário da aplicação quer filtrar as disciplinas de um determinado aluno pelos seguintes status: todas as disciplinas,
disciplinas que o aluno esteja matriculado ou disciplinas que o aluno tenha dependência. 

Exemplo de uma `dropdown-list` utilizado para filtros:

<form action="">
    <select name="status">
		<option value="TODAS">Todas</option>
		<option value="MATRICULADO">Matriculado/Cursando</option>
		<option value="DEPENDENCIA">Com dependência	</option>
	</select>	
</form>

## Primeira opção
Antes tudo, é necessário implementar no teste referente a esse buscador, os testes para todos os filtros desejados, para ler mais sobre filtros 
[clique aqui](http://dojo.objectos.com.br/procedimento/filtros/00.-filtro-teste.html).

Na implementação do filtro dentro do método `newPager()`, teríamos a seguinte implementação:

	private NativeSql newPager(String what, AlunoKey key, RequestWrapper wrapper) {
	  String nome = wrapper.param("nome");
	                                                                                 
	  DisciplinaStatus status = wrapper.enumParam(DisciplinaStatus.class, "status");

	  DisciplinaStatus statusMatriculado = status != null ? DisciplinaStatus.MATRICULADO : null;

	  DisciplinaStatus statusDependencia = status != null ? DisciplinaStatus.DEPENDENCIA : null;

	  return newSelect(what)

	      .add("where DISCIPLINA.ALUNO_ID = ?").param(key.getId())

	      .addIf("and DISCIPLINA.MATRICULADO = ?").paramNotNull(statusMatriculado.booleanValue())
	      .addIf("and DISCIPLINA.DEPENDENCIA = ?").paramNotNull(statusDependencia.booleanValue())

	      .addIf("and ALUNO.NOME like concat('%', ?, '%')").paramNotNull(nome)

	      .add("order by")
	      .add("ALUNO.NOME");
	}

Para entender como será construída a consulta desse filtro, em tempo de execução será considerado: <br />
Todos os registros: `statusMatriculado` e `statusDependencia` serão iguais a `null` <br />
Alunos matriculados: `statusMatriculado = true`  e `statusDependencia = false` <br />
Alunos com dependência: `statusMatriculado = false` e `statusDependencia = true`

O enum `DisciplinaStatus` tem os tipos de status disponíveis para o filtro: TODAS, MATRICULADO e DEPENDENCIA.
Nossa primeira solução é utilizar um operador ternário que seria o mesmo que utizar um `if` para obter o filtro invocado, e caso 
filtro não tenha sido incodado é atribuido `null`para o status, assim ao construir a consulta `SQL` será decidido qual status será incluso.

O código		`DisciplinaStatus statusMatriculado = status != null ? DisciplinaStatus.MATRICULADO : null;` seria o mesmo que:

	if(status != null) {
	  status = DisciplinaStatus.MATRICULADO;
	}else{
	  status = null;
	}

Note então que o primeiro meio para resolver qual filtro será selecionado é utilizando o operador ternário para todas as combinações de filtros possíveis para esse filto.
Essa solução funciona, mas não estamos tratando um tipo de `DisciplinaStatus` como objeto, que tem estado e comportamento. Abaixo veremos a mesma implementação sem utilizar operador ternário.

## Segunda opção
Agora temos o enum `DisciplinaStatus` com a seguinte estrutura:

	public enum DisciplinaStatus {

	 TODAS("Todas"),
	 MATRICULADO("Matriculado") {
	   @Override
	   public Boolean matriculadoValue() {
	     return Boolean.TRUE;
	   }

	   @Override
	   public Boolean depedenciaValue() {
	     return Boolean.FALSE;
	   }
	 },

	 DEPENDENCIA("Com dependência") {

	   @Override
	   public Boolean matriculadoValue() {
	     return Boolean.FALSE;
	   }

	   @Override
	   public Boolean depedenciaValue() {
	     return Boolean.TRUE;
	   }

	 };

	 private final String descricao;

	 private DisciplinaStatus(String descricao) {
	   this.descricao = descricao;
	 }

	 public String getDescricao() {
	   return descricao;
	 }

	 public Boolean matriculadoValue() {
	   return null;
	 }

	 public Boolean depedenciaValue() {
	   return null;
	 }

	}
 
Conseguimos definir um estado distindo para cada tipo do enum `DisciplinaStatus`, então para cada instância teremos o seguinte comportamento:<br /> 
`TODOS`: `matriculadoValue()` e `depedenciaValue()` retornarão `null`, esse é estado padrão.<br />
`MATRICULADO`: `matriculadoValue()` retornará `true`  e `depedenciaValue()` retornará `false`.<br />
`DEPENDENCIA`: `matriculadoValue()` retornará `false`  e `depedenciaValue()` retornará `true`.<br />

Nesse momento sabemos quais estados de `DisciplinaStatus` terá o comportamento que esperamos em nosso filtro, para isso o método `newPager()` terá a estrutura abaixo:

	private NativeSql newPager(String what, AlunoKey alunoKey, RequestWrapper wrapper) {
	  String nome = wrapper.param("nome");

	  DisciplinaStatus status = wrapper.enumParam(DisciplinaStatus.class, "status");
	  status = status != null ? status : DisciplinaStatus.TODAS;

	  return newSelect(what)

	      .add("where DISCIPLINA.ALUNO_ID = ?").param(alunoKey.getId())

	      .addIf("and DISCIPLINA.MATRICULADO = ?").paramNotNull(status.matriculadoValue())
	      .addIf("and DISCIPLINA.DEPENDENCIA = ?").paramNotNull(status.depedenciaValue())

	      .addIf("and ALUNO.NOME like concat('%', ?, '%')").paramNotNull(nome)

	      .add("order by")
	      .add("ALUNO.NOME");
	}

Feito isso, temos apenas um operador ternário chegando se a variável `status` não é `null`, caso seja, o valor padrão é `DisciplinaStatus.TODAS`, caso contrário
é a instância que foi construída a partir do filtro selecionado pelo usuário.
Então para cada instância, teremos um comportamento distindo quando a variável de referência `status` chamar os métodos `matriculadoValue()` e `depedenciaValue()`.


##Códigos-fonte
[BuscarDisciplinaGuice.java](https://github.com/objectos/objectos-dojo/blob/88baa0501aa9428ddfe9ff6c522f0831c0f07322/objectos-dojo-team/src/test/java/br/com/objectos/dojo/enanschau/gen/TesteDeBuscarDisciplina.java)  
[DisciplinaStatus.java](https://github.com/objectos/objectos-dojo/blob/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/enanschau/gen/DisciplinaStatus.java)

