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

import br.com.objectos.dojo.enanschau.gen.Aluno.Construtor;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class ConstrutorDeAlunoFalso implements Construtor {

  private String nome;
  private String matricula;

  @Override
  public Aluno novaInstancia() {
    return new AlunoJdbc(this);
  }

  public ConstrutorDeAlunoFalso nome(String nome) {
    this.nome = nome;
    return this;
  }

  public ConstrutorDeAlunoFalso matricula(String matricula) {
    this.matricula = matricula;
    return this;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getMatricula() {
    return matricula;
  }

}