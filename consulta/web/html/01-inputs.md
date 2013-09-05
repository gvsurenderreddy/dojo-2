---
layout: post-alpha
title: "Inputs"
author: "Carolene Bertoldi"
user: "CaroleneBertoldi"
date: "2013-09-05"
published: true
partof: consulta
num: 1
---

##Introdução

Os inputs são campos de entrada de dados presentes em um formulário, portanto devem sempre ser escritos 
dentro das tags &lt;form&gt; &lt;form/&gt;
Eles possuem tipos distintos que devem ser usados de acordo com o tipo de dado a ser inserido, 
definidos através do atributo type.
Segue abaixo um guia rápido para os tips de inputs e suas aplicações.

## Acesso rápido

Para acessar os tópicos do artigo siga o checklist abaixo:

<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_0">Input checkbox</a>
    </td>    
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_1">Input file</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_2">Input hidden</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_3">Input submit</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_4">Input text</a>
    </td>
  </tr>
</table>

##<a id="0_0"> </a> Input checkbox

O input checkbox é utilizado para os casos de inserção de um campo booleano.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
             &lt;input type="checkbox"
                       name="confirmacao"
                       data-way-field="{
                        'label' : 'Aceito os termos deste contrato'
                       }"/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/WU9PP/">visualizar</a></td>
    </tr>
  </tbody>
</table>

`type` - define o tipo do input

`name` - define o nome do campo, que deve ser igual ao atributo correspondente na implementação do Form
e poderá ser utilizado no CSS para determinar o estilo.

`data-way-field` - é um elemento que, adicionado como atributo, indica a biblioteca objectos-js que o input 
é um objeto do tipo campo, do inglês `field`, que portanto pode possui as mesma configurações.

`label`- atributo de um campo (`data-way-field`) que define uma descrição, um rótulo ao campo.

## Para o caso de edição

Para edição, em checkbox, diferente dos demais inputs para exibir o valor que campo possui, não podemos
utilizar o atributo `value`, utilizamos a seguinte sintaxe:

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
             &lt;input type="checkbox"
                       name="confirmacao"
                       {{#confirmacao}}checked{{/confirmacao}}
                       data-way-field="{
                        'label' : 'Aceito os termos deste contrato'
                       }"/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/WU9PP/">visualizar</a></td>
    </tr>
  </tbody>
</table>

Desta forma será exibido conforme o valor que está no banco de dados.

##<a id="0_1"> </a> Input file

Abre uma caixa de diálogo para carregar um arquivo. 
É necessário inserir o atributo `enctype="multipart/form-data"` ao formulário, pois este atributo 
indica que existe um file a ser enviado pelo formulário.

Este atributo `enctype` indica o tipo de dado que será enviado, e nos casos dos formulários method="post",
possuem um valor padrão, porém para envio de arquivos é necessário uma declaração explicíta do atributo.
Ver <a href="http://www.w3.org/TR/html401/interact/forms.html#h-17.3">Form elemento</a>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;form enctype="multipart/form-data"&gt;
              &lt;input type="file"
                        name="open"/&gt;
            &lt;/form&gt;                     
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/WY3TA/">visualizar</a></td>
    </tr>
  </tbody>
</table>

`type` - define o tipo do input

`name` - define o nome do campo, que deve ser igual ao atributo correspondente na implementação do Form
e poderá ser utilizado no CSS para determinar o estilo.

##<a id="0_2"> </a> Input hidden

Campo não exibido, como o próprio nome indica "escondido". Normalmente utilizado nos casos em que o 
formulário é preenchido com uma informação que já existe na página em que está. 
Neste caso ele sempre será preenchido com um valor pré-definido por uma variável.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        Em uma página de aluno está um formulário 
        de atividade a ser entregue.
        
        Atividade tem um atributo aluno.
        <pre>
          <code>
            &lt;input type="hidden"
                     name="aluno"
                     value="{{aluno.id}}"/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/NWND9/">visualizar</a></td>
    </tr>
  </tbody>
</table>

`type` - define o tipo do input

`name` - define o nome do campo, que deve ser igual ao atributo correspondente na implementação do Form
e poderá ser utilizado no CSS para determinar o estilo.

`value` - pré-define o valor de um input
`{{aluno.id}}` - uma variável - `{{ }}` indicam ser uma variavel mustache - que esta recenbendo o id do 
aluno - retirado do contexto da própria página em que está o formulário.

##<a id="0_3"> </a> Input submit

Submit é tipo de input para exibir o botão de envio do formulário. 

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
             &lt;input type="submit" 
                       class="btn btn-primary" 
                       value="Salvar"/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/MQ3S5/">visualizar</a></td>
    </tr>
  </tbody>
</table>

`type` - define o tipo do input

`name` - define o nome do campo, que deve ser igual ao atributo correspondente na implementação do Form
e poderá ser utilizado no CSS para determinar o estilo.

`class` - definições de estilo do botão

`value` - o nome a ser exibido no botão

##<a id="0_4"> </a> Input text

É o tipo de input mais utilizado. O tipo text permite que o usuário digite os dados a serem inseridos.
São utilizados para inserção de dados de texto como nome, por exemplo, mas também é utilizado para inserção 
de valores como datas, moeda, porcentagem. Para este caso utilizamos os tipos de validação.

Atributos padrão:

`type` - define o tipo do input

`name` - define o nome do campo, que deve ser igual ao atributo correspondente na implementação do Form
e poderá ser utilizado no CSS para determinar o estilo.

`class` - definições de estilo do botão

`data-way-field` - é um elemento que, adicionado como atributo, indica a biblioteca objectos-js que o input 
é um objeto do tipo campo, do inglês `field`, que portanto pode possui as mesma configurações.

`label`- atributo de data-way-field que define uma descrição, um rótulo ao campo.

`mask`- define uma máscara, uma formatação para os dados inseridos

`validators` - válida o tipo de dado inserido, por exemplo, uma data não pode ser negativa,mesmo nos 
casos onde não é necessária uma validação é utilizada a validação `required` que válida se o campoesta 
na requisição.

### Campos de texto

São campos em que o usuário irá digitar caracteres, campos comuns como nome, sobrenome, que não precisam de 
formatação e/ou validação.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                      name="nome"
                      class="span2 tac"
                      data-way-field="{
                        'label' : 'Nome',
                        'validators' : ['required']
                      }"/&gt; 
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/4NTsT/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

### Campos de data

É preciso se certificar de duas principais coisas sobre a data digitada pelo usuário. Primeiro obriga-lo
a digitar no padrão exigido e segundo que ele digite uma data válida.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                      name="dataInicial"
                      class="span2 tac"
                      data-way-field="{
                        'label' : 'Data inicial',
                        'mask' : 'fixed.date',
                        'validators' : ['required', 'validate-date']
                      }" /&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/NAp47/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

`mask` - define a formatação do campo

`validate-date` - atribui a validação a nececidade de tratar o input como campo de data.

### Edição de um campo data

Para edição de um campo data, deve ser adicionado o atributo `value` recebndo a variável mustache
e a formatação da data. Ver <a>Habilitando form update na web</a>
É importante que se diga o tipo de formatação, pois a data não pode ser exibida sem a definição de 
uma formatação.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                      name="dataInicial"
                      class="span2 tac"
                      value={{dataInicial.iso}}
                      data-way-field="{
                        'label' : 'Data inicial',
                        'mask' : 'fixed.date',
                        'validators' : ['required', 'validate-date']
                      }" /&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/s6Ek8/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

Obs. No exemplo utilizamos a formatação `iso` que poderia também ser substituida pelos demais tipos
de formatação, segundo o tipo de fortação desejado.

Os tipos de formatação são:

`iso` - Possui formatação `yyyy-MM-dd`, ou seja ano-mês-dia, exemplo, 2013-09-06

`dd/MM/yy` - Possui formatação dia/mês/ano, sendo ano com dois digitos, exemplo, 06/09/13

`dd/MM/yyyy` - Possui formatação dia/mês/ano, sendo ano com quatro digitos, exemplo, 06/09/2013

`MM/dd/yyyy` - Possui formatação mês/dia/ano, sendo ano com quatro, exemplo, 09/06/2013

### Campos de CPF

Para mácara de CPF é utilizado no `data-way-field`,`'mask : fixed.cpf'` e `'validators' : [validate-cpf]'.
Como no exemplo abaixo:

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                      name="cpf"
                      class="span2"
                      data-way-field="{
                        'label' : 'CPF',
                        'mask' : 'fixed.cpf',
                        'validators' : ['validate-cpf']
                      }"&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/jF52T/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

### Campos de CNPJ

Para mácara de CNPJ é utilizado no `data-way-field`,`'mask : fixed.cnpj'` e `'validators' : [validate-cnpj]'.
Como no exemplo abaixo:

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                      name="cnpj"
                      class="span2"
                      data-way-field="{
                        'label' : 'CNPJ',
                        'mask' : 'fixed.cnpj',
                        'validators' : ['validate-cnpj']
                      }"&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/jF52T/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

### Campos de valor monetário

Deve adicionar o atributo `mask` a `data-way-field` atribuindo o valor, que define o tipo monetário.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                name="preco"
                class="span2"
                data-way-field="{
                  'label' : 'Preço',
                  'mask' : 'reverse.reais',
                  'validators' : ['required']
                 }" /&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/cn59v/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

No exemplo utilizamos o tipo `reverse.reais`, porque queremos o valor monetário em reais, mas existem
outros tipos.

Tipos de mácara de valor monetário:

`reverse.reais` - valor em Reais

`reverse.dollar` - valor em Dólares

### Campos com valores decimais

Para armazenar dados com valores decimais utilize a mascara `reverse.decimal`, que possui precisão de 
até 5 casas decimais e permite números negativos. Pode ser utilizado para armazenar valores de percentuais.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                name="peso"
                class="span2"
                data-way-field="{
                  'label' : 'Peso',
                  'mask' : 'reverse.decimal',
                  'validators' : ['required']
                 }" /&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/LMeG2/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

### Campos com valores arredondados 

A máscara `reverse.integer-us` arredonda valores decimais, transformando-os em inteiros.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Exemplo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <pre>
          <code>
            &lt;input type="text" 
                name="peso"
                class="span2"
                data-way-field="{
                  'label' : 'PIB em milhões',
                  'mask' : 'reverse.integer-us',
                  'validators' : ['required']
                 }" /&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/4dtVg/">visualizar</a></td>
    </tr>   
  </tbody>
</table>