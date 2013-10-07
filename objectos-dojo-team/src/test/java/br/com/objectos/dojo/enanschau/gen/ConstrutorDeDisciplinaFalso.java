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

  private Professor professor;
  private String nome;

  @Override
  public Disciplina novaInstancia() {
    return new DisciplinaJdbc(this);
  }

  public ConstrutorDeDisciplinaFalso professor(Professor professor) {
    this.professor = professor;
    return this;
  }

  public ConstrutorDeDisciplinaFalso nome(String nome) {
    this.nome = nome;
    return this;
  }

  @Override
  public Professor getProfessor() {
    return professor;
  }

  @Override
  public String getNome() {
    return nome;
  }

}