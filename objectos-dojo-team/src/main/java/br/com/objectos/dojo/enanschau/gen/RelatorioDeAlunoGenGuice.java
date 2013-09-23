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

import com.google.inject.Inject;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
class RelatorioDeAlunoGenGuice implements RelatorioDeAlunoGen {

  private final BuscarFatura buscarFatura;

  private final BuscarDisciplina buscarDisciplina;

  @Inject
  public RelatorioDeAlunoGenGuice(BuscarFatura buscarFatura, BuscarDisciplina buscarDisciplina) {
    this.buscarFatura = buscarFatura;
    this.buscarDisciplina = buscarDisciplina;
  }

  @Override
  public RelatorioDeAluno gerarDe(Aluno aluno) {
    return new Construtor(aluno).novaInstancia();
  }

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
      return buscarFatura.vencidas(aluno);
    }

    @Override
    public List<Disciplina> getDisciplinas() {
      return buscarDisciplina.porAluno(aluno);
    }

  }

}