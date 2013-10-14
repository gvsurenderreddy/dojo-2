---
layout: post
title: Métodos privados
author: "Edenir Norberto Anschau"
date: 2013-09-30
published: true
partof: java
num: 4
---

## Introdução
Normalmente os métodos privados acabam sempre auxiliando os métodos publicos na  execução de alguma lógica, isso ajuda a quebrar um problema maior em partes menores
para ser resolvidos dentro de uma mesma classe.

Além do uso na questão da visibilidade do método para outras classe, usamos amplamente métodos privados para isolar o código em comum que está sendo repetido em outros métodos
da mesma classe, que basicamente fazem a mesma tarefa.

## Implementação com método público
Imagine que devemos gerar um relatório de faturas de um determinado aluno, nesse relatório deve conter listas de faturas abertas,
vencidas e pagas. Esse relatório poderia ser representando pela interface abaixo:

    public interface RelatorioDeAluno {

	  interface Construtor extends br.com.objectos.comuns.base.Construtor<RelatorioDeAluno> {

	    String getNomeDoAluno();

	    List<Fatura> getFaturasEmAberto();

	    List<Fatura> getFaturasVencidas();

	    List<Fatura> getFaturasPagas();

	  }

	  String getNomeDoAluno();

	  List<Fatura> getFaturasEmAberto();

	  List<Fatura> getFaturasVencidas();

	  List<Fatura> getFaturasPagas();

	}

A modelagem acima é apenas para efeitos didádicos. Para gerar essas faturas teríamos na classe `RelatorioDeAlunoGen`
([clique aqui](http://dojo.objectos.com.br/procedimento/gen/00.-teste-de-gen.html) para ler sobre Gens) a seguinte implementação do construtor de `RelatorioDeAlunoGen`:


	private class Construtor implements RelatorioDeAluno.Construtor {

      private final Aluno aluno;
    
      public Construtor(Aluno aluno) {
        this.aluno = aluno;
      }
    
      @Override
      public RelatorioDeAluno novaInstancia() {
        return new RelatorioDeAlunoPojo(this);
      }
    
      @Override
      public String getNomeDoAluno() {
        return aluno.getNome();
      }
    
      @Override
      public List<Fatura> getFaturasEmAberto() {
        List<Fatura> faturas = buscarFatura.porStatus(FaturaStatus.ABERTA, aluno);
        return faturas;
      }
    
      @Override
      public List<Fatura> getFaturasVencidas() {
        List<Fatura> faturas = buscarFatura.porStatus(FaturaStatus.VENCIDA, aluno);
        return faturas;
      }
    
      @Override
      public List<Fatura> getFaturasPagas() {
        List<Fatura> faturas = buscarFatura.porStatus(FaturaStatus.PAGA, aluno);
        return faturas;
      }
    
    }

Observe que nos três métodos que retornam as faturas, repetimos praticamente o mesmo código, alterando somente o tipo do status da fatura. Essa
repetição de código continuaria a crescer caso precisassemos obter faturas com outros status. Abaixo veremos com isolar essa parte do código em comum
num único método, evitando repetição de código.

## Implementação com método privado
Para eliminar a repetição de código do exemplo anterior, primeiro temos que verificar o que há em comum na implementação desses métodos. No nosso caso,
em comum temos que sempre iremos ter que passar um tipo de `FaturaStatus` mais uma uma instância de `Aluno`, e o retorno sempre será o mesmo, uma lista
de `Fatura`. Além disso, sempre chamamos o método `porStatus()` da interface `BuscarFatura`.

Então podemos começar criando um método privado que irá receber como parâmetro uma instância de `FaturaStatus` e outra de `Aluno`. Além disso irá retornar
uma lista de `Fatura`.


	private List<Fatura> faturasDe(FaturaStatus status, Aluno aluno) {
	  return null;
	}

Ainda não chegamos aonde desejamos, o próximo passo é extrair a implementação que foi repetida nos métodos acima e implementar no novo método:


	private List<Fatura> faturasDe(FaturaStatus status, Aluno aluno) {
	  List<Fatura> faturas = buscarFatura.porStatus(status, aluno);
	  return faturas;
	}


Dado isso, agora a implementação do consutror de `RelatorioDeAlunoGen` ficará assim:

	private class Construtor implements RelatorioDeAluno.Construtor {

	  private final Aluno aluno;

	  public Construtor(Aluno aluno) {
	    this.aluno = aluno;
	  }

	  @Override
	  public RelatorioDeAluno novaInstancia() {
		return new RelatorioDeAlunoPojo(this);
	  }

	  @Override
	  public String getNomeDoAluno() {
	    return aluno.getNome();
	  }

	  @Override
	  public List<Fatura> getFaturasEmAberto() {
	    return faturasDe(FaturaStatus.ABERTA, aluno);
	  }

	  @Override
	  public List<Fatura> getFaturasVencidas() {
	    return faturasDe(FaturaStatus.VENCIDA, aluno);
	  }

	  @Override
	  public List<Fatura> getFaturasPagas() {
	    return faturasDe(FaturaStatus.PAGA, aluno);
	  }

	  private List<Fatura> faturasDe(FaturaStatus status, Aluno aluno) {
	    List<Fatura> faturas = buscarFatura.porStatus(status, aluno);
	    return faturas;
	  }

	}


Além da redução de código, agora temos um único lugar para fazer alteração quando precisar, com isso evitamos que o mesmo código ficasse espalhado e consequentemente facilitará a
manutenção do mesmo.


## Observaçãoes
Ao implementar métodos que não serão acessados externamente é sugerido sempre usar o modificar mais restritivo, nesse caso o private. Então somente aumente
a visibilidade de um método caso haja necessidade de seram usados por outras classes.
