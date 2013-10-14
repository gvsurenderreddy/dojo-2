---
layout: post-alpha
title: "Casos Especiais com o método preparSqlUnit"
author: "Cristiane Iope Pelissari"
user: "cpelissari"
date: 2013-10-01
published: true 
partof: faq-crud-entidade
num: 9
---

##Introdução

Neste artigo abordaremos situações que fogem do escopo do procedimento padrão utilizado para criação
de _testes de busca_. Criaremos um _Teste De Buscar_ envolvendo entidades que possuam herança e que
utilizem a mesma tabela do banco de dados para inserir seus objetos falsos, focando principalmente
no método `prepararSqlUnit()`. 

## Antes de iniciar

Este artigo assume conhecimento prévio nos assuntos tratados nos artigos abaixo listados:

[Implementando Buscador: Testes](http://dojo.objectos.com.br/procedimento/crud-entidade/01.0-implementando_buscador_testes.html)

[implementação do Buscador](http://dojo.objectos.com.br/procedimento/crud-entidade/01.1-implementando_buscador_buscadores.html)

[Objetos falsos](http://dojo.objectos.com.br/procedimento/crud-entidade/00.1-criando-objetos-falsos.html)

## Criando o método prepararSqlUnit()

Para nossos exemplos consideraremos que as classes de objetos falsos foram previamente criadas.
  
Ao construirmos um _Teste De Buscar_, o primeiro método a ser criado em nosso _Teste_ é o `prepararSqlUnit()`,
em sua implementação utilizamos um outro método chamado `loadEntitySet()` pertencente a interface `SqlUnit`
e nele passamos como parâmetro a classe de objetos falsos em que estamos trabalhando e todas as classes
que se relacionem com ela.   

    @BeforeClass
    public void prepararSqlUnit() {
     sqlUnit.loadEntitySet(EntidadesFalso.class);
    }

Internamente o método `loadEntitySet()` atua da seguinte forma: primeiramente ele “trunca”, ou seja, zera
todos os registros da tabela no banco de dados referente a entidade que foi passada como parâmetro e em seguida ele 
insere os objetos falsos.

### Ordenação do método prepararSqlUnit() 

Para exemplificar, vamos criar o método `prepararSqlUnit()` para o `TesteDeBuscarFuncionario`, consideraremos
que a entidade `Funcionario` possui um relacionamento com a classe `Departamento` e que existem vários 
tipos de departamentos: Compras, financeiro e vendas. Todos estes tipos de departamento herdam da `superclasse`  
`Departamento` e todos os objetos falsos são inseridos em uma mesma tabela no banco de dados, na tabela 
`DEPARTAMENTO`.
 
Sabemos que a tabela `FUNCIONARIO` se relaciona com a tabela `DEPARTAMENTO`, por tanto ao realizar
uma busca por funcionários, seus respectivos departamentos também deverão ser exibidos.

     @Test
     @Guice(modules = { ModuloDeTesteFuncionario.class })
     public class TesteDeBuscarFuncionario {

     @Inject
     private BuscarFuncionario buscarFuncionario;

     @Inject
     private SqlUnit sqlUnit;

     @BeforeClass
     public void prepararSqlUnit() {
      sqlUnit.loadEntitySet(FuncionariosFalso.class);
    
      sqlUnit.loadEntitySet(DepartamentosComprasFalso.class);
      sqlUnit.loadEntitySet(DepartamentosFinanceiroFalso.class);
      sqlUnit.loadEntitySet(DepartamentosVendasFalso.class);
     }

Em nosso exemplo, a classe `FuncionariosFalso` é a primeira a ser passada como parâmetro para o método
`loadEntitySet()`. Internamente o método `loadEntitySet()` irá zerar todos os registros da tabela `FUNCIONARIO`
e em seguida irá inserir os registros de `Funcionariosfalso` no banco de dados.

Dentro da tabela `FUNCIONARIO` existe uma coluna chamada `DEPARTAMENTO_ID` que contém o `ID` da tabela
`DEPARTAMENTO`. Porém, de acordo com a ordenação que estamos seguindo agora esta coluna estará vazia 
e por isso ao executarmos nosso teste o seguinte erro ocorrerá: 

    FAILED: busca_por_funcionario
    java.lang.NullPointerException  

Este erro ocorre no método `getDepartamento.getId()` da classe `FuncionarioToString`, que retorna _null_,
pois esta informação proveniente da coluna `DEPARTAMENTO_ID` está vazia no banco de dados ja que o método
`loadEntitySet()` das entidades _departamentos_ não foi executado até este momento e portanto o registro de
nenhum dos _departamentos_ foi inserido. 

O método `prepararSqlUnit()` deverá seguir uma ordenação onde a classe em que estamos trabalhando
deverá ser sempre a última a ser passada como parâmetro para o método `loadEntitySet()`, ou seja, devemos 
primeiramente passar como parâmetro os falsos que possuem relacionamento com a classe de nosso
interesse para que todas as informações dos campos referentes a eles sejam devidamente preenchidos. 

Portanto, a ordenação __correta__ para o método `prepararSqlUnit()` de nosso exemplo ficará conforme
abaixo:

     @Test
     @Guice(modules = { ModuloDeTesteFuncionario.class })
     public class TesteDeBuscarFuncionario {

     @Inject
     private BuscarFuncionario buscarFuncionario;

     @Inject
     private SqlUnit sqlUnit;

     @BeforeClass
     public void prepararSqlUnit() {
      sqlUnit.loadEntitySet(DepartamentosComprasFalso.class);
      sqlUnit.loadEntitySet(DepartamentosFinanceiroFalso.class);
      sqlUnit.loadEntitySet(DepartamentosVendasFalso.class);
    
      sqlUnit.loadEntitySet(FuncionariosFalso.class);
    }

###Casos especiais de uso do método prepararSqlUnit()

Apesar de termos seguido a ordem correta no exemplo acima, iremos nos deparar com outro problema. 
Conforme citado no início deste artigo, todos os registros de _departamentos falsos_ estão utilizando
a mesma tabela `DEPARTAMENTO` no banco de dados. Sendo assim, quando passamos `DepartamentosComprasFalso`
como parâmetro para o método `loadEntitySet()`, todos os registros de `DEPARTAMENTO` são "zerados" pelo 
método `truncate()` e em seguida o método `load()` insere os registros de `DepartamentosComprasFalso` 
nesta tabela. Na próxima linha de código, a classe `DepartamentosFinanceiroFalso` é passada como parâmetro
para o método `loadEntitySet()` e a tabela `DEPARTAMENTO` tem seus registros zerados novamente, apagando desta
forma todos os dados de `DepartamentosComprasFalso` que acabaram de ser inseridos e após isso, os registros
de `DepartamentosFinanceiroFalso` são inseridos no banco de dados.

Desta forma, podemos observar que a cada classe falsa que passamos como parâmetro para o método `prepararSqlUnit()`
todos os registros anteriores são apagados e somente os registros da última tabela passada como parâmetro 
estão persistindo no banco de dados, ou seja, em nosso exemplo somente os registros de `DepartamentosVendasFalso`
estão persistindo.   

Esta situação ocorre devido ao fato de estarmos "truncando" várias vezes a mesma tabela no banco de dados.

Ao executarmos o `TesteDeBuscarFuncionario`, o seguinte erro ocorrerá na classe `FuncionarioLoader` 
que é utilizada na implementação do _Buscador_: 

    java.lang.ClassCastException
 

A classe `FuncionarioLoader` implementa o construtor da interface `Funcionario`, sobrescrevendo os 
métodos `get()` de cada departamento e fazendo com que eles retornem o conteúdo da coluna `DEPARTAMENTO_ID` 
do banco de dados.  

Exemplo de implementação dos métodos `getDepCompras()` e `getDepFinanceiro()` da classe `FuncionarioLoader`:

     @Override
     public DepartamentoCompras getDepCompras() {
      DepartamentoCompras dep = null;

      rs.getInt("DEPARTAMENTO_COMPRAS_ID");
      if (!rs.wasNull()) {
        ResultSet resultSet = rs.getResultSet();
        dep = (DepartamentoCompras) new DepartamentoLoader("DEPARTAMENTO_COMPRAS").load(resultSet);
      }
      return dep;
    }
    
     @Override
     public DepartamentoFinanceiro getDepFinanceiro() {
      DepartamentoFinanceiro dep = null;

      rs.getInt("DEPARTAMENTO_FINANCEIRO_ID");
      if (!rs.wasNull()) {
        ResultSet resultSet = rs.getResultSet();
        dep = (DepartamentoFinanceiro) new DepartamentoLoader("DEPARTAMENTO_FINANCEIRO").load(resultSet);
      }
      return dep;
    }
    

O erro está acontecendo na classe `Loader` porque, conforme o exemplo acima, somente os registros da 
última classe de falsos passada como parâmetro para o método `prepararSqlUnit()` estão persistindo no
banco de dados, que neste caso seria o `DepartamentosVendasFalso`. Por isso, na classe `FuncionarioLoader`
quando tentamos converter este registro para o tipo `DepartamentoCompras` ocorrerá este erro de conversão 
em tempo de execução.   

###Caso especial de classe falsa 

Para resolver este problema, criaremos uma classe chamada `DepartamentosFalso` onde todos os tipos de
departamentos serão inseridos no banco de dados e a tabela `DEPARTAMENTO` será "truncada" uma única
vez.

    public class DepartamentosFalso implements EntitySet {

     private static final DepartamentosComprasFalso compras = new DepartamentosComprasFalso();

     private static final DepartamentosFinanceiroFalso financas = new DepartamentosFinanceiroFalso();

     private static final  DepartamentosVendasFalso vendas = new DepartamentosVendasFalso();

     DepartamentosFalso() {
     }

     @Override
     public void truncate(Truncate truncate) {
      truncate.table("EMPRESAS.DEPARTAMENTO");
     }

     @Override
     public void load(SqlUnit sqlUnit) {
      compras.load(sqlUnit);
      financas.load(sqlUnit);
      vendas.load(sqlUnit);
     }

    }
    
No `TesteDeBuscarFuncionario` iremos passar como parâmetro apenas a classe `DepartamentosFalso`
que internamente já insere todos os tipos de departamento através do método `load()`.
   

    @BeforeClass
    public void prepararSqlUnit() {
     sqlUnit.loadEntitySet(DepartamentosFalso.class);
    
     sqlUnit.loadEntitySet(FuncionariosFalso.class);
    }      
    
Pronto, agora nosso `TesteDeBuscarFuncionario` será executado com sucesso.
   
Esta solução poderá ser aplicada sempre que formos trabalhar com casos de entidades que possuam herança
e que utilizem a mesma tabela no banco de dados para inserção de registros.  
    
