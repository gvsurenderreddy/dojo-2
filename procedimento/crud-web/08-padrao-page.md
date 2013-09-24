---
layout: post-alpha
title: "Padrão de construção dos arquivos mustaches"
author: "Carolene Bertoldi"
user: "CaroleneBertoldi"
date: "2013-09-16"
published: true
partof: procedimento-crud-web
outof: 8
num: 8
---

##Introdução 

Uma página web é composta de uma classe Java e seu respectivo Mustache. Neste post partiremos do princípio
de que a classe Java está pronta, e daremos ênfase apenas a construção dos mustaches necesários a 
uma página.

A `EntidadePage.mustache` é o arquivo mustache base para criação de uma página web. Poderíamos inserir todas 
as informações em um único arquivo mustache, porém este arquivo seria um arquivo com grande quantidade de 
código o que dificulta consulta e manutenção, por isso isolar em arquivos o conteúdo das `divs` é uma opção 
de melhores práticas.

![Estrutura de uma página](/img/estrutura_mustache.jpg "Estrutura de uma EntidadePage.mustache")

Cada arquivo, neste caso significa uma parte específica da página, por isso, quando é necessário criar
uma `EntidadePage.mustache` está implicíto que será necessário criar todos os mustaches que a compõe, ou seja
toda página web precisa de um `Action`, pelo menos um `Panel`, um `VCard` e uma `Table` para cada `Panel`, e se
houver um formulário para a entidade que a página exibirá, um `Form`.

`EntidadePage.java`- classe que define a url, o título da página e o(s) contexto(s) que será(ão)
utilizado(s) nos mustache para exibir seus dados.

`EntidadePage.mustache` - esta é a página base da entidade a ser exibida. Neste arquivo estão as chamadas
dos arquivos `EntidadeAction.mustache`, `EntidadeVCard.mustache` e `EntidadePanel.mustache`. 

`EntidadeAction.mustache` - é o arquivo em que estão os botões para os seus painel correspondentes.

`EntidadeVCard.mustache` - é o "cartão de visita" de um painel, onde está a descrição de conteúdo da página
e os filtros, se houverem. Cada painel precisa de um `VCard` correspondente.

`EntidadePanel.mustache` - no painel está a chamada da tabela de listagem da entidade e do formulaŕio,
se houver. Caso exista formulaŕio este deve ficar escondido (`hidden`) e só ser exibido atraves de um botão
chamado "Adicionar".

`EntidadeTable.mustache` - tabela para exibição dos dados de uma entidade.

`EntidadeForm` - formulário para inserção de registros do tipo da entidade listada.

<table class="table table-bordered">
  <tr>
    <td class="tac col2em">
      <a id="topo_0_0"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_0">EntidadePage.mustache</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_1"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_1">EntidadeAction.mustache</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_2"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_2">EntidadeVCard.mustache</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_3"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_3">EntidadePanel.mustache</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_4"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_4">EntidadeTable.mustache</a>
    </td>
  </tr>
  <tr>
    <td class="tac col2em">
      <a id="topo_0_5"><input type="checkbox" /></a>
    </td>
    <td>
      <a href="#0_5">EntidadeForm.mustache</a>
    </td>
  </tr>  
</table>
      
###<a id="0_0"> </a>EntidadePage.mustache

Iremos utilizar como exemplo uma página de listagem de livros de uma loja virtual.

A `EntidadePage.mustache` é o arquivo base que chamará os demais mustaches para que a página 
contenha todas as partes necessárias. A ordem de suas divs indica a ordenação em que os arquivos serão
exibidos.

####1. Nomeclatura

O padrão de nomeclatura se dá pelo Nome_da_entidade + sufixo "Page", se queremos listar dados da entidade
Livro, então o nome da nossa página será LivroPage.

####2. {% raw  %}{{&lt;base}}{% endraw %} {% raw  %}{{/base}}{% endraw %}

Todo o conteúdo deve estar dentro das tags  `\{\{\&lt;base\}\} \{\{/base\}\}`.

####3. {% raw  %}{{$body}}{% endraw %} {% raw  %}{{/body}}{% endraw %}

As tags  {% raw  %}`{{$body}}`{% endraw %} {% raw  %}`{{/body}}`{% endraw %}, início e fim do corpo da página. Assim como em html.

####4. Chamada do Action

Com esta `div` a `LivroPage.mustache` está invocando o arquivo `LivroAction.mustache`.
Os valores dos atributos `id` e `class` serão padrão as todas as páginas.

<pre>
  <code>
    &lt;div id="action-panel-top"
         class="action-panel action-panel-top"
         data-way-template="produtos/LivroAction.mustache"&gt;&lt;/div&gt;
  </code>
</pre>

####5. Chamada do VCard

Após o `Action` a próxima `div` carregará o conteúdo do arquivo `LivroVCard.mustache`, note que estamos
escrevendo as chamadas, atribuindo os nomes segundo a nomeclatura estabelecida. Estes arquivos serão criados 
posteriormente e deverão ter exatamente os nomes aqui estabelecidos.

O `id` atribuído também segue um padrão de nomeclatura nome_da_ entidade + "-" + tipo_do_arquivo_mustache, com
todas as palavras em letra minúscula.
Deverá existir um `VCard` para cada `Panel` inserido na página.

<pre>
  <code>
    &lt;div id="livro-vcard"
         data-way-template="produtos/LivroVCard.mustache"&gt;&lt;/div&gt;
  </code>
</pre>

####6. Chamada do Panel

A `div` de chamada do `Panel` é inserida logo abaixo da `div` de chamada do `VCard`, com apenas uma quebra de linha,
sem linhas em branco entre uma `div` e outra, para indicar que o `VCard` está relaionado àquele `Painel` abaixo.
Quando há mais de um `Panel` em uma página, manter este padrão torna o código muito mais legível.

<pre>
  <code>
    &lt;div id="livro-panel"
         class="panel-mega"
         data-way-template="produtos/LivroPanel.mustache"&gt;&lt;/div&gt;
  </code>
</pre>

O id atribuido também segue o mesmo padrão de nomeclatura nome_da_entidade + "-" + tipo do mustache.

####7. Código de LivroPage.mustache completo

O código completo de nossa LivroPage.mustache ficaria assim: 

<pre>
  <code>
    {% raw  %}{{&lt;base}}{% endraw %}
    {% raw  %}{{$body}}{% endraw %}
    &lt;div id="action-panel-top"
         class="action-panel action-panel-top"
         data-way-template="produtos/LivroAction.mustache"&gt;&lt;/div&gt;
  
    &lt;div id="livro-vcard"
         data-way-template="produtos/LivroVCard.mustache"&gt;&lt;/div&gt;
    &lt;div id="livro-panel"
         class="panel-mega"
         data-way-template="produtos/LivroPanel.mustache"&gt;&lt;/div&gt;
    {% raw  %}{{/body}}{% endraw %}
  {% raw  %}{{/base}}{% endraw %}
  </code>
</pre>

Observe que de uma página para outra, o que mudará será somente o pacote (se houverem módulos distintos)
e o nome do arquivo, ou seja, uma forma bastante simples de se fazer uma EntidadePage.mustache mais
rapidamente é copiando o arquivo, atribuindo o nome da entidade que a página listará e com `Ctrl + F`, 
substituir o nome da entidade do arquivo que forá copiado para a entidade que deseja.

Exemplo, agora precisaremos de uma página de listagem de CD's da loja. Se já temos a página de listagem de
livros apenas copiamos o arquivo e colamos, com ambas no mesmo pacote será aberta uma caixa de dialógo para
que o nome da copia seja alterado e `Copy of LivroPage.mustache` será renomeada para `CDPage.mustache`.
Se estiverem em pacotes diferentes renomeie `LivroPage.musche` do pacote onde deverá estar `CDPage.mustache`.

Dentro do arquivo o código será idêntico ao código de `LivroPage` (pois `CDPage.mustache` uma cópia), com `Ctrl + F` 
substitua "Livro", por "CD" `Alt + A` para substituir todos e `Esc` para sair, o mesmo com "livro" substituido
por "cd",

Pronto temos a `CDPage.mustache` pronta. Se estiverem em pacotes diferente mude também o caminho dos arquivos.
Assim teremos,

Veja o código de <a href="https://github.com/objectos/objectos-dojo/blob/723272eadaf7407884a4ffbc9b0579098fc16c94/objectos-dojo-team/src/main/java/br/com/objectos/dojo/cbertoldi/CDPage.mustache">CDPage.mustache</a> completo.

###<a id="0_1"> </a>EntidadeAction.mustache

O `Action` é o arquivo em estão localizados os botões e os links de navegação da página. Toda 
`EntidadePage` necessota de um `EntidadeAction`.

Os botões são ancoras para os painéis da página, é muito útil principalmente quando há mais de um painel,
atraves dos botões o usuário navega entre os painéis de forma bastante intuitiva, sem precisar utilizar
barra de rolagem para procurar informações na página.

Sabemos que `LivroPage.mustache` possui uma `div` com a chamada de um arquivo chamado `LivroAction.mustache`.
Este arquivo ainda não existe, precisará ser cirado, vocẽ pode utilizar `Ctrl + N` para criar um novo arquivo, 
selecionar o pacote em que deverá ficar, um pacote com nome parecido com "...view.produtos", logo abaixo do pacote
"...page.produtos", também pode apenas copiar um arquivo Action e remomeá-lo para `LivroAction.mustache`
e substituir os dados de acordo com a entidade Livro.

####1. Base

Para começar `LivroAction.mustache` devemos abrir uma `div` com `class="wrapper"`. Esta `div` é padrão
a todas as páginas.

<pre>
  <code>
    &lt;div class="wrapper"&gt;
    
    &lt;/div&gt;
  </code>
</pre>

####2. Links de navegação

Para facilitar navegação do usuário entre as páginas colocamos links que descrvem o "caminho" percorrido 
pelo usuário a página, isso facilita seu retorno.

Exemplo, para chegar a página de listagem de livros o usuário acessa a página principal da loja, a Index, clica
no link produtos, ou seja navega por `ProdutoPage`  da listagem dos tipos de produtos ele clica em Livros,
redirecionando para a página `LivroPage`.

`Loja -> Produtos -> Livros`, com seus respectivos links de volta.

<pre>
  <code>
    &lt;ul class="crumbs"&gt;
      &lt;li&gt;
        &lt;a href="{% raw  %}{{bricks.baseUrl}}{% endraw %}"&gt;Loja&lt;/a&gt;
      &lt;/li&gt;
      &lt;li class="divider"&gt;&#8226;&lt;/li&gt;
      &lt;li&gt;
        &lt;a href="{% raw  %}{{bricks.baseUrl}}{% endraw %}/produtos"&gt;Produtos&lt;/a&gt;
      &lt;/li&gt;
      &lt;li class="divider"&gt;&#8226;&lt;/li&gt;
      &lt;li&gt;
        &lt;a href="{% raw  %}{{bricks.baseUrl}}{% endraw %}/produtos/livro"&gt;Livros&lt;/a&gt;
      &lt;/li&gt;
    &lt;/ul&gt;
  </code>
</pre>

Onde \{\{bricks.baseUrl\}\} representa o dominio do sistema.

####3. Botões

Como dito anteriormente, os botões também são âncoras, e o seu `href` aponta para o `id` da `div` em que 
queremos estabelecer link, o que permite o efeito de rolagem da página para até a `div` epecifica que
desejamos, quando o botão é clicado. 

Cada painel deverá possuir um botão para o referenciar. Porém o `id` inserido é do `VCard` respectivo ao `Panel`,
sabemos que todos painel deve possuir um `VCard`, o cartão de visita do painel, então quando o botão é
clicado, a página é redirecionada para a duv VCard especificada, da própria página e o `Panel`, logo
abaixo, dando sensação de o `Panel` ter sido involcado também.

<pre>
  <code>
  &lt;ul class="actions"&gt;
    &lt;li&gt;
      &lt;a href="#hd" 
         class="btn action"&gt;Topo&lt;/a&gt;
    &lt;/li&gt;
    &lt;li&gt;
      &lt;a href="#livro-vcard" 
         class="btn action"&gt;Livros&lt;/a&gt;
    &lt;/li&gt;
  &lt;/ul&gt;
  </code>
</pre>

Veja código completo de <a href="https://github.com/objectos/objectos-dojo/blob/723272eadaf7407884a4ffbc9b0579098fc16c94/objectos-dojo-team/src/main/java/br/com/objectos/dojo/cbertoldi/LivroAction.mustache">LivroAction.mustache</a>

###<a id="0_2"> </a>EntidadeVCard.mustache

No cartão de visita de cada painel estão as informações da entidade que será listada e os seus 
respectivos filtros caso existam.

####1. Código padrão

Esta parte do código do `VCard` é padrão, trata-se de definições `CSS` comuns a `VCards`.

<pre>
  <code>
      &lt;div class="vcard mt20px"&gt;
       &lt;div class="row"&gt;
         &lt;div class="span2 tac"&gt;
           &lt;ul class="thumbnails"&gt;
             &lt;li class="span2"&gt;
               &lt;div class="thumbnail"&gt;
                 &lt;img src="{% raw  %}{{bricks.imgUrl}}{% endraw %}/iamgens/livro-vcard-01.png" /&gt;
               &lt;/div&gt;
             &lt;/li&gt;
           &lt;/ul&gt;
         &lt;/div&gt;
  </code>
</pre>

Observe que existe uma chamada de uma imagem, no fragmento &lt;`img src="{% raw  %}{{bricks.imgUrl}}
{% endraw %}/iamgens/livro-vcard-01.png" /`&gt;, esta imagem irá ser exibida no `VCard` da página.
É importante lembrar que imagens possuem `Copyrigth`, portanto não podemos postar uma imagem a menos 
que a tenhamos comprado ou tenhamos certeza que os direitos de uso sobre a imagem são gratuitos, 
inclusive para uso comercial, pois muitas imagens possuem uso gratuito apenas em projetos sem fins 
lucrativos. 

####2. Descrição da página

Nesta `div` está a descrição da página.

<pre>
  <code>
       &lt;div class="span3 sidebar"&gt;
         &lt;h2&gt;Livros &lt;/h2&gt;
  
         &lt;p&gt;Consulta e manutenção dos livros cadastrados no sistema. &lt;/p&gt;
       &lt;/div&gt;
  </code>
</pre>

####3. Filtros

Imagine que tenhamos muitos livros a venda nesta loja. Deixariamos o usúarios percorrer a lista de
todos os livros para localizar o livro que deseja comprar? Certamente se fizessemos isso ninguém
compraria livros em nossa loja virtual. Portanto, colocaremos um campo para o usuário digitar o nome
do livro que deseja e assim filtrar os resultados de livros em que o título possui as palavras digitadas
pelo usuário.

<pre>
  <code>
    &lt;div class="offset1 span6 form-row"&gt;
    
           &lt;form method="get"
            data-way-form-search="bootstrap-inline-block"
            data-way-form-search-options="{
              'bindTo' : 'livro-list',
              'bindType' : 'id',
              'reRender' : ['livro-list']
            }"&gt;
            
             &lt;div class="row"&gt;
               &lt;div class="span6"&gt;
                 &lt;input type="text"
                       name="titulo"
                       data-way-field="{
                        'label' : 'Título'
                       }" /&gt;
               &lt;/div&gt;
             &lt;/div&gt;      
    
    &lt;/form&gt;
  </code>
</pre>

Assim, os filtros são utilizados para exibir resultados a partir do valor de um campo especifico.
Por se tratar de um `input` deve esta inserido dentro das tags de um formulário.

Observe alguns pontos deste formulário de pesquisa:

`data-way-form-search`- diz respeito ao tipo de formulário, incluindo também informações sobre o
seu estilo.

`data-way-form-search-options` - possui atributos em que configuramos onde estão os dados a serem filtrados
e ode serão exibidos os dados filtrados.

`livro-list` - id atribuido a div que chamará a tabela de exibição dos dados.

`'bindTo' : 'livro-list'` - informa a div que deverá ser executada com o preenchimento do form search.

`'reRender' : ['livro-list']` - informa em qual div os dados filtrados serão exibidos.

Dentro do formulário há um input, onde de fato o usuário irá digitar sua pesquisa.

`name` - identificação do `input`.

Neste input é importante observar que assim como nos demais formulários o código Java deverá possuir
um atributo correspondente, para que a implementação funcione. No caso do form search não um atributo 
e sim uma variável local no método `pagePorTodos()` do buscador da entidade que está sendo listada na
página, que será o parâmetro passado a consulta sql responsável pelo filtro. 
Então sempre que é necessário habilitar um filtro na web, deve se consultar a classe buscador da entidade,
onde estará a implementação do filtro, e o nome correto a ser atribuido ao `input` de pesquisa.

No nosso exemplo o nosso `name` atribuÍdo foi `titulo` pois o filtro está implementado em `BuscarLivroGuice`,
com o parametro recebido definido como `titulo`.

      private NativeSql newPager(String what, RequestWrapper wrapper) {
        String titulo = wrapper.param("titulo");
    
        return newSelect(what)
            .addIf("where LIVRO.TITULO like concat('%',?,'%')").paramNotNull(titulo)
    
            .add("order by")
            .add("LIVRO.TITULO asc");
      }
      
Ver mais sobre implemtação de filtros em. 
      
Uma página poderá possuir vários filtros, e todos "conviverem" em harmonia, por exemplo, possa que o usuário 
não lembre o título do livro mas saiba o autor do livro, uma buscar por autor restornaria todos os livro 
daquele autor e assim ele poderia encontrar o livro que deseja. Assim sendo, existem duas opções de filtros.

 <pre>
  <code>
    &lt;div class="offset1 span6 form-row"&gt;
    
           &lt;form method="get"
            data-way-form-search="bootstrap-inline-block"
            data-way-form-search-options="{
              'bindTo' : 'livro-list',
              'bindType' : 'id',
              'reRender' : ['livro-list']
            }"&gt;
            
             &lt;div class="row"&gt;
               &lt;div class="span6"&gt;
                 &lt;input type="text"
                       name="titulo"
                       data-way-field="{
                        'label' : 'Título'
                       }" /&gt;
               &lt;/div&gt;
             &lt;/div&gt;      
    
            &lt;div class="row"&gt;
             &lt;div class="span6"&gt;
               &lt;input type="text"
                     name="autor"
                     data-way-field="{
                      'label' : 'Autor'
                     }" /&gt;
              &lt;/div&gt;
            &lt;/div&gt;
             
    &lt;/form&gt;
  </code>
</pre>   
    
Note que também deverá existir uma implementação de filtro correspondente para autor.

      private NativeSql newPager(String what, RequestWrapper wrapper) {
        String titulo = wrapper.param("titulo");
        String autor = wrapper.param("autor");
    
        return newSelect(what)
            .addIf("where LIVRO.TITULO like concat('%',?,'%')").paramNotNull(titulo)
            .addIf("and LIVRO.AUTOR like concat('%',?,'%')").paramNotNull(autor)
    
            .add("order by")
            .add("LIVRO.TITULO asc");
      }

Veja o código <a href="https://github.com/objectos/objectos-dojo/blob/723272eadaf7407884a4ffbc9b0579098fc16c94/objectos-dojo-team/src/main/java/br/com/objectos/dojo/cbertoldi/LivroVCard.mustache">LivroVCard.mustache</a> completo.

###<a id="0_3"> </a>EntidadePanel.mustache

Cada página deverá possuir um ou mais arquivos do tipo `Panel`, a depender de quantas listagens deverá exibir.
Cada listagem devrá ser exibida em um Panel próprio.

Na verdade, um `Panel` é um painel que exibirá o conteúdo da página, este(s) conteúdo(s) estão em arquivos
a parte que serão chamados no `Panel`.

Mas, por que utilizar um `Panel` se ele apenas carrega outro arquivo onde realmente o conteúdo está?

<pre>
  <code>
    &lt;div class="panel"&gt;
      &lt;div class="panel-hd"&gt;
        &lt;h3&gt;&nbsp;&lt;/h3&gt;
      &lt;/div&gt;
      
     &lt;div id="livro-list" 
           data-way-async="produto/LivroTable.mustache"
           data-way-async-options="{'url':'{% raw  %}{{bricks.baseUrl}}{% endraw %}/produto/livro'}"&gt;&lt;/div&gt;
     &lt;/div&gt;  
  </code>
</pre>

O exemplo acima é a forma mais simples de um arquivo `Panel`, neste `Panel` exibirá apenas uma listagem,
carregada através de um arquivo `Table`, porém se houver um `Form` este também será chamdo através do `Panel`
o que dá ao usuário a sensação de o formulário estar naquele painel especifico.

Observe também que sempre será necessário haver um `Panel`, mesmo que apenas contenha a chamada de uma listagem,
nunca haverá uma `Table` chamada diretamente em uma `Page`, pois matem um padrão de organização e facilita
manutenções, ou adição de conteúdos, pois o fato de o formulário não existir hoje, não quer dizer que não existirá amamhã,
e já havendo um `Panel` existe um padrão, uma premissa "Um `Form` ou `Table` sempre será invocado em um `Panel`", então
não haverá dúvida sobre onde colocar o `Form` ou uma `Table`.

Fica a dica: Padronização é uma forma eficiênte de evitar erros e manter a organização.

Existem duas formas de se invocar o arquivo de listagem, atráves do atributo `data-way-async`, ou do
atributo `data-way-template`.

####1. Data-way-async X Data-way-template

Um `data-way-async` é utilizado quando a listagem é carregada a partir de um `Servico` e o `data-way-tempate`
é utilizado quando a listagem é feita a partir de um cache.

Se a listagem será de uma entidade que possui muitos registros em seu banco de dados ou que possui muitas
alterações (inserções, exclusões ou edições) o mais indicado é utilizar uma listagem a partir de um `Servico` pois
o servico carrega os dados diretamente do buscador. Porém se a entidade possui um cache, são poucos os 
dados a serem listados e não há alterações com frequência então utilizaremos uma lisatgem a partir de um cache.

#####1.1 Declarando um data-way-async

<pre>
  <code>
    &lt;div class="panel"&gt;
      &lt;div class="panel-hd"&gt;
        &lt;h3&gt;&nbsp;&lt;/h3&gt;
      &lt;/div&gt;
      
     &lt;div id="livro-list" 
           data-way-async="produto/LivroTable.mustache"
           data-way-async-options="{'url':'{% raw  %}{{bricks.baseUrl}}{% endraw %}/produto/livro'}"&gt;&lt;/div&gt;
     &lt;/div&gt;  
  </code>
</pre>

No exemplo acima, nossa listagem esta sendo carregada através de um `Servico`, o que quer dizer que existe uma
classe `ServicoDeLivro.java` que passa o contexto (contexto será explicado na seção `EntidadeTable.mustache`) 
ao arquivo `Table`, por isso utilizaremos `data-way-async` para realizar a chamada do arquivo `LivroTable`,
para "dizermos" ao arquivo `Table` o contexto que este deverá ser exibido.

`data-way-async` - atributo utilizado realizar um chamada assíncrona do arquivo de listagem. Uma chamada
assíncrona (de apenas um lado para outro), quer dizer que o mustache irá exibir os dados a partir de um
contexto que será passado a ele, e não utilizará o contexto da EntidadePage.mustache, terá sua própria
classe Java, o `Serviço` que definirá o contexto que a `Table` seguirá.

`data-way-async-options` - declara a url relacionada ao `Servico` que passará o contexto a `Table`.
Qunado a classe `ServicoDeLivro.java` foi criada, uma url foi definida no `Modulo`, esta será a url
informada ao `data-way-async-options`.

Na classe ModuloProduto.java:

    @Override
    protected void bindProduto() {
      at("/produto/livro")
          .serve(ServicoDeLivro.class);
    }

Lembrando que {% raw  %}{{bricks.baseUrl}}{% endraw %} indica o dominio do sistema.

#####1.2 Declarando um data-way-template

Adaptando nosso exemplo ao da listagem ser carregada a partir de um cache.

<pre>
  <code>
    &lt;div class="panel"&gt;
      &lt;div class="panel-hd"&gt;
        &lt;h3&gt;&nbsp;&lt;/h3&gt;
      &lt;/div&gt;
      
     &lt;div id="livro-list" 
           data-way-template="produto/LivroTable.mustache">
     &lt;/div&gt;  
  </code>
</pre>

`data-way-template` - invoca um arquivo, transmitindo a este o contexto declarado em `EntidadePage.java`

Ao utilizar `data-way-template` é necessário conferir se a classe Java EntidadePage.java, declarou
o contexto que a `Table` utilizará para exibir os dados. 

Neste caso de uma listagem carragada através do cache a LivroPage.java deverá esta escrita assim:

    public class LivroPage extends AbstractProdutoPage {
    
      private final CacheDeLivro cacheDeLivro;
    
      @Inject
      public LivroPage(PrismaWay way,
                       CacheDeLivro cacheDeLivro) {
        super(way);
        this.cacheDeLivro = cacheDeLivro;
      }
    
      @Override
      @Get
      public Reply<?> get() {
    	return super.get();
 	  }

  	  @Override
 	  @Post
  	  public Reply<?> post() {
        return super.post();
      }
      
      @Override
      Context decorate(Context c) {
        List<Livro> livros = cacheDeLivro.porTodos();
        c.put("livros", PageList.of(livros));
    
        return super.decorate(c);
      }
      
      @Override
      String getTitulo() {
        return "Livros";
      }
    
      @Override
      String getUrl() {
        return "livro";
      }
    
    }

Observe que quando a listagem foi carregada através de `ServicoDeLivro`, `LivroPage` não possuia o
método decorate, nem declaração da variavel de instância `cacheDeLivro`.

    public class LivroPage extends AbstractProdutoPage {
    
      @Inject
      public LivroPage(PrismaWay way) {
        super(way);
      }
    
      @Override
      @Get
      public Reply<?> get() {
      return super.get();
     }

      @Override
      @Post
      public Reply<?> post() {
        return super.post();
      }
            
      @Override
      String getTitulo() {
        return "Livros";
      }
    
      @Override
      String getUrl() {
        return "livro";
      }
    
    }

###<a id="0_4"> </a>EntidadeTable.mustache

O arquivo `Table` é o arquivo que possui a estrutura da tabela que será exibida na listagem.
Cada listagem deverá possuir sua própria tabela, pois cada entidade possui atributos distintos e estes
atributos serealizados devem ser declarados em sua EntidadeTable.

Agora, iremos fazer nosso arquivo LivroTable.mustache.

Para exibirmos os registros da entidade Livro precisamos certificar-nos de que esta entidade está `serealizada`,
ou seja que possui uma classe `LivroSerealizar.java` que serializa os atributos da entidade Livro.

Abra a interface Livro e observe-a, ela deve possuir a anotação `@JsonSerialize(using = LivroSerializer.class)`.

Com `Ctrl + clic` abra a classe `LivroSerealizar`:

    class LivroSerializer extends JsonSerializer<Livro> {
    
      @Override
      public void serialize(Livro value, JsonGenerator jgen, SerializerProvider provider)
          throws IOException, JsonProcessingException {
    
        jgen.writeStartObject();
    
        jgen.writeObjectField("id", value.getId());
        jgen.writeStringField("titulo", value.getTitulo());
        jgen.writeObjectField("autor", value.getAutor());
        jgen.writeObjectField("genero", value.getGenero());
        jgen.writeNumberField("preco", value.getPreco);
    
        jgen.writeEndObject();
    
      }
    
    }

Ok, se nossa classe LivroSerealizer existe, então já sabemos como declarar os campos da tabela corretamente,
então podemos começar a escrever nosso arquivo `Table`.

####1. Declarando seção mustache

A primeira coisa a ser declarada em uma tabela é a chave da listagem. Este chave foi declarada, atribuindo
a ela a lista de objetos a serem listados. Uma chave pode ser declarada em um `Servico`,

    Context c = Context.of();
    c.put("livros", list);

ou na `Page`, para o caso de listagem a partir de um cache,

      @Override
      Context decorate(Context c) {
        List<Livro> livros = cacheDeLivro.porTodos();
        c.put("livros", PageList.of(livros));
    
        return super.decorate(c);
      }

Observe que para a tabela isso não importa, o que realmente importa para ela é que um contexto lhe seja
passado, no caso a chave "livros".

<pre>
  <code>
    {% raw  %}{{#livros}}{% endraw %}
    
    {% raw  %}{{/livros}}{% endraw %}
  </code>
</pre>

Foi declarada então uma seção mustache. Uma seção mustache funciona de forma similar
a um forech, exibindo um bloco de texto uma ou mais vezes a depender do valor de sua chave.

No exemplo, nossa chave é uma lista chamada "livros", o que significa bloco de texto interno da seção 
será exibido segundo a quantidade de itens que temos na lista.

####2. Caso a lista esteja vazia

<pre>
  <code>
    {% raw  %}{{#livros}}{% endraw %}
      {% raw  %}{{#empty}}{% endraw %}
      &lt;p&gt;Nenhum livro encontrado.&lt;/p&gt;
      {% raw  %}{{/empty}}{% endraw %}
    {% raw  %}{{#livros}}{% endraw %}  
  </code>
</pre>

`empty` - chave mustache executada quando a chave da seção principal está vazia. 

No nosso caso quando não houverem livros registrados no banco de dados, ou seja, quando não houverem 
objetos do tipo Livro na lista "livros", é exibida a frase "Nenhum livro encontrado".

####3.  Estruturando tabela

A tabela será construída dentro da seção \{\{^empty\}\}\{\{/empty\}\}

`^` - sinal de negação. O bloco será executado quando a seção não está vazia.

Quando há algum registro a tabela é exibida.

<pre>
  <code>
    {% raw  %}{{^empty}}{% endraw %}
      &lt;table class="table table-condensed"&gt;
      
      &lt;/table&gt;
    {% raw  %}{{/empty}}{% endraw %}
  </code>
</pre>    

#####3.1 Páginação

O parcial \{\{&gt; pager.mustache\}\}, carrega o arquivo `pager.mustache` responsável pela páginação.

<pre>
  <code>
    {% raw  %}{{^empty}}{% endraw %}
      &lt;table class="table table-condensed"&gt;
        {% raw  %}{{> pager.mustache}}{% endraw %}      
      &lt;/table&gt;
    {% raw  %}{{/empty}}{% endraw %}
  </code>
</pre>    

#####3.2 Título da tabela

Deve ser declarado o título de cada campo que será exibido.

Em uma das colunas não deve ser declarado tamanho, isso fará a coluna ocupe o espaço que sobra para 
completar o tamanho total da largura da `div`.

<pre>
  <code>
      &lt;thead&gt;
        &lt;tr&gt;
          &lt;th class="col5em"&gt;Título&lt;/th&gt;
          &lt;th class="col15em"&gt;Autor&lt;/th&gt;
          &lt;th class="col12em"&gt;Gênero&lt;/th&gt;
          &lt;th&gt;Preço&lt;/th&gt;
        &lt;/tr&gt;
      &lt;/thead&gt;
  </code>
</pre>

#####3.3 Definindo colunas

Nestas colunas estão as variáveis mustache, no lugar delas será exibido o valor dos respectivos atributos 
de cada objeto.

<pre>
  <code>
     &lt;tbody&gt;
      {% raw  %}{{#rows}}{% endraw %}
     &lt;tr&gt;
       &lt;td class="col5em"&gt;{% raw  %}{{titulo}}{% endraw %}&lt;/td&gt;
       &lt;td class="col12em"&gt;{% raw  %}{{autor.nome}}{% endraw %}&lt;/td&gt;
       &lt;td class="col12em"&gt;{% raw  %}{{genero.descricao}}{% endraw %}&lt;/td&gt;
       &lt;td&gt;{% raw  %}{{preco}}{% endraw %}&lt;/td&gt;
     &lt;/tr&gt;
      {% raw  %}{{/rows}}{% endraw %}
    &lt;/tbody&gt;
  </code>
</pre>

As colunas estão declaradas dentro da seção `rows`, o que indica que o bloco será exibido segundo a 
quantidade de linhas que o PageList possui (observe que o valor ligado a chave tanto no `Servico`
quanto na `Page` quando a lista é carregada a partir de um cache, em ambos os casos é um objeto PageList),
sendo `rows` um atributo de PageList, do tipo lista do objeto listado, no caso List&lt;Livro&gt;.

É importante observar que o nome da variável mustache que representa cada atributo do objeto, foi definida
na classe `EntidadeSerealiza`, no exemplo `LivroSerealize`.

Note também as variáveis \{\{autor.nome\}\} e \{\{genero.descricao\}\} ambas possuem um "." e uma palavra
além da variavel. Este "." em mustache significa "selecionar um item da lista", reparece antes que autor,
e genero, são objetos:

        jgen.writeObjectField("autor", value.getAutor());
        jgen.writeObjectField("genero", value.getGenero());

E estes objetos também possuem suas respectivas classes de serealização, `AutorSerealizer` e `GeneroSerealizer`,
que possuem definidas as seguintes declaraçõez:

AutorSerealizer

        jgen.writeObjectField("nome", value.getNome());
        
        
GeneroSerealizer        
        
        jgen.writeObjectField("descricao", value.getDescricao());
        
Com isso, podemos deduzir que as variáveis, \{\{autor.nome\}\} e \{\{genero.descricao\}\} irão exibir na 
tabela na coluna de autor, o nome do autor e na coluna de genero, a descrição do gênero. Ou seja, será
exibido um item específico de cada objeto, seguindo o padrão \{\{objeto.item\}\}.  

Vaja o arquivo <a href="https://github.com/objectos/objectos-dojo/blob/723272eadaf7407884a4ffbc9b0579098fc16c94/objectos-dojo-team/src/main/java/br/com/objectos/dojo/cbertoldi/LivroTable.mustache">LivroTable.mustache</a> completo.