---
layout: post-alpha
title: "Autocomplete"
author: "Carolene Bertoldi"
user: "CaroleneBertoldi"
date: "2013-09-09"
published: true
partof: procedimento-crud-web
num: 9
outof: 9
---

##Introdução

O `autocomplete` é um serviço que permite a um `input` a função de completar dados que estão sendo 
digitados por um usuário. Um exemplo de `autocomplete` comum são os campos de origem e destino, de um
formulário de pesquisa de passagens aéreas. Ao começarmos a preencher estes campos é exibida uma 
lista de opções de localidades que vão se restringindo a medida em que digitamos.

Este tipo de campo é recomendado quando precisamos preencher um dado que tenha opções pré-definidas, 
como no caso da `combobox`, porém para os casos em que existem muitas opções de modo que uma `combobox` não atenderia, pois ficaria mais
dificil encontrar a opção desejada. Por exemplo, com o `autocomplete` o usuário digita parte do nome da localidade
desejada, tanto de origem quanto de destino e completa com uma das opções fornecidas pelo `autocomplete`, assim
ao digitarmos "Porto", aparecerão as opções "Porto Alegre", "Porto Velho", "Porto Seguro" e assim 
por diante.

### Construindo um autocomplete

Iremos construir uma página de pesquisa de passagens para a empresa de linhas áereas, 
precisaremos então de um `autocomplete` localidade para os campos de origem e destino.

Para implementação do `autocomplete` serão necessários: um Buscador, um Serviço e a declaração de um `input` 
`autocomplete` em um formulário.

#### Buscador

O buscador será construído utilizando os mesmos padrões de uma listagem comum. 
Precisaremos de um método `pagePorTodos()` e um filtro para os valores possíveis ao campo `autocomplete`.
É importante utilizarmos um `pagePorTodos()` e não um `porTodos()`, pois o `pagePorTodos()` nos permite
acesso a informação sobre a indexação da lista.

Escreva o <a href="https://github.com/objectos/objectos-dojo/blob/f4c2b31a58478d8b7a527af660cf8547860d6dbf/objectos-dojo-team/src/main/java/br/com/objectos/dojo/cbertoldi/`autocomplete`/TesteDeBuscarLocalidade.java">TesteDeBuscarLocalidade.</a>

Veja a implementação de <a href="https://github.com/objectos/objectos-dojo/blob/f4c2b31a58478d8b7a527af660cf8547860d6dbf/objectos-dojo-team/src/main/java/br/com/objectos/dojo/cbertoldi/`autocomplete`/BuscarLocalidadeGuice.java">BuscarLocalidadeGuice.</a>

#### Serviço

Precisamos de uma classe Java de `Serviço` responsável por fazer a interface entre o buscador e o código
HTML do `autocomplete`.

Criaremos uma classe seguindo a mesma nomeclatura dos demais serviços, acrescentado o sufixo `Json`.

      public class ServicoDeLocalidadeJson {
      
      }
      
Será necessária a utilização de uma variável de instância de BuscarLocalidade,

      public class ServicoDeLocalidadeJson {
      
        private final BuscarLocalidade buscarLocalidade;
      
        @Inject
        public ServicoDeLocalidadeJson(BuscarLocalidade buscarLocalidade) {
          this.buscarLocalidade = buscarLocalidade;
        }
      
      }     

e um método `get(Request request)` que retorna os dados em `Json`.

<pre>
  <code>
    @Get
    public Reply&lt;?&gt; get(Request request) {
      PassaroBrancoRequestWrapper wrapper = new PassaroBrancoRequestWrapper(request);
      
      PageList&lt;Localidade&gt; list = buscarLocalidade.pagePorTodos(wrapper);
      List&lt;Localidade&gt; rows = list.getRows();
      
      return Reply.with(rows).as(Json.class);
    }
  </code>
</pre>

Veja <a href="https://github.com/objectos/objectos-dojo/blob/f4c2b31a58478d8b7a527af660cf8547860d6dbf/objectos-dojo-team/src/main/java/br/com/objectos/dojo/cbertoldi/`autocomplete`/ServicoDeLocalidade`Json`.java">ServicoDeLocalidade`Json`</a> completa.

#### Adicionar URL a Módulo

No Módulo, declare a URL definida no `TesteDeServico` como chave da classe de Servico da seguinte maneira.

  	@Override
  	protected void bindApiV3() {
   		at("/api/passagens/v3/localidade/json").serve(ServicoDeLocalidadeJson.class);
  	}
  
A url estará no mesmo método em que estão declarados os demais Servicos.

#### Formulário de compra de passagens

Um campo `autocomplete` é um campo `input` `type="text"`, utilizando o atributo de `data-way-field` `autocomplete`.
Este atributo `autocomplete`, também possui atributos onde estão definidos os dados para habilitar a função de 
completar palavra no `input`.

	{% raw  %}{{^origem}}{% endraw %}
  	<input type="text"
           name="origem"
           data-way-field="{
  	         'label' : 'Origem',
             'autocomplete': {
       	         'url' : '{% raw  %}{{bricks.baseUrl}}{% endraw %}/api/passagens/v3/aeroporto/json',
          	     'options' : {
              	     'filter' : {
                       	'path' : 'descricao'
                         },
                       'urlOptions' : {
                          'queryVarName' : 'q'
                         },
                       'valueFilter' : function(data) {
                           return data.id;
                        }
                    }
                },
                'validators' : ['required']
             }" />
	{% raw  %}{{/origem}}{% endraw %}

`url` - mesma URL adicionada ao Modulo para ServicoDeLocalidadeJson

`path`- a este atributo é relacionada a chave de serialização do tipo localidade.
Neste exemplo esta chave, "origem" estará em `PassagemSerializer`, associada a um atributo do tipo Localidade.

		class PassagemSerializer extends JsonSerializer<Ipo> {

  			@Override
  			public void serialize(Passagem value, JsonGenerator jgen, SerializerProvider provider)
     			throws IOException, JsonProcessingException {

   					jgen.writeStartObject();

    				jgen.writeNumberField("id", value.getId());
    				jgen.writeObjectField("origem", value.getLocalidade());
    				jgen.writeObjectField("destino", value.getLocalidade());
    				jgen.writeObjectField("data", value.getLocaldate();

    				jgen.writeEndObject();

 			}

		}

onde Localidade, por sua vez possui a classe de serialização `LocalidadeSerializer`, onde está declarada a
chave "descrição".

		class LocalidadeSerializer extends JsonSerializer<Ipo> {

  			@Override
  			public void serialize(Localidade value, JsonGenerator jgen, SerializerProvider provider)
     			throws IOException, JsonProcessingException {

   					jgen.writeStartObject();

    				jgen.writeNumberField("id", value.getId());
    				jgen.writeObjectField("descricao", value.getLocaldate();

    				jgen.writeEndObject();

 			}

		}

Note que `descricao` inserido dentro da seção "origem" é o mesmo que se fosse declarado assim:

        'filter' : {
         	'path' : 'origem.descricao'
         }

`queryVarName` - o valor deste atributo deve possuir o mesmo nome que filtro declarado em BuscarLocalidadeGuice

 		private NativeSql newPager(String what, RequestWrapper wrapper) {
   			String localidade = wrapper.param("q");

    		return newSelect(what)
        		.add("where 1 = 1")
        		.addIf("and NOME_DO_BANCO.LOCALIDADE like concat('%',?,'%')").paramNotNull(localidade)

        		.add("order by")
        		.add("NOME_DO_BANCO.LOCALIDADE");
 		}
		
`valueFilter` - neste atributo é identificado o valor que de fato irá para o banco de dados.
 
     'valueFilter' : function(data) {
        return data.id;
     }

onde o `id` dentro da seção origem equivale a `origem.id`, ou seja o valor do campo que será enviado ao 
formulário para ser armazenado no banco de dados é o id da localidade selecionada.

Assim, teremos habilitado um campo de `autocomplete` para preenchimento da origem desejada no formulário
de pesquisa de passagens.

E para habilitarmos também o campo de destino só será necessário adicionar outro `input`,`name="destino"`, também `autocomplete`,
no formulário, que utilizará o mesmo `ServicoDeLocalidade`, conforme o exemplo abaixo.

	{% raw  %}{{^destino}}{% endraw %}
        <input type="text"
               name="destino"
               data-way-field="{
               	'label' : 'Origem',
                'autocomplete': {
                	'url' : '{% raw  %}{{bricks.baseUrl}}{% endraw %}/api/passagens/v3/aeroporto/json',
                    'options' : {
                    	'filter' : {
                        	'path' : 'descricao'
                         },
                         'urlOptions' : {
                         	'queryVarName' : 'q'
                         },
                         'valueFilter' : function(data) {
                             return data.id;
                         }
                     }
                },
               'validators' : ['required']
            }" />
	{% raw  %}{{/destino}}{% endraw %}