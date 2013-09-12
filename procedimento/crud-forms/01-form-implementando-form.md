---
layout: post-alpha
title: "Implementando Form Create: Form"
author: "Anderson A. Silva"
user: "asilva26"
date: "2013-09-16"
published: true
partof: procedimento-crud-forms
num: 1
---

##Implementação do form

A implementação do form deve ser realizada no diretório `src/main/java`, na primeira linha é feita a declaração do `forms`, para este primeiro instante só a declaração do forms será suficiente. Após isso aprtar `Alt + S + A` para a criação de do construtor com argumentos.

    public class FormDeAlunoCreate {
    
    private final Forms forms;
    
    FormDeAlunoCreate(Forms forms) {
     this.forms = forms;
    }

 O próximo passo é criar o método post, que será responsável pelo envio das informações do formulário sem que seja informado o conteúdo. Para entender melhor isso, dê uma lida neste material [get e post](http://marceloweb.info/principais-diferencas-entre-os-metodos-http-get-e-post/).

    @Post
    public Reply<?> post() {
        return reply();
    }

Após a declaração deste método note que um erro ocorrerá pois o reply ainda não existe, para corrigir este erro será criado o método que ficará assim:

    private Reply<?> reply() {
     return forms.of(PojoJson.class)
    
        .reply();
    }

O erro no post será corrigido porém um novo erro irá aparecer, pelo fato da classe PojoJson não existir, desta forma o próximo passo é criar uma inner class PojoJson.

  private static class PojoJson implements EntityJson {

    int id;
    String nome;
    String matricula;
    int curso;

Aqui são feitas as declarações de variáveis com os tipos de dados que seja deseja gravar no banco. 

__IMPORTANTE:__ Mesmo que se utilize uma outra interface no form, ao se fazer a declaração das variáveis deve-se utilizar os tipos primitivos de dados, NÃO fazer sob hipótese alguma a declaração do objeto, pois o form não sabe qual dado que será utilizado. 
Ex: Se em curso tivesse sido passado o objeto inteiro ao invés do `int` iria aparecer um erro parecido com este no console:

`FAILED: post
com.google.sitebricks.client.TransportException: org.codehaus.jackson.JsonParseException: Unexpected character ('<' (code 60)): expected a valid value (number, String, array, object, 'true', 'false' or 'null')
 at [Source: org.jboss.netty.buffer.ChannelBufferInputStream@1f2cdb01; line: 1, column: 2].`

    @Override
    public EntityMapping toMapping() {
     return Relational.table("BANCO.ALUNO")
    
         .id("ID", id, new GeneratedKeyListener() {
    @Override
    public void set(GeneratedKey key) {
      id = key.getInt();
     }
    })

Este método é bem parecido com o GeneratedKey do Jdbc, e tem a mesma funcionalidade, gerar um novo Id, de forma automática, ao gerar um novo registro automaticamente será gerado um novo id.

    .col("NOME", nome)
    .col("MATRICULA", matricula)
    .col("CURSO_ID", curso);
Aqui são declaradas quais dados serão armazenados no banco de dados, id e data de criação por serem gerados de forma automática, não são declarados.

Em alguns análogos vai ser comum encontrar a declaração da classe `EventBus` bem como sua instância, que é utilizada para fazer a invalidação do cache, neste exemplo por ser um passo a passo mais genérico não foi utilizado.


##Código

[TesteDeFormDeAlunoCreate.java](https://github.com/objectos/objectos-dojo/blob/334dbd82ac72f2683001b242ff13905f8267c69f/objectos-dojo-team/src/main/java/br/com/objectos/dojo/taguiar/FormDeAlunoCreate.java)
