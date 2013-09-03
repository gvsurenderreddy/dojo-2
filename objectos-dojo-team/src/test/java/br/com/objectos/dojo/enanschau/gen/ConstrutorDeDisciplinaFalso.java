/*
 * Copyright 2013 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.dojo.enanschau.gen;

import br.com.objectos.dojo.enanschau.gen.Disciplina.Construtor;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class ConstrutorDeDisciplinaFalso implements Construtor {

  private Aluno aluno;
  private String nome;

  @Override
  public Disciplina novaInstancia() {
    return new DisciplinaJdbc(this);
  }

  public ConstrutorDeDisciplinaFalso aluno(Aluno aluno) {
    this.aluno = aluno;
    return this;
  }

  public ConstrutorDeDisciplinaFalso nome(String nome) {
    this.nome = nome;
    return this;
  }

  @Override
  public Aluno getAluno() {
    return aluno;
  }

  @Override
  public String getNome() {
    return nome;
  }

}