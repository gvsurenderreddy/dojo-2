---
layout: post-alpha
title: "Uso de funções no Gen"
author: "Edenir Norberto Anschau"
user: "eanschau"
date: "2013-09-12"
partof: faq-gen
num: 0
---

## Introdução<a id="topo"> </a>
Em `RelatarioDeAlunoGen` usamos um objeto do tipo `Aluno` para gerar um `RelatorioDeAluno`. Mas e se precisarmos dado uma lista
de objetos de tipo `Aluno`, gerar uma lista de `RelatorioDeAluno`. Precisaremos de outro Gen?

Se já temos como gerar um `RelatorioDeAluno` de um aluno, então poderíamos pensar em como chamar o método `gerarDe()` para cada item
da lista e para cada `RelatorioDeAluno` gerado, adicioná-lo em outra lista, assim retornar o resultado esperado.
Essa implementação pode ser feita numa classe separada ou em outro método dentro de `RelatarioDeAlunoGen`, aqui vamos analisar como será
feita a implementação com a segunda opção.

`RelatarioDeAlunoGen` já está implementado e testado, então precisamos de um método com a seguinte assinatura:

`List<RelatorioDeAluno> gerarDe(List<Aluno> aluno);`

A ideia é o método acima chamar o método `gerarDe(Aluno aluno)`, então podemos implementá-lo assim:

	public List<RelatorioDeAluno> gerarDe(List<Aluno> alunos) {
	  List<RelatorioDeAluno> relatorios = newArrayList();
	  for (Aluno aluno : alunos) {
	    RelatorioDeAluno relatorioDeAluno = gerarDe(aluno);
	    relatorios.add(relatorioDeAluno);
	  }

	  return relatorios;
	}

Utizando funções temos outra alternativa para implementar esse novo método. Funções entre outros usos, nos permite transformar uma coleção de objetos X,
em objetos Y. No nosso caso, precisamos transformar uma lista de `Aluno` para uma lista de `RelatorioDeAluno`.   

Para implementar esse método usando funções vamos utilizar recursos do [Guava](http://code.google.com/p/guava-libraries/wiki/FunctionalExplained#Functions)
para trabalhar com programação funcional em Java.

Utilizando funções, podemos obter o mesmo resultado com o seguinte código:

	public List<RelatorioDeAluno> gerarDe(List<Aluno> alunos) {
	  return Lists.transform(alunos, new ToRelatorioDeAluno());
	}

Abaixo a função que estamos passamos para o método `transform()`:

	private class ToRelatorioDeAluno implements Function<Aluno, RelatorioDeAluno> {
	  @Override
	  public RelatorioDeAluno apply(Aluno input) {
	    return gerarDe(input);
	  }
	}

Na implementaçao do método usando função, chamamos o método `transform()` da classe `List`, para fazer a transformação que precisamos. Essa
transformação ocorrerá de fato quando for necessário utilizarmos algum elemento da lista de `RelatorioDeAluno`, pois a função aplicada é
`lazyly`. Para evitarmos o carregamento lazy da lista, é necessário copiar a lista retornada para uma nova lista, isso pode ser feito usando 
o método `copyOf()` da classe [`ImmutableList`](http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableList.html).

	public List<RelatorioDeAluno> gerarDe(List<Aluno> alunos) {
	  List<RelatorioDeAluno>  res = Lists.transform(alunos, new ToRelatorioDeAluno()); 
	  return ImmutableList.copyOf(res);
	}

Com isso garantimos que quando o método `gerarDe()` for chamado a função será aplicada no mesmo instante.

##Códigos-fonte
[RelatorioDeAlunoGenGuice.java](https://github.com/objectos/objectos-dojo/blob/f090c77cdbfc606b57675f7b6a9c04b830208905/objectos-dojo-team/src/main/java/br/com/objectos/dojo/enanschau/gen/RelatorioDeAlunoGenGuice.java)
