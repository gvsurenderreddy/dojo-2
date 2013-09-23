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
public class RelatorioDeAlunoPojo implements RelatorioDeAluno {

  private final String nomeDoAluno;
  private final List<Fatura> faturasEmAberto;
  private final List<Disciplina> disciplinas;

  public RelatorioDeAlunoPojo(Construtor construtor) {
    nomeDoAluno = construtor.getNomeDoAluno();
    faturasEmAberto = construtor.getFaturasEmAberto();
    disciplinas = construtor.getDisciplinas();
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

}