---
layout: post-alpha
title: "Habilitando form na web"
author: "Carolene Bertoldi"
user: "CaroleneBertoldi"
date: "2013-09-02"
published: true 
partof: procedimento-crud-web
num: 7
outof: 7
---

##<a id="topo"> </a> Introdução

Com a implementação do formulário pronta, a próxima etapa é habilitá-lo na web. 
Para isso é necessário basicamente criar um arquivo .mustache onde será escrito o HTML necessário ao
seu funcionamento. 
Continuaremos com o exemplo do post anterior, <a href="http://dojo.objectos.com.br/procedimento/crud-forms/01-form-implementando-form.html">Implementando Form Create: Form</a>,
habilitando o formulário de aluno. 

##1. A tag &lt;form&gt;&lt;/form&gt; e seus atributos

Crie um arquivo chamado AlunoForm.mustache e insira as tags &lt;form&gt; com os atributos necessários.

<pre>
  <code>
    &lt;form action="{{bricks.baseUrl}}/api/crud/faculdade/:faculdadealuno"
        method="post" 
        data-way-form="bootstrap-horizontal"
        data-way-form-chain="{
          'dissolve' : 'form-aluno',
          'reRender' : 'aluno-list'
        }"&gt;;
  
    &lt;form&gt;
  </code>
</pre>

`action`– neste atributo deverá ser inserida a URL a qual o formulário deverá efetuar requisição. 
Note que é exatamente a URL definida no ModuloFaculdadeUI (post anterior), que representa a chave 
relacionada a classe de implementação do form, no caso a  FormDeAlunoCreate.class. 
Assim, o script HTML que estamos escrevendo estará relacionado a implementação do formulário.

```java
@Override
protected void bindApiCrud() {
  at("api/faculdade/:faculdade/crud/aluno").serve(FormDeAlunoCreate.class);
}
```

Não podemos esquecer também de utilizar a variável `\{\{bricks.baseUrl\}\}` antes da url que está no 
ModuloFaculdadeUi, em produção esta variável representa o domínio em que o sistema está hospedado, 
porém em fase de desenvolvimento representa o servidor local. 

`method` – este atributo identifica o tipo de envio do formulário, que normalmente será uma requisição
post.

`data-way-form` – O exemplo utiliza o `bootstrap-horizontal` um template de formulário do Bootstrap,
que não somente informa o estilo que o form terá, mas indica o tipo de form, sem esta informação o
formulário não funcionará corretamente.

`data-way-form-chain` - quando o servidor web responde que o formulário foi enviado com sucesso, o
`data-way-form-chain` executa as operações que lhe foram definidas, em cadeia.
O `dissolve` apontará para o div de chamada do formulário (no arquivo AlunoPanel.mustache),
isso fará com que o formulário feche após o seu envio.Em sequencia o `reRender` reinderiza a página para atualizar
a div de listagem, no nosso caso a listagem de alunos, para que o novo registro acrescentado apareça
na listagem logo após o novo aluno ser cadastrado.
Outras opções poderão ser configuradas como por exemplo, exibir uma confirmação de envio, abrir outra página
entre outros.
 
##2. Tag &lt;fielset&gt;&lt;/fieldset&gt;

Dentro das tags &lt;form&gt;&lt;/form&gt; insira a tag &lt;fielset&gt;&lt;/fieldset&gt;, que é
responsável pelo agrupamento dos inputs do formulário.

##3. Inserção de dados

Os campos do forma deverão ser coerentes ao tipo de dado que o formulário esta armazenando, 
usaremos basicamente inputs e combobox em alguns casos especificos.
Note que tanto o id, como o número de matricula e a data de criação do formulário são valores 
que devem ser gerados automaticamente, portanto não serão preenchidos no formulário.

###3.1 Haverão inputs hidden?

Antes de adicionarmos os campos que o usuário deve preencher devemos observar em que página o nosso
form será exibido, se trata-se de uma página de detalhes ou não. Este fator inclusive foi definido 
previamente ainda no TesteDeFormCreate, pois esta informação é necessária para definir a url do formulário.
O fato de um formulário ser exibido em uma página de detalhes indica que aquele formulário está relacionado 
à entidade desta página.

No exemplo, estamos matriculando um aluno em uma determinada faculdade (ou campus), estamos dentro da página
desta faculdade especifica, por exemplo, o aluno sendo matriculado na USP e a matricula é 
efetuada para o Campus de São Carlos. Na prática a requisição seria análogo a
`&lt;form action="www.usp.br/api/crud/faculdade/:2/aluno&gt;&lt;/form&gt;`, caso fosse uma requisição get.

Portanto se é necessário passar primeiro pela página do campus para depois realizar a matricula, toda matricula 
ali efetuada pertencerá ao Campus de São Carlos, o que significa que o usuário não irá preencher este dado
também, este dado será "capturado" pelo contexto através de um input hidden, como descrito abaixo:
 
    <input type="hidden" 
           name="faculdade"
           value="{{faculdade.id}}"/>
           
Sempre que um form estiver dentro de uma página de detalhes será necessário utilizar um input hidden
para preencher corretamente o atributo correspondente.   

Agora vamos supor que a universidade possui uma gestão centralizada, e tem permissão de acesso
a todos os campus, ou mesmo que uma matricula para um campus possa ser efetuada em qualquer outro campus.
Neste caso, no formulário de matricula o usuário deverá selecionar o campus ao qual o aluno será matriculado, 
logo ele não estará em uma página de detalhes e também não poderá ser utilizado um input hidden desta maneira,
o mais adequado seria utilizar uma combobox para selecionar o campus.

###3.2 Nome

Para inserção do nome do aluno utilizaremos um input.

     <input type="text" 
            name="nome"
            class="span2 tac"
            data-way-field="{
              'label' : 'Nome',
              'validators' : ['required']
            }" />  

`type` – determina o tipo do input, cada tipo determinará um modelo de campo compativel com o tipo de 
informação a ser armazenada. 'text' é o tipo de input utilizado para campos de texto. 

`name` – identificador da combobox. O name deve ser exatamente igual ao atributo correspondente da 
classe interna PojoJson do FormAlunoCreate, no exemplo nome.

`class` – configuração de estilo do input

`data-way-field` – possui os atributos label, uma descrição do campo, mask, para atribuir máscara 
(quando necessário) e validators para validar requisição.

###3.3 Data de nascimento

A data de nascimento também será inserida com um campo input, porém um campo data apesar de também 
possuir o type text, precisa seguir um padrão de formatação especifico, por isso utilizamos a máscara, 
`'mask' : 'fixed.date'`, para assegurar que usuário digite na formatação desejada.
Além da formatação temos que garantir que o usuário digite uma data válida e não um texto ou uma 
data fora do limite possivel para ser armazenada no banco de dados, por isso acrecentamos `'validate-date'`
ao atributo de validação.

     <input type="text" 
            name="dataDeNascimento"
            class="span2 tac"
            data-way-field="{
              'label' : 'Data de nascimento',
              'mask' : 'fixed.date',
              'validators' : ['required', 'validate-date']
            }" /> 

###3.4 Curso

Seu próximo campo a ser preenchido é o curso. Um aluno é matriculado em um curso preexistente,
sabemos quais são todos os cursos da faculdade possíveis ao aluno ser matriculado pois a tabela 
Curso do banco de dados já possui todos os cursos cadastrados, além disso devemos garantir a 
integridade dos dados para que não existam alunos matriculados em um curso que não exista.
Para atender a estas especificações usaremos uma combobox que irá nos fornecer todas as opções 
possíveis de curso cadastradas no banco de dados para que o usuário.

    <select name="curso"
            class="span2"
            data-way-field="{
              'label' : 'Curso'
            }">
      {{#cursos}}
      <option value="{{id}}">{{codigo}}</option>
      {{/cursos}}
    </select>

`{{#cursos}} {{/cursos}}` significa abertura de uma seção com contexto cursos, onde as variáveis 
serão correspondentes a seu contexto. É importante lembrar que “cursos” deve ter sido colocada 
no contexto das páginas onde estará o formulário. 
Option representa as opções, a seção permite um loop para listar todas as opções de curso presentes 
no contexto cursos. A variável id representará o id e codigo será o valor a ser exibido. 
Nos atributos name, class e `data-way-field` aplica-se as mesmas regras que dos inputs.

###3.5 Bolsa

Para respondermos se o aluno a ser cadastrado possui bolsa de estudo usaremos um input do tipo 
checkbox, pois checkbox armazena apenas um valor booleano a só existem duas opções ou o aluno é 
bolsista ou não é.

     <input type="checkbox" 
            name="bolsa"
            data-way-field="{
              'label' : 'Possui bolsa de estudo?'
            }" />
            
##4. Salvar e cancelar formulário

Finalizamos com uma div onde estarão os botões de Salvar e Cancelar, da seguinte maneira:

    <div class="form-actions">
      
      <input type="submit" 
             class="btn btn-primary" 
             value="Salvar" />
      
      <a href="#" 
         data-way-dissolve="aluno-form">Cancelar</a>
         
    </div>

Note que o botão Salvar é um input do tipo submit com um value, um valor definido “Salvar”, e que o 
botão Cancelar é um botão porém é um anchor e quando selecionado cancela o preenchimento do formulário.
O botão cancelar utiliza o atributo 'data-way-dissolve' para fechar o formulário cancelendo seu 
preenchimento.

Por fim o nosso form ficará assim:

<a href="http://jsfiddle.net/vcku4/">Visualizar formulário</a>
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
                    }" /&gt; 
                  
          &lt;input type="text" 
                    name="dataDeNascimento"
                    class="span2 tac"
                    data-way-field="{
                      'label' : 'Data de nascimento',
                      'mask' : 'fixed.date',
                      'validators' : ['required', 'validate-date']
                    }" /&gt; 
                              
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
