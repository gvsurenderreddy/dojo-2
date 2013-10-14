---
layout: post
title: "Usar composição parar gerar objetos falsos"
author: "Edenir Norberto Anschau"
date: 2012-10-01
published: true
partof: java
num: 5
outof: 6
---

## Introdução
Ao testar uma classe que use composição, também é possível criar objetos falsos para o teste usando composição, como veremos abaixo.

## Exemplo
Veja o exemplo abaixo da interface `Disciplina`:

	public interface DisciplinaUI {

	 interface Construtor extends br.com.objectos.comuns.base.Construtor<DisciplinaUI> {

	  Professor getProfessor();

	  String getNome();

	 }

	 int getId();

	 Professor getProfessor();

	 String getNome();

	}

Em determinada situação em nosso sistema tornou-se desnecessário `Disciplina` ter uma instância de `Aluno` com todos seus atributos, e sim somente o nome do aluno.
Nesse caso não podemos simplesmente refatorar a entidade `Discplina` para que isso aconteça, então decidimos criar outra classe para representar o que precisamos, que vai ser composta
através de uma instância de `DISCIPLINA`, então chegamos ao seguinte código:

	public interface DisciplinaUI {

	 interface Construtor extends br.com.objectos.comuns.base.Construtor<DisciplinaUI> {

	  String getProfessor();

	  String getNome();

	 }

	 int getId();

	 String getProfessor();

	 String getNome();

	}

## Teste e objetos falsos de `DisciplinaUI`
Ao criarmos o teste para essa classe, notaremos que vamos precisar criar a classe `DisciplinasUIFalso` para criarmos os objetos falsos necessários. 
Isso deve funcionar, mas existe a possibilidade de gerar objetos falsos de `DisciplinaUI` sem necessáriamente de que criar uma classe só para esse uso.

## Use composição para criar os objetos falsos
Precisaremos apenas de uma classe que crie objetos do tipo `DisciplinaUI`, pare isso precisaremos criar a classe `ConstrutorDeDisciplinaUIFalso`:

	public class ConstrutorDeDisciplinaUIFalso implements DisciplinaUI {

	  private final Disciplina disciplina;

	  public ConstrutorDeDisciplinaUIFalso(Disciplina disciplina) {
	    this.disciplina = disciplina;
	  }

	  @Override
	  public int getId() {
	    return disciplina.getId();
	  }

	  @Override
	  public String getAluno() {
	    Aluno aluno = disciplina.getAluno();
	    return aluno.getNome();
	  }

	  @Override
	  public String getProfessor() {
	    Professor professor = disciplina.getProfessor();
	    return professor.getNome();
	  }

	  @Override
	  public String getNome() {
	    return disciplina.getNome();
	  }

	}

Note que recebemos uma instância de `Disciplina` no construtor e partir dela iremos obter os demais atribiutos da classe.

O teste ficará com a estrutura abaixo:

	public void page_por_aluno_key() {
	  Aluno aluno = AlunosFalso.ALUNO_1;
	  AlunoKey alunoKey = new AlunoKey(aluno);

	  List<Disciplina> disciplinas = ImmutableList.of(DISCIPLINA_1_ALUNO_1, DISCIPLINA_2_ALUNO_1);
	  List<DisciplinaUI> contra = transform(disciplinas, new ToDisciplinaUI());
	  List<String> prova = Lists.transform(contra, new DisciplinaToUIString());

	  List<DisciplinaUI> todos = gen.gerarDe(alunoKey);
	  List<String> res = Lists.transform(todos, new DisciplinaToUIString());

	  assertThat(res.size(), equalTo(2));
	  assertThat(res, equalTo(prova));
	}

	private class ToDisciplinaUI implements Function<Disciplina, DisciplinaUI> {
	  @Override
	  public DisciplinaUI apply(Disciplina input) {
	    return new ConstrutorDeDisciplinaUIFalso(input);
	  }
	}

Criamos uma função que nos auxiliará na criação da lista de objetos falsos, observe que a função utiliza também a classe `ConstrutorDeDisciplinaUIFalso` para construir uma instância
de `DisciplinaUI`. Através de uma lista de objetos falsos do tipo `Disciplina` conseguimos facilmente gerar outra lista de falsos de `DisciplinaUI` sem a necessidade de criar uma classe só pra esse
fim, como foi comentado anteriormente.

##Códigos-fonte
[Disciplina.java](https://github.com/objectos/objectos-dojo/blob/cd8c1494bcd9786cae91abe9762b36cf594496de/objectos-dojo-team/src/main/java/br/com/objectos/dojo/enanschau/gen/Disciplina.java)  
[DisciplinaUI.java](https://github.com/objectos/objectos-dojo/blob/cd8c1494bcd9786cae91abe9762b36cf594496de/objectos-dojo-team/src/main/java/br/com/objectos/dojo/enanschau/gen/DisciplinaUI.java)  
[ConstrutorDeDisciplinaUIFalso.java](https://github.com/objectos/objectos-dojo/blob/cd8c1494bcd9786cae91abe9762b36cf594496de/objectos-dojo-team/src/test/java/br/com/objectos/dojo/enanschau/gen/ConstrutorDeDisciplinaUIFalso.java)  
[TesteDeDisciplinaUIGen.java](https://github.com/objectos/objectos-dojo/blob/cd8c1494bcd9786cae91abe9762b36cf594496de/objectos-dojo-team/src/test/java/br/com/objectos/dojo/enanschau/gen/TesteDeDisciplinaUIGen.java)  
