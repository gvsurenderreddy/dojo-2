---
layout: post
title: "Implementando Forms Update: Testes"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-05"
published: true
partof: procedimento-crud-forms
num: 2
---

## <a id="TOPO"> </a> Introdução
Geralmente quando temos algum cadastro em um site, temos a necessidade de realizar uma atualização
em nossos dados como por exemplo, atualização de endereço, telefone, e-mail e etc.<br> 
Em alguns casos, esta atualização é feita no intuito de corrigir um erro no momento da inserção 
daquele dado.
  
Esta atualização é feita através de um _form_ semelhante ao de criação. Porém, um pré-requisito para
que este _form_ funcione é ter os dados armazenados em um banco de dados.

## Antes de iniciar 
Este item exige conhecimentos sobre:

- [URL](http://pt.wikipedia.org/wiki/URL)
- [SiteBricks](http://sitebricks.org)

## Especificação

Siga o checklist abaixo:
<table class="table table-bordered">
 <tr>
   <td class="tac col2em">
    <a id="topo_0_0"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_0">Quais dados devem ser atualizados?</a>  
   </td>
 </tr>
 <tr>
   <td class="tac col2em">
    <a id="topo_0_1"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_1">Como seria a URL?</a>  
   </td>
 </tr>
</table>

### <a id="0_0"> </a>Quais dados devem ser atualizados?
Utilizando o mesmo exemplo do artigo [Implementando Forms Create: Testes]({{base.url}}/procedimento/crud-forms/00-implementando-forms-testes.html)
onde tinhamos um aluno a ser cadastrado em um determinado curso, atualizaremos os seus dados já 
cadastrados.

Para esta aplicação, atualizaremos apenas o nome do aluno pensando no erro de digitação que possa
ocorrer no momento da criação/adição do mesmo no sistema (isto é pode ocorrer com nomes semelhantes
como "Souza" e "Sousa", "Luiz" e "Luis" e etc.

A atualização também deve estar bem clara na especificação, ou seja, os dados que devem ser alterados
devem, de fato, resolver o problema. Por exemplo, imagine que alguns alunos possuam
uma bolsa de estudos. E todo ano a faculdade precisa atualizar a _renda do grupo familiar_ a pedido
do _MEC_. É fato que este tipo de dado DEVE estar neste _form_, já uma data de nascimento não, 
porque uma data de nascimento nunca será mudada (lembrando que isto não é uma regra, é um caso de uso
específico da faculdade).

### <a id="0_1"> </a>Como seria a URL?
Geralmente quando fazemos uma atualização de uma entidade, é comum redirecionar a URL a mesma página,
assim podemos visualizar o resultado, ou seja, como ficou a entidade após a atualização feita no 
_form_.

## Implementação

Siga o checklist abaixo:
<table class="table table-bordered">
 <tr>
   <td class="tac col2em">
    <a id="topo_0_3"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_3">Preparar o teste e definir a URL</a>  
   </td>
 </tr>    
 <tr>
   <td class="tac col2em">
    <a id="topo_0_6"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_6">Preparar o método de teste: put</a>
   </td>
 </tr>
  <tr>
   <td class="tac col2em">
    <a id="topo_0_7"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_7">Atenção!!!!</a>
   </td>
 </tr>
</table>

### <a id="0_3"> </a>Preparar o teste e definir a URL
Da mesma forma do _Form Create_, criaremos a classe `TesteDeFormDeAlunoUpdate` no pacote 
`ui.api.crud` do diretório de teste do projeto `/src/test/java` e adicione o `@Test`

    @Test
	public class TesteDeFormDeAlunoUpdate {
	}
  
Torne-a uma subclasse de `TesteDeIntegracaoWeb`

	@Test
	public class TesteDeFormDeAlunoUpdate extends TesteDeIntegracaoWeb {
	}
	
Utilize a mesma URL definica no `TesteDeFormDeAlunoCreate`:

    private static final String URL = "api/crud/faculdade/curso/direito/aluno";
	

### <a id="0_6"> </a> Defina o buscador
Defina um _buscador de aluno_ após declarar a `URL`.

	@Test
	public class TesteDeFormDeAlunoUpdate extends TesteDeIntegracaoWeb {

	 private static final String URL = "api/faculdade/crud/curso/direito/aluno";

	 @Inject
	 private BuscarAluno buscarAluno;

	}
	
Será necessário carregar os falsos como no `TesteDeFormDeAlunoCreate`, para isso pode-se copiar o bloco do teste.<br> 
__Obs.:__ Em um teste de form de update <big>JAMAIS</big> se deve truncar a tabela, pois para realizar a atualização de algum registro o mesmo deve existir no banco de dados.

	@Override
	protected void prepararSqlUnit(SqlUnit sqlUnit) {
	 sqlUnit.loadEntitySet(UsuariosFalso.class);

	 sqlUnit.loadEntitySet(AlunosFalso.class);
	}

Crie o método `put()` e defina um aluno falso no qual deseja atualizar o registro. Extraia o `id` deste aluno, e atribua novos valores para ser atualizado.  


	public void put() {
	 Usuario usuario = UsuariosFalso.USUARIO_A;

	 Aluno aluno = AlunosFalso.ALUNO_1;
	 int id = aluno.getId();
	 String nome = "Luiz de Souza";
	}

O próximo passo é certificar que o novo valor atribuido é diferente do atual, para isso faça uma assertiva para o nome aluno como mostrado baixo:

	public void put() {
	 Usuario usuario = UsuariosFalso.USUARIO_A;

	 Aluno aluno = AlunosFalso.ALUNO_1;
	 int id = aluno.getId();
	 String nome = "Luiz de Souza";

	 Aluno antes = buscarAluno.porId(id);
	 assertThat(aluno.getNome(), not(equalTo(nome)));
	}


No `TesteDeFormDeAlunoUpdate` são passados apenas as informações que se deseja alterar mais o `id`, que serve para garantir que a informação a ser alterada pertence a um registro específico.  
Um dos erros mais comuns é esquecer de passar o `id` no _form_, então certifique-se que o `id` esta sendo inserido no _form_.


	public void put() {
	 Usuario usuario = UsuariosFalso.USUARIO_A;

	 Aluno aluno = AlunosFalso.ALUNO_1;
	 int id = aluno.getId();
	 String nome = "Luiz de Souza";

	 Aluno antes = buscarAluno.porId(id);
	 assertThat(aluno.getNome(), not(equalTo(nome)));

	 Map<String, Object> form = newHashMap();
	 form.put("id", id);
	 form.put("nome", nome);
	}

Na sequência  é definido o bloco de código que simula o usuário inserindo dados no formulário, o mesmo código utilizado no `TesteDeFormDeAlunoCreate`, porém onde é declarado
um `WebResponse`, devemos chamar o método `put` ao invés de `post`, esta mudança faz toda a diferença, pois isso vai garantir que informação seja alterada ao invés de inserida. 
Certique-se também que realmente foi alterado de `post` para `put`, já que é muito comum deixar como `post`.

Veja abaixo a diferença.

No _TesteDeFormDeAlunoUpdate_

	public void put() {
	 Usuario usuario = UsuariosFalso.USUARIO_A;

	 Aluno aluno = AlunosFalso.ALUNO_1;
	 int id = aluno.getId();
	 String nome = "Luiz de Souza";

	 Aluno antes = buscarAluno.porId(id);
	 assertThat(aluno.getNome(), not(equalTo(nome)));

	 Map<String, Object> form = newHashMap();
	 form.put("id", id);
	 form.put("nome", nome);

	 Map<String, String> cookies = login(usuario);
	 WebResponse response = jsonClientOf(URL, cookies).put(form);
 	 FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
	 assertThat(response.toString(), json.isValid(), is(true));
	}

No _TesteDeFormDeAlunoCreate_

	Map<String, String> cookies = login(usuario);
	WebResponse response = jsonClientOf(URL, cookies).post(form);
	FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
	assertThat(json.toString(), json.isValid(), is(true));

Conforme comentamos, atualizaremos apenas o campo _nome_, mas você poderá atualizar outros valores
para testá-los se necessário.  

### Definirar no módulo
Defina a URL no método `bindApiCrud()` da classe `ModuloFaculdadeUI`.  

	@Override
	protected void bindApiCrud() {
		at("api/crud/faculdade/curso/aluno").serve(FormDeAlunoCreate.class);
		at("api/crud/faculdade/curso/aluno").serve(FormDeAlunoUpdate.class);
	}
	
Note que haverá erros de compilação pois a classe `FormDeAlunoUpdate` ainda não existe. Utilize o
atalho `Ctrl + 1` e crie esta classe no pacote `ui.api.crud` do diretório principal do projeto 
`/src/main/java`.

###<a id="0_7"> </a> Atenção !!!!

Haverá situações em que na mesma classe de teste será testado o `FormCreate` e `FormUpadate`. Uma dessas, senão a principal é quando se tem poucos dados no formulário a serem passados.<br>
Quando se há muitas informações para serem passadas no form, se for incluir o bloco de teste do update, será mais complicado passar montar o _mustache_ posteriormente, uma vez que será necessário passar muitos parâmetros, a separação facilita esta parte da implementação. Esta separação ocorre tanto no teste quanto na implementação, note que alguns análogos terá uma implementação com o sufixo _update_ e outras somente com o nome do form sem sufixo _update_. A implementação sem sufixo contém o _Create_ + _Update_.

Implementaremos o _Form_ a seguir, com as duas formas.	

Para mais informações acesse os códigos nos links abaixo:

[ModuloFaculdadeUI.java](https://github.com/objectos/objectos-dojo/blob/995199ab73f60d47602e99bf717a80ca321b6b17/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/ModuloFaculdadeUI.java)  
[TesteDeFormDeAlunoUpdate.java](https://github.com/objectos/objectos-dojo/blob/403aac69b1d4c79dd10577ebef58bef8d3aad193/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/TesteDeFormDeAlunoUpdate.java)  
[TesteDeFormDeAluno.java](https://github.com/objectos/objectos-dojo/blob/403aac69b1d4c79dd10577ebef58bef8d3aad193/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/TesteDeFormDeAluno.java)  
Siga para o próximo passo. O Form! <a href="{{ site.url }}/procedimento/crud-forms/" class="btn btn-success">Continuar!</a><br>
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>
