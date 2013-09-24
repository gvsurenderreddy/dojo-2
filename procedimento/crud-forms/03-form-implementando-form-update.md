---
layout: post-alpha
title: "Implementado Form Update: Implementação"
author: "Anderson Amorim Silva"
user: "asilva26"
date: "2013-09-23"
published: true
partof: procedimento-crud-forms
num: 3
---

##Introdução 
 
Após realizar o teste de seu form update, podemos partir para sua implementação e verificar se o form
de fato realiza com sucesso a atualização de uma entidade no banco de dados. Este artigo 
será muito parecido com a artigo de implementação de forms 
[create](http://dojo.objectos.com.br/procedimento/crud-forms/01-form-implementando-form.html).

Novamente lembrando que, antes de iniciar a leitura deste artigo é de suma importância que o teste 
esteja implementado de form correta.

## Acesso rápido

Para acessar os tópicos do artigo siga o checklist abaixo:

<table class="table table-bordered">
 
  
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      Implementando o form
    </td>
    <td>
      <a href="#0_0">help!</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
        Implementação do post e put no mesmo form    
    </td>
    <td>
      <a href="#0_1">help!</a>
    </td>
  </tr>
   <tr>
    <td class="tac col2em">
      <a id="topo_0_2"><input type="checkbox" /></a>
    </td>
    <td>
       Link para códigos
    </td>
    <td>
      <a href="#0_2">help!</a>
    </td>
  </tr>
</table>


##<a id="0_0"> </a> Implementando o form

Ao realizar a implementação deve-se começar pela declaração da instância _Forms_, bem parecido com a implementação do `FormDeAlunoCreate`.

    public class FormDeAlunoUpdate {

	 private final Forms forms;
 
	}

Um erro irá ocorrer pela ausência do contrutor, para inserí-lo basta apertar `Alt+S+A`, depois de criar o construtor deve-se colocar a anotação `@Inject`.

	public class FormDeAlunoUpdate {

	 private final Forms forms;

	 @Inject
	 public FormDeAlunoUpdate(Forms forms) {
		this.forms = forms;
	 }

	}

Após será criado o método `put`, a primeira coisa a se fazer é colocar a anotação `@Put`, a implementação dele será muito parecida com a implementação do método `post`.

	public class FormDeAlunoUpdate {

	 private final Forms forms;

	 @Inject
	 public FormDeAlunoUpdate(Forms forms) {
	  this.forms = forms;
	 }

	 @Put
	 public Reply<?> put() {
	  return reply();
	 }

	 private Reply<?> reply() {
	  return forms.of(PojoJson.class)

	  .reply();
	 }

	}

Um erro irá acontecer pois a _inner class_ `PojoJson` ainda não foi criada, ela será muito parecida com a criada anteriormente para implementação do _FormCreate_.

	private static class PojoJson implements EntityJson {

	 int id;
	 String nome;

	  @Override
	  public EntityMapping toMapping() {
	   return Relational.table("BANCO.ALUNO")

	    .id("ID", id, new GeneratedKeyListener() {
		  @Override
		  public void set(GeneratedKey key) {
		   id = key.getInt();
		  }
	  })

	  .col("NOME", nome);
	 }

	}

Note que esta implementação é praticamente a mesma realizada no _FormCreate_, o que as difere é utilização de apenas dois parâmetros, `id` que irá identificar um registro específico, e `nome`, que será o atributo a ser atualizado.


###<a id="0_1"> </a> Implementação do post e put no mesmo form      

É comum utilizar a mesma implementação do _form_ para receber requisões vindas com os métodos HTTP `post`, `put`, ou seja, para criar um novo registro ou atualizá-lo.
Mas como seria esta implementação? Esta parte do artigo tem como objetivo mostrar a implementação mais básica para este tipo de situação.

A criação da classe e declaração da instância forms é idêntica a realizada nos outros forms, o que começa a ser diferente é o que vem a seguir:

    public class FormDeAluno {

	 private final Forms forms;
 
    
     @Inject
	 public FormDeAlunoUpdate(Forms forms) {
	   this.forms = forms;
	 }

	 @Post
	 public Reply<?> post() {
		return reply();
	 }

	 @Put
	 public Reply<?> put() {
		return reply();
	 }

	 private Reply<?> reply() {
		return forms.of(PojoJson.class)

		  .reply();
	 }

	}

Tanto o `post` quanto o `put` são declarados um em seguida do outro, o método `reply()` é o mesmo para os dois, o que vai determinar qual será utilizado, vai ser o tipo de requisição feita.
Ocorrerá um erro pois o método `reply()` ainda não existe, o bloco a seguir irá implementá-lo:

    private Reply<?> reply() {
	  return forms.of(PojoJson.class)

	   .reply();
	}

A implementação da `inner class` `PojoJson` será idêntica a realizada no `FormDeAlunoCreate`. Note que na implementação do método `toMapping` definimos todas as colunas da tabela referente a entidade
`Aluno`.

    private static class PojoJson implements EntityJson {

	  int id;
	  String nome;
	  String matricula;
	  int curso;

	  @Override
	  public EntityMapping toMapping() {
	    return Relational.table("BANCO.ALUNO")

		 .id("ID", id, new GeneratedKeyListener() {
		   @Override
		   public void set(GeneratedKey key) {
		    id = key.getInt();
		   }
		})

		 .col("NOME", nome)
		 .col("MATRICULA", matricula)
		 .col("CURSO_ID", curso);
	  }

	}

###<a id="0_2"> </a> Link para códigos:
[FormDeAlunoUpade.java](https://github.com/objectos/objectos-dojo/blob/104487c2da6df8e39291a94422bc2326beed69fb/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/FormDeAlunoUpdate.java)  
[FormDeAluno.java](https://github.com/objectos/objectos-dojo/blob/104487c2da6df8e39291a94422bc2326beed69fb/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/FormDeAluno.java)
