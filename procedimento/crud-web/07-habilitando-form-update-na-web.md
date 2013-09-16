---
layout: post-alpha
title: "Habilitando form update na web"
author: "Carolene Bertoldi"
user: "CaroleneBertoldi"
date: "2013-09-03"
published: true 
partof: procedimento-crud-web
num: 7
---

##<a id="topo"> </a> Introdução

Para habilitarmos um formulário de edição podemos reaproveitar o próprio formulário create,
fazendo apenas algumas modificações.
Esta forma de edição é válida somente quando a implementação do formulário possibilita tanto 
inserção quanto edição na mesma classe dependendo apenas do tipo de requisição ser post ou put.

Temos o seguinte formulário de aluno, o qual utilizaremos também no form de edição.

<pre>
  <code>
    &lt;form action="{{bricks.baseUrl}}/api/crud/faculdade/:faculdade/aluno"
          method="post" 
          data-way-form="bootstrap-horizontal"
          data-way-form-chain="{
            'dissolve' : 'form-aluno',
            'reRender' : 'aluno-list'
          }"&gt;
       &lt;fieldset&gt;
       
          &lt;input type="hidden" 
                    name="faculdade"
                    value="{{faculdade.id}}"/&gt;       
    
          &lt;input type="text" 
                    name="nome"
                    class="span2 tac"
                    data-way-field="{
                      'label' : 'Nome',
                      'validators' : ['required']
                    }"/&gt; 
                  
          &lt;input type="text" 
                    name="dataDeNascimento"
                    class="span2 tac"
                    data-way-field="{
                      'label' : 'Data de nascimento',
                      'mask' : 'fixed.date',
                      'validators' : ['required', 'validate-date']
                    }"/&gt; 
                              
          &lt;select name="curso"
                      class="span2"
                      data-way-field="{
                        'label' : 'Curso'
                      }"&gt;
              {{#cursos}}
              &lt;option value="{{id}}"&gt;{{codigo}}&lt;/option&gt;
              {{/cursos}}
           &lt;/select&gt;
              
          &lt;input type="checkbox" 
                    name="bolsa"
                    data-way-field="{
                      'label' : 'Possui bolsa de estudo?'
                    }" /&gt;   
            
          &lt;div class="form-actions"&gt;
            
              &lt;input type="submit" 
                        class="btn btn-primary" 
                        value="Salvar" /&gt;
              
              &lt;a href="#" 
                    data-way-dissolve="aluno-form"&gt;Cancelar&lt;/a&gt;
               
          &lt;/div&gt;
    
       &lt;/fildset&gt;
    &lt;form&gt;
  </code>
</pre>

##1. Inserindo o id

A principal informação que fará a implementação do formulário "entender" que é uma requisição de edição 
(put) e não de inserção (post) é o fato de o formulário passar um id.
Por isso utilizaremos um input hidden, ou seja, que não será exibido, value preenchido com a variavel 
que fornecerá o id e data-way-form-pk para indicar que o campo é uma chave primária.

    <input type="hidden" 
           name="id"
           value="{{aluno.id}}"
           data-way-form-pk />
           
O atributo `data-way-form-pk` é imprescindível para que a edição do formulário funcione. Pois indica que o
resgistro posui um id (`hasid`), sendo este id passado para a implementação do formulério que ao receber o id
"entende" que trata-se de uma edição e não de inserção e não cria um novo id, permitindo edição do registro
correspondente ao id.

##2. Acrescentando value

Quando solicitada a edição de um registro, o formulário aparecerá por padrão preenchido com os campos atuais
para que o usuário possa modificar apenas os valores que deseja, mantendo os demais. O value é o atributo
responsável por exibir estas informações, portanto acrescentaremos value preenchido com variável correspondente
a cada campo.

<pre>
  <code>
      &lt;input type="hidden" 
                name="faculdade"
                value="{{faculdade.id}}"/&gt;          
    
      &lt;input type="text" 
                name="nome"
                value="{{aluno.nome}}"
                class="span2 tac"
                data-way-field="{
                  'label' : 'Nome',
                  'validators' : ['required']
                }"/&gt;            

      &lt;input type="text" 
                name="dataDeNascimento"
                class="span2 tac"
                value="{{aluno.dataDeNascimento}}"/&gt;                     
                data-way-field="{
                  'label' : 'Data de nascimento',
                  'mask' : 'fixed.date',
                  'validators' : ['required', 'validate-date']
                }" /&gt; 
                          
      &lt;select name="curso"
                  class="span2"
                  value="{{curso.id}}"/&gt;                         
                  data-way-field="{
                    'label' : 'Curso'
                  }"&gt;
          {{#cursos}}
          &lt;option value="{{id}}"&gt;{{codigo}}&lt;/option&gt;
          {{/cursos}}
       &lt;/select&gt;
          
      &lt;input type="checkbox" 
                name="bolsa"
                {{#bolsa}}checked{{/bolsa}}                    
                data-way-field="{
                  'label' : 'Possui bolsa de estudo?'
                }" /&gt;  
  </code>
</pre>                    
            
É importante observar que as variáveis mustache, {{contexto.atributo}}, atribuidas a value nos inputs são 
compostas de um contexto, no caso "aluno" e o nome do campo, que deve ser o mesmo valor de name e mesmo valor
definido em AlunoSerializer para serialização dos dados.         

O form agora poderá ser utilizado tanto para cadastro de alunos como para edição.

<pre>
  <code>
    &lt;form action="{{bricks.baseUrl}}/api/crud/faculdade/:faculdade/aluno"
          method="post" 
          data-way-form="bootstrap-horizontal"
          data-way-form-chain="{
            'dissolve' : 'form-aluno',
            'reRender' : 'aluno-list'
          }"&gt;
       &lt;fieldset&gt;
       
          &lt;input type="hidden" 
                    name="id"
                    value="{{aluno.id}}"
                    data-way-form-pk />       
       
          &lt;input type="hidden" 
                    name="faculdade"
                    value="{{faculdade.id}}"/&gt;          
        
          &lt;input type="text" 
                    name="nome"
                    value="{{aluno.nome}}"
                    class="span2 tac"
                    data-way-field="{
                      'label' : 'Nome',
                      'validators' : ['required']
                    }"/&gt;            

          &lt;input type="text" 
                    name="dataDeNascimento"
                    class="span2 tac"
                    value="{{aluno.dataDeNascimento}}"/&gt;                     
                    data-way-field="{
                      'label' : 'Data de nascimento',
                      'mask' : 'fixed.date',
                      'validators' : ['required', 'validate-date']
                    }" /&gt; 
                              
          &lt;select name="curso"
                      class="span2"
                      value="{{curso.id}}"/&gt;                         
                      data-way-field="{
                        'label' : 'Curso'
                      }"&gt;
              {{#cursos}}
              &lt;option value="{{id}}"&gt;{{codigo}}&lt;/option&gt;
              {{/cursos}}
           &lt;/select&gt;
              
          &lt;input type="checkbox" 
                    name="bolsa"
                    {{#bolsa}}checked{{/bolsa}}                    
                    data-way-field="{
                      'label' : 'Possui bolsa de estudo?'
                    }" /&gt;   
            
          &lt;div class="form-actions"&gt;
            
              &lt;input type="submit" 
                        class="btn btn-primary" 
                        value="Salvar" /&gt;
              
              &lt;a href="#" 
                    data-way-dissolve="aluno-form"&gt;Cancelar&lt;/a&gt;
               
          &lt;/div&gt;
    
       &lt;/fildset&gt;
    &lt;form&gt;
  </code>
</pre>     
