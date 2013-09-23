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

import java.util.List;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class ConstrutorDeRelatorioDeAlunoFalso implements RelatorioDeAluno.Construtor {

  private String nomeDoAluno;
  private List<Fatura> faturasEmAberto;
  private List<Disciplina> disciplinas;

  @Override
  public RelatorioDeAluno novaInstancia() {
    return new RelatorioDeAlunoPojo(this);
  }

  @Override
  public String getNomeDoAluno() {
    return nomeDoAluno;
  }

  @Override
  public List<Fatura> getFaturasEmAberto() {
    return faturasEmAberto;
  }

  @Override
  public List<Disciplina> getDisciplinas() {
    return disciplinas;
  }

  public ConstrutorDeRelatorioDeAlunoFalso aluno(Aluno aluno) {
    this.nomeDoAluno = aluno.getNome();
    return this;
  }

  public ConstrutorDeRelatorioDeAlunoFalso faturasEmAberto(List<Fatura> faturasEmAberto) {
    this.faturasEmAberto = faturasEmAberto;
    return this;
  }

  public ConstrutorDeRelatorioDeAlunoFalso disciplinas(List<Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
    return this;
  }

}