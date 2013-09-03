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

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;

import com.google.common.collect.ImmutableList;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class AlunosFalso implements EntitySet {

  public static final Aluno ALUNO_1 = novo()
      .nome("Aluno 1")
      .matricula("185745")
      .novaInstancia();

  public static final Aluno ALUNO_2 = novo()
      .nome("Aluno 2")
      .matricula("18235745")
      .novaInstancia();

  public static final Aluno ALUNO_3 = novo()
      .nome("Aluno 3")
      .matricula("1835745")
      .novaInstancia();

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("DOJO.ALUNO");
  }

  private static final List<Aluno> todos = ImmutableList.<Aluno> builder()
      .add(ALUNO_1)
      .add(ALUNO_2)
      .add(ALUNO_3)
      .build();

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static final ConstrutorDeAlunoFalso novo() {
    return new ConstrutorDeAlunoFalso();
  }

}