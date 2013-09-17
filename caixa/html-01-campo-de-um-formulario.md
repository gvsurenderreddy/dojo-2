---
layout: post-alpha
title: "Campos de um formulário"
author: "Carolene Bertoldi"
user: "CaroleneBertoldi"
date: "2013-09-05"
published: true
partof: html
num: 1
---

##Introdução

Os inputs e combobox são campos de entrada de dados presentes em um formulários, portanto devem sempre ser escritos 
dentro das tags &lt;form&gt; &lt;form/&gt;
Eles possuem tipos distintos que devem ser usados de acordo com o tipo de dado a ser inserido, 
definidos através do atributo type.
Segue abaixo um guia rápido para os tipos de inputs e suas aplicações.

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
      <a id="topo_0_2"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_2">Input hidden</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_3"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_3">Input submit</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_4"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_4">Input text</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_5"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_5">Combobox</a>
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

Atributos de um `data-way-field` utilizados em um checbox:

`label`- atributo de um campo que define uma descrição, um rótulo ao campo.

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
                       {% raw  %}{{#confirmacao}}checked{{confirmacao}}{% endraw %}
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

Desta forma será exibido no campo o valor correspondente que está no banco de dados.

##<a id="0_1"> </a> Input file

Abre uma caixa de diálogo para carregar um arquivo. 
É necessário inserir o atributo `enctype="multipart/form-data"` ao formulário, pois este atributo 
indica que existe um file a ser enviado pelo formulário.

O atributo `enctype` indica o tipo de dado que será enviado, e quando não declarado possuem um valor padrão, 
de envio de texto, porém para envio de arquivos é necessário uma declaração explicíta do atributo.
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

Em uma página de aluno está um formulário atividade a ser entregue.Atividade tem um atributo aluno.

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
            &lt;input type="hidden"
                     name="aluno"
                     value={% raw  %}{{aluno.id}}{% endraw %};
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
`\{\{aluno.id\}\}` - uma variável - `\{\{ \}\}` indicam ser uma variavel mustache - que esta recenbendo o id do 
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

Atributos padrão a todos os inputs `type="text":`

`type` - define o tipo do input

`name` - define o nome do campo, que deve ser igual ao atributo correspondente na implementação do Form
e poderá ser utilizado no CSS para determinar o estilo.

`class` - definições de estilo do botão

`data-way-field` - é um elemento que, adicionado como atributo, indica a biblioteca objectos-js que o input 
é um objeto do tipo campo, do inglês `field`, que portanto pode possui as mesma configurações.

Atributos de um `data-way-field`:

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
e a formatação da data. Ver <a href="http://dojo.objectos.com.br/procedimento/crud-web/07-habilitando-form-create-na-web.html">Habilitando form update na web</a>
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
                  value={% raw  %}{{dataInicial.iso}}{% endraw %}
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

Para mácara de CPF é utilizado no `data-way-field`,`'mask : fixed.cpf'` e `'validators' : [validate-cpf]'`.
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

Para mácara de CNPJ é utilizado no `data-way-field`,`'mask : fixed.cnpj'` e `'validators' : [validate-cnpj]'`.
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

##<a id="0_5"> </a> Combobox

Comboboxs são utilizadas para o caso de atributos do formulário que devem ser preenchidos com uma opção de valor
de uma lista pré-fixada, 
Normalmente quando possuimos a informação de quais são todos os valores possiveis que um campo pode receber. Estas 
opções de valores podem vir de Enums, objetos em composição, ou simplesmente uma lista.

A principal vantagem da utilização da combobox é não permitir que o usuário insira um valores duplicados ou
que não esteja em concordancia com os dados esperados. Um exemplo clássico de aplicação seria, "em um formulário 
deve ser inserida a informação do estado onde a pessoa reside",sem uma combobox usuário pode digitar BA, ou Bahia 
e o sistema "entenderia" como estados diferentes, além disso o usuário poderia cadastrar no sistema estados que não existem. 
Como sabemos quais são todos os estados do Brasil, então a melhor opção seria utilizar uma combobox que permitiria 
ao utuário apenas selecionar uma opção pré-existente, e como ele mora no Brasil, alguma opção certamente se adequaria a
sua realidade.

Uma combobox também é recomendada quando há composição de objetos, ou seja, quando um objeto é atributo de outro.como
neste para criar um novo registro, é necessáriamente preciso que este objeto atributo pré-exista, então uma combobox carrega
as possiveis opções de valores que o atributo poderá receber, garantindo assim a integridade dos dados cadastrados.

### Combobox com dados serealizados

Forma mais comum de escrever uma combobox. 
Para entender este conceito é preciso entender que uma section, {{#contexto}}{{/contexto}}, funciona de forma
análoga a um loop (mais especificamnete um foreach), exibindo as variaveis serealizadas correspondente ao contexto. 

#### Objetos em composição

Combobox carregada a partir de dados serealizados acontece normalmente quando a composição de objetos, ou seja
um objeto possui como atributo um outro objeto. que necessáriamente deve existir anteriormente ao objeto compõe. 
Assim podemos garantir que o campo será realmente preenchido com um objeto do tipo do atributo correto,
pois a combobox lista todos os valores possiveis ao preenchimento do campo,

Abaixo um exemplo de um formulario de Usuário que possui o objeto Perfil como atributo:

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
            &lt;select name="perfil"
                       value={% raw  %}{{perfil.id}}{% endraw %}
                       data-way-field="{
                        'label' : 'Perfil'
                       }"&gt;
                  <code>
                  {% raw  %}{{#perfis}}{% endraw %}
                    &lt;option value="{% raw  %}{{id}}{% endraw %}"&gt; {% raw  %}{{nome}}{% endraw %} &lt;/option&gt;
                  {% raw  %}{{/perfis}}{% endraw %}
             &lt;/select/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/WK8Sg/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

Note no caso de Perfil ser uma classe,`"perfis"` é o contexto para que a lista da combobox seja carregada e deve ser adicionado na página em
que o formulário ficará, carregando a lista a partir do buscador ou do cache.

    private Context context() {
      Context c = way.contexts().get(new Meta());
  
      List<Perfil> perfis = buscar.porTodos();
      c.put("perfis", perfis);
  
      return c;
    }

ou 

    private Context context() {
      Context c = way.contexts().get(new Meta());
  
      List<Perfil> perfis = cache.porTodos();
      c.put("perfis", perfis);
  
      return c;
    }

<div class="alert alert-warning">NOTA: Muito cuidado se estiver utilizando pagePorTodos().</div>

O pagePorTodos() é um método que retorna um PageList, e o PageList foi construido para paginação e não é uma boa opção
para combobox, pois exibi um limite itens por página. Exemplo, o PageList está definido para exibir apenas
5 itens por página se a sua combobox tem 6 itens e for utilizado um pagePorTodos() o item 6 não aparecerá
na lista.

Uma forma de resolver esta questão seria colocando option dentro da seção `rows`.

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
            &lt;select name="perfil"
                       value="{{perfil.id}}"
                       data-way-field="{
                        'label' : 'Perfil'
                       }"&gt;
                  {% raw  %}{{#perfis}}{% endraw %}
                    {% raw  %}{{#rows}}{% endraw %}
                     &lt;option value="{{id}}"&gt;{% raw  %}{{nome}}{% endraw %}&lt;/option&gt;
                    {% raw  %}{{/rows}}{% endraw %}
                  {% raw  %}{{/perfis}}{% endraw %}
             &lt;/select/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/WK8Sg/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

Podendo escolher, prefira sempre o método porTodos();

#### Enuns serealizados

Uma combobox carregada a partir de um Enum serealizado, se comporta da mesma forma de um dado serealizado.
Ocorre quando temos um Enum como atributo do objeto que queremos cadastrar, o não deixa de ser uma composição.
A única diferença se dará na declaração do contexto na Page.java, que é a página java que carrega o contexto
respectiva a página onde o formulário será exibido.

Mustache:

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
            &lt;select name="uf"
                  class="span7"
                  data-way-field="{
                    'label' : 'Estados'
                  }">
                  {% raw  %}{{#estados}}{% endraw %}
                    &lt;option value="{{nome}}"&gt;{% raw  %}{{descricao}}{% endraw %}&lt;/option&gt;
                  {% raw  %}{{/estados}}{% endraw %}
            &lt;select/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/8N5Ct/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

Page.java:

    private Context context() {
        Context c = way.contexts().get(new Meta());
       
        c.put("estados", Estados.values());
    
        return c;
    }

### Combobox com dados não serealizados `"{{.}}"`

Já sabemos que uma section {{#contexto}}{{/contexto}}, funciona de forma análogaa um loop, exibindo as 
variaveis serealizadas correspondente ao contexto, mas e se os dados não estiverem serealizados? 
Necessariamente precisamos serealizar o atributo se form um objeto, Enum, ou List?

Se form um objeto, sim, é necessário serealizar a classe deste objeto. Porém se form um List&lt;String&gt;
(que não deixa de ser um String\[\]) ou um Enum podemos utilizar `"{{.}}"` para exibir os itens da lista.

Precisamos entender que `"."` na serealização indica `\{\{contexto_do_objeto.nome_do_atributo\}\}`, no caso 
dos estados, se não estivermos na section estados faremos

    {% raw  %}{{estados.sigla}}{% endraw %}

e será exibida a sigla do estado. Porém se estivermos na section de estados, podemos chamar apenas 
{{sigla}} diretamente e a sigla será exibida.

    {% raw  %}{{#estados}}{% endraw %}
      {% raw  %}{{sigla}}{% endraw %}
    {% raw  %}{{/estados}}{% endraw %}

Mas no caso de não termos serealização nos dados o `"."`, indicará exibição de um item da lista, 
em um loop até o fim da lista.

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
            &lt;select name="uf"
                  class="span7"
                  data-way-field="{
                    'label' : 'Estados'
                  }">
                  {% raw  %}{{#estados}}{% endraw %}
                   &lt;option value="{{.}}"&gt;{% raw  %}{{.}}{% endraw %}&lt;/option&gt;
                  {% raw  %}{{/estados}}{% endraw %}
            &lt;select/&gt;
          </code>
        </pre>
      </td>
      <td><a href="http://jsfiddle.net/8N5Ct/">visualizar</a></td>
    </tr>   
  </tbody>
</table>

Por isso,`"{{.}}"` não funciona com objetos, pois um item seria um objeto, que não serealizado
exibiria `"object[]"`, no caso de String\[\], seria exibido um texto, e por isso seria exibido
sem problemas.

E no caso do Enum, é importante lembrar que deve ser declarado o contexto na Page.java da seguinte maneira:

     private Context context() {
      Context c = way.contexts().get(new Meta());
  
      c.put("estados", Estados.values());
  
      return c;
    }