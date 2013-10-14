---
layout: post-alpha
title: "Erros Markdown e Jekyll"
author: "Anderson Amorim Silva"
user: "asilva26"
date: 2013-10-07
published: true 
partof: faq-crud-entidade
num: 10
outof: 10
---

##Procedimento para resolução de erros Markdown e Jekyll

Ao escrever artigos no formato Markdown comumente nos deparamos com erros de sintaxe, e que nem sempre são tão óbvios de serem resolvidos. Este artigo tem por finalidade sanar dúvidas de como corrigir estes erros fáceis de serem deparados, principalmente quando não se tem muito conhecimento sobre a sintaxe **markdown**.

Para entender melhor este artigo é necessário conhecimento em:  
[Markdown](http://daringfireball.net/projects/markdown/)  
[Jekyll](http://jekyllrb.com/)

Siga o checklist abaixo:
<table class="table table-bordered">
 <tr>   
  <td class="tac col2em">
    <a id="topo_0_0"><input type="checkbox" /></a>
   </td>
  <td>
    Erros comuns de sintaxe markdown
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
    Erros de sintaxe html
   </td>
  <td>
    <a href="#0_1">help!</a>
   </td>
  </tr>
 <tr>
  <td>
    <a id="topo_0_2"><input type="checkbox" /></a>
   </td>
  <td>
    Erro ao escrever artigos com tags mustache no Jekyll
   </td>
  <td>
    <a href="#0_2">help!</a>
   </td>
  </tr>
 <tr>
  <td>
    <a id="topo_0_3"><input type="checkbox" /></a>
   </td>
  <td>
    Erro ao inserir códigos
   </td>
  <td>
    <a href="#0_3">help!</a>
   </td>
  </tr>
</table>

###<a id="0_0"> </a> Erros comuns de sintaxe markdown

Geralmente quando se escreve um comando, nome de classe, é utilizado o *acento grave* (\`\), e um erro muito comum é esquecer de fechá-lo, por exemplo, `public void`.  
Outro erro bastante comum de acontecer é escrever uma palavra ou frase em **negrito** ou *itálico* e esquecer de fechar o **underscore** ou o **asterísco**, dependendo do padrão utilizado.

###<a id="0_1"> </a> Erros de sintaxe html

Como o markdown é uma ferramenta de conversão de texto para *HTML*, acaba sendo muito comum utilizar tags para criação ou edição de um texto, e aí que está o problema. Ferramentas de desenvolvimento facilitam nossa vida no sentido de não precisarmos nos preocupar tanto com sintaxe pois elas acabam completando comandos digitados, e por conta disso acabamos adquirindo o vício de abrir e esquecer de fechar as tags.  
Para criação de artigo é utilizado geralmente um editor de texto (no caso o Gedit), que não oferece estas comodidades, e comumente é esquecido de fechar. Por exemplo ao criar uma tabela onde são inseridos os títulos dos artigos, por padrão é colocada uma tabela com o checklist dos tópicos abordados, e nessa de focar em escrever o artigo, deixa-se de atentar em fechar as tags utilizadas, gerando uma mensagem de erro parecida com esta:  

     ___________________________________________________________________________
    | Maruku tells you:
    +---------------------------------------------------------------------------
    | Bad html: 
    | >#<MaRuKu::Exception: Error: Malformed: tag <table> closes <tr> 
    | >HTML READER
    | > state=inside_element match="</table>"
    | >Tag stack = ["table", "tr"] 
    | >Before:
    | >|<table class="table table-bordered">
    | >| <tr>   
    | >|  <td class="tac col2em">
    | >|    <a id="topo_0_0"><input type="checkbox" /></a>
    | >|   </td>
    | >|  <td>
    | >|    Erros comuns de sintaxe markdown
    | >|   </td>
    | >|  <td>
    | >|    <a href="#0_0">help!</a>
    | >|   </td>
    | >|  </tr>
    | >| <tr>
    | >|  <td class="tac col2em">
    | >|    <a id="topo_0_1"><input type="checkbox" /></a>
    | >|   </td>
    | >|  <td>
    | >|    Erros de sintaxe html
    | >|   </td>
    | >|  <td>
    | >|    <a href="#0_1">help!</a>
    | >|   </td>
    | >|</table>
    | >After:
    | >
    | >>/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/html_helper.rb:151:in `handle_tag'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/html_helper.rb:79:in `eat_this'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_span_better.rb:480:in `read_inline_html'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_span_better.rb:122:in `read_span'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_span_better.rb:46:in `parse_span_better'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_span_better.rb:36:in `parse_lines_as_span'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_block.rb:275:in `read_paragraph'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_block.rb:158:in `read_text_material'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_block.rb:69:in `parse_blocks'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_block.rb:41:in `parse_text_as_markdown'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/input/parse_doc.rb:57:in `parse_doc'
    | >/usr/lib64/ruby/gems/1.8/gems/maruku-0.6.0/lib/maruku/maruku.rb:30:in `initialize'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/converters/markdown/maruku_parser.rb:44:in `new'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/converters/markdown/maruku_parser.rb:44:in `convert'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/converters/markdown.rb:39:in `convert'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/convertible.rb:50:in `transform'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/convertible.rb:142:in `do_layout'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/page.rb:115:in `render'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/site.rb:210:in `render'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/site.rb:208:in `each'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/site.rb:208:in `render'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/site.rb:36:in `process'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/command.rb:18:in `process_site'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/commands/build.rb:23:in `build'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/../lib/jekyll/commands/build.rb:7:in `process'
    | >/home/asilva/.gem/ruby/1.8/gems/jekyll-1.2.0/bin/jekyll:97
    | >/home/asilva/.gem/ruby/1.8/gems/commander-4.1.5/lib/commander/command.rb:180:in `call'
    | >/home/asilva/.gem/ruby/1.8/gems/commander-4.1.5/lib/commander/command.rb:180:in `call'
    | >/home/asilva/.gem/ruby/1.8/gems/commander-4.1.5/lib/commander/command.rb:155:in `run'
    | >/home/asilva/.gem/ruby/1.8/gems/commander-4.1.5/lib/commander/runner.rb:402:in `run_active_command'
    | >/home/asilva/.gem/ruby/1.8/gems/commander-4.1.5/lib/commander/runner.rb:66:in `run!'
    | >/home/asilva/.gem/ruby/1.8/gems/commander-4.1.5/lib/commander/delegates.rb:7:in `run!'
    | >/home/asilva/.gem/ruby/1.8/gems/commander-4.1.5/lib/commander/import.rb:10
    | >/usr/bin/jekyll:19

Este erro foi causado pela ausência de uma tag `</tr>`.
Um outro problema encontrado é em relação a exibição de scripts html, como o html é reconhecido automaticamente pelo markdown é necessário realizar algumas modificações no script para que ele rode. Uma exemplificação é a substituição de `<` por `&lt`, e `>` por ` &gt`.  
Esta substituição que ao primeiro olhar possa parecer insignificante, evita muita dor de cabeça na criação de artigos para o padrão *Markdown*.

###<a id="0_2"> </a> Erro ao escrever artigos com tags mustache no *Jekyll*

Um outro tipo de erro acontece ao rodar o servidor *Jekyll*, que é responsável por compilar o artigo _.md_. Por exemplo, ao inserir um trecho de código [Mustache](http://en.wikipedia.org/wiki/Mustache(template_system). Pode acontecer do erro aparecer somente ao rodar o jekyll, isso se deve por a chave (`{`) é considerada caracter reservado no *Jekyll*, de modo que eventualmente o artigo pode ser exibido no GitHub e não conseguir ser compilado pelo *Jekyll*. Para fazer a utilização dos códigos *mustache* foram necessárias realizar algumas adaptações como estas:

    {% raw  %}{{contexto.atributo}}{% endraw %}
    
Este tratamento permite que códigos mustache sejam inseridos dentro dessas tags e possam ser exibidos como se não tivesse nada, uma vez que estas tags não serão visíveis para quem estiver lendo o artigo.   

###<a id="0_3"> </a> Erro ao inserir códigos 

Ao editar e/ou visualizar artigos mais antigos é comum encontrar bloco de códigos com três acentos graves, este padrão é aceito na sintaxe *Markdown*, porém ao ser compilado pelo *Jekyll* dará erro . Este é mais simples de ser resolvido, pois basta apertar duas vezes a tecla `Tab` na linha ou bloco de código que se deseja destacar, que será visualizado como código.  
Uma ferramenta muito interessante para resolver esta e as outras situações citadas neste artigo é através do site do [Dillinger](http://dillinger.io/), que permite a visualização, criação de textos, edições de códigos e textos em Markdown. Desta forma, é possível colar um código e já editá-lo sem a necessidade de compilar para conseguir a visualização, agilizando a modificação dos artigos, e acabando com a ***tentativa e erro*** na tentativa de resolução de erros.
Note que ao utilizar este site para criação de artigos, toda vez que for inserido um comando, nome de classe, interface ou método, ou bloco de código, onde há duas tabulações ficará vermelho, na tela do lado esquerdo, e do lado direito haverá um destaque com um retangulo cinza.  
