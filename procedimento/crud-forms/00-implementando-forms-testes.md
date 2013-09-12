---
layout: post
title: "Implementando Forms Create: Testes"
author: "Tiago Aguiar"
user: "taguiar"
date: "2012-04-05"
published: true
partof: procedimento-crud-forms
num: 0
---

## <a id="TOPO"> </a> Introdução
Quase todos os sites possuem páginas que podem ser exibidas somente a usuários cadastrados. Estes cadastros são realizados através de formulários preenchidos pelo usuário no próprio site que lhe concederá algumas permissões não disponíveis antes do cadastro como por exemplo, consultar a _Nota Fiscal Paulista_ no site da _Secretaria da Fazenda_, realizar compras em um site de vendas e até mesmo um _upload_ de seus videos à um site como o _YouTube_.
Os responsáveis por realizar os cadastros no "site", isto é, no banco de dados, são conhecidos aqui como __Forms__. São os _forms_ que gerenciam estes cadastros no banco de dados do sistema.

É importante entender que os _forms_ não se limitam a cadastros de usuários, eles podem ser utilizados para gravar dados de um livro, de um aluno, de uma organização e etc.

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
    <a href="#0_0">Quais dados devem ser cadastrados?</a>  
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

### <a id="0_0"> </a>Quais dados devem ser cadastrados?
Semelhante ao artigo [Implementando Consultas: Testes]({{ site.url }}/procedimento/crud-entidade/02.0-implementando-consultas-testes.html) , isto dependerá de cada situação. Podemos ter diversas situações como cadastrar um novo aluno em uma faculdade, cadastrar um usuário em um _e-commerce_, cadastrar um número de celular para 
concorrer aos prêmios daquele site, entre outras mais. Lembrando que um cadastro talvez exiga campos obrigatórios ou não como um nome e CEP respectivamente.

Precisamos do endereço no _form_ se quisermos comprar um eletrodoméstico pela internet?<br>
R: Sim! Precisamos para realizar a entrega do produto.

Precisamos do endereço no _form_ se quisermos comprar um _E-Book_?<br>
R: Não! Neste caso é necessário/obrigatório o e-mail do cliente para enviar o _link_ do _E-Book_.

__Por isso, é muito importante ter a especificação bem definida para evitar manutenção em códigos já
escritos e o aumento no tempo de finalização do projeto.__

Citaremos o exemplo de um _form_ para alunos que ingressaram em uma faculdade.<br>
Para tal exemplo, cadastraremos os seguintes dados:

- Nome
- Matricula
- Código do Curso
- Data de criação

Selecionamos estes dados (supondo que já exista a tabela e o banco de dados com estes 
atributos mais o ID), pois através deles os funcionários da faculdade poderão realizar futuras 
consultas como: 

- Todos os alunos daquele curso em ordem alfabética;
- Alunos com débitos em mensalidades (através da matrícula);
- Alunos que ingressaram na faculdade no ano de 2011.

Nota 1: Não entraremos em detalhes quanto ao endereço, data de nascimento, grau de escolaridade, etc.<br>
Nota 2: Os cadastrados deste tipo (aluno, funcionário, livro, etc) são geralmente efetuados por um
administrador do sistema que deve ter permissão/autenticação para estas funções.<br>  

### <a id="0_1"> </a>Como seria a URL?
Ao realizar um cadastrado (preencher o formulário e clicar em _ok_ por exemplo), podemos ter duas 
situações:

1. Redirecionar o usuário para a mesma URL, `faculdade/curso/direito/aluno`;
2. Redirecionar o usuário para uma nova URL, `faculdade/curso/direito/aluno/20`;

A primeira situação seria interessante para realizar um novo cadastro imediatamente.<br>
Já a segunda situação, seria interessante caso já quisessemos visualizar os dados daquele aluno cadastrado
recentemente.
  
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
    <a id="topo_0_4"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_4">Preparar o método de teste: acesso a um usuário não autenticado</a>
   </td>
 </tr>
   <tr>
   <td class="tac col2em">
    <a id="topo_0_5"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_5">Preparar o método de teste: acesso a um usuário não autorizado</a>
   </td>
 </tr>
    <tr>
   <td class="tac col2em">
    <a id="topo_0_6"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_6">Preparar o método de teste: gravar dados no banco de dados</a>
   </td>
 </tr>  
 <tr>
   <td class="tac col2em">
    <a id="topo_0_7"><input type="checkbox" /></a>
   </td>
   <td>
    <a href="#0_7">Form com Parâmetro</a>
   </td>
 </tr>
</table>

### <a id="0_3"> </a>Preparar o teste e definir a URL
O teste poderá ter diversos métodos para cada situação. Podemos ter métodos de testes para
usuários não autenticado/autorizado (veja logo mais), testes de NÃO gravar dados com login 
existente (no caso de "cadastro de usuário"), testes de NÃO gravar conta corrente 
existente (no caso de um banco), entre outros.

Crie a classe `TesteDeFormDeAlunoCreate` no pacote `ui.api.crud` do diretório de teste de seu
projeto `/src/test/java`

	public class TesteDeFormDeAlunoCreate {
	}
	
Adicione a _Annotation_ `@Test` e torne esta classe uma subclasse de `TesteDeIntegracaoWeb`

	@Test
	public class TesteDeFormDeAlunoCreate extends TesteDeIntegracaoWeb {
	}
	
Importante: Utilize a classe `TesteDeIntegracaoWeb` do projeto correto, pois haverá várias classes 
com este mesmo nome!

Defina a URL atual a partir de `faculdade`.

    public static final String URL = "api/crud/faculdade/curso/direito/aluno";

Esta será a URL onde "estaremos preenchendo" o formulário. 	

### <a id="0_6"> </a>Preparar o método de teste: gravar dados no banco de dados
Iremos criar um método para testar
se os dados inseridos no formulário serão gravados no banco de dados.

Iniciaremos com uma busca que retorna todos os alunos do curso de Direito conforme definido na
URL, para tal, utilizaremos o _buscador de aluno_ e _buscador de curso_. Para mais informações sobre 
buscadores, veja [aqui](http://dojo.objectos.com.br/procedimento/crud-entidade/01.0-implementando_buscador_testes.html).

Defina a variável `BuscarCurso` no inicio da classe.

    @Test
    public class TesteDeFormDeAlunoCreate extends TesteDeIntegracaoWeb {
    	  
    @Inject
    private BuscarCurso buscarCurso;
	
Os códigos a seguir inserem os dados definidos nas variáveis da tabela `ALUNO` no banco de dados.

Nota 1: Se possível, sempre utilize para o teste uma entidade que não possua nenhum registro, por exemplo,
um curso que ainda não tenha nenhum aluno cadastrado (diferente do mostrado aqui, onde todos os cursos já
possuem pelo menos um aluno).<br>
Nota 2: Faça a extração da variável para que futuramente possamos alterar algum valor em um único ponto (a
variável). Evite adicionar valores diretamente nos argumentos dos métodos.

Antes de fazer a declaração do método é necessário carregar os falsos que serão utilizados no teste, os falsos utilizados serão os mesmos do _teste de buscar_ e _cache_, as únicas alterações serão a inserção de `UsuarioFalso`, o método ao invés de `public` será `protected` com a anotação `@Override` ao invés `@BeforeClass`, e o construtor do prepararSqlUnit terá de parâmetro `SqlUnit`, ao invés de ser injetado a parte.

    @Override
    protected void prepararSqlUnit(SqlUnit sqlUnit) {
        sqlUnit.loadEntitySet(UsuariosFalso.class);
        
        sqlUnit.loadEntitySet(AlunosFalso.class);
    }

Criação do método de inserção de dados no formulário.

    public void post() {
    Usuario usuario = UsuariosFalso.USUARIO_A;
    Curso curso = CursosFalso.CURSO_B;

    String nome = "Robson de Souza";
    String matricula = "20120001";

    List<Aluno> alunos = buscarAluno.porCurso(curso);
    assertThat(alunos.size(), equalTo(0));
    
No teste o resultado para busca é _0_, isso se deve ao fato do curso buscado não ser utilizado em nenhum outro teste e como resultado não estar persistido no banco de dados. Geralmente em testes que possuem 3 falsos, uma não é usado justamente para ser utilizado no falso, isso possibilita não truncar a tabela. 
    
    Map<String, Object> form = newHashMap();
    form.put("nome", nome);
    form.put("matricula", matricula);
    form.put("curso", curso.getId());
    
Este bloco simula o usuário inserindo dados no formulário.

    Map<String, String> cookies = login(usuario);
    WebResponse response = jsonClientOf(URL, cookies).post(form);
    FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
    assertThat(json.toString(), json.isValid(), is(true));
    
Este bloco simula o envio dos dados. Todo `TesteDeFormCreate` deverá ter este `post(form)`, se por acaso não tiver o teste não irá funcionar.
    
A responsabilidade de gravar a data de criação é da classe do _Form_.
     
Após o teste da gravação dos dados, iremos comparar se estes dados foram realmente gravados no item
seguinte, isto é, se há a inserção de 1 aluno neste momento e se seus dados são equivalentes aos definidos nas
variáveis. Vejamos:

    alunos = buscarAluno.porCurso(curso);
    assertThat(alunos.size(), equalTo(1));
O resultado da busca agora é _1_, este _1_ garante que o teste está funcionando, pelo fato de antes não haver nenhum registro. As assertivas abaixo são apenas para garantir que este registro corresponde aos dados inseridos através deste form, onde são comparados os atributos passados no form com os buscados no banco de dados.

    Aluno res = alunos.get(0);
    assertThat(res.getNome(), equalTo(nome));
    assertThat(res.getMatricula(), equalTo(matricula));
    assertThat(res.getCurso().getId(), equalTo(curso.getId()));
    
Nota 3: NÃO utilize o `assertThat` para o ID. Isto porque os ids, geralmente possuem um _auto increment_,
o que causará uma falha na segunda execução do teste em diante. Por exemplo, um aluno gravado com `id = 1`
na primeira execução do teste e o aluno gravado com `id = 2` na segunda execução teste, porém a 
variável possui o valor 1:      

	  int id = 1;	

    Aluno res = alunos.get(0);
    assertThar(res.getId(), equalTo(id));

No `ModuloFaculdadeUI` defina a url no método `bindApiCrud()`:

    @Override
    protected void bindApiCrud() {
    at("/api/crud/faculdade/curso/:curso/aluno").serve(FormDeAlunoCreate.class);
    }
    
Você entenderá esta URL quando implementar o _Form_.    
    
Note que haverá erros de compilação pois a classe `FormDeAlunoCreate` ainda não existe. Utilize o
atalho `Ctrl + 1` e crie esta classe no pacote `ui.api.crud` do diretório principal do projeto 
`/src/main/java`.

### <a id="0_7"> </a>Form com Parâmetro

Haverá situações em que se precisará passar parâmetros para os forms, mas como se deve fazer isso? 
Em aproximadamente 80% dos casos os parâmetros são passados via form, dentro do método Map, como no exemplo abaixo:

    Map<String, Object> form = newHashMap();
    form.put("nome", nome);
    form.put("descricao", descricao);
    form.put(“usuario”, usuario.getId());

As informações passadas aqui não necessariamente precisam ser armazenadas no banco de dados, podem ser utilizadas para invalidar um cache por exemplo. O que vale ressaltar é que esta estrutura é a mais utilizada pelo fato ser mais fácil de alterar se por ventura o teste e/ou a implementação estiverem errados.

Uma outra forma de se passar parâmetros é através da `url`, geralmente são utilizados forms mais específicos. Abaixo um exemplo:

    public static final String URL = "api/crud/faculdade/curso/direito/aluno/%d";

Esta opção obriga modificar vários lugares, como o módulo, se por ventura o teste não passar, achar o problema poderá ser mais demorado.<br>
__Importante:__ Sempre que for necessário passar parâmetros, utilize a primeira opção uma vez que se pode resolver 80% dos casos, além de ser mais simples.

Retomando a idéia da especificação, poderiamos ter uma situação onde quisessemos separar
os alunos por curso e período desta forma: <br>`faculdade/curso/direito/periodo/noturno/aluno`.<br> __Assim toda a URL e o teste mudaria, itens como buscadores seriam alterados por exemplo. Lembre-se, tenha em mente a especificação bem definida para tomar decisões quanto a situações deste tipo.__ 

Implementaremos o _Form_ a seguir.	

Para mais informações acesse os códigos nos links abaixo:

[TesteDeFormDeAlunoCreate.java](https://github.com/objectos/objectos-dojo/blob/334dbd82ac72f2683001b242ff13905f8267c69f/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/TesteDeFormDeAlunoCreate.java)<br>
[ModuloFaculdadeUI.java](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/ModuloFaculdadeUI.java)<br>

Siga para o próximo passo. O Form! <a href="{{ site.url }}/procedimento/crud-forms/00-form-implementando-form.html" class="btn btn-success">Continuar!</a><br>
Leia mais uma vez! <a href="#TOPO" class="btn btn-warning">Revisar!</a>
