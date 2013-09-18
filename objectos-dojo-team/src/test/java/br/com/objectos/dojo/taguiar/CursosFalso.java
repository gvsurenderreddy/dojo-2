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
package br.com.objectos.dojo.taguiar;

import java.util.List;

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;

import com.google.common.collect.ImmutableList;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class CursosFalso implements EntitySet {

  public static final Curso CURSO_A = novo()
      .nome("Curso A")
      .codigo("AAA")
      .novaInstancia();

  public static final Curso CURSO_B = novo()
      .nome("Curso B")
      .codigo("BBB")
      .novaInstancia();

  public static final Curso CURSO_C = novo()
      .nome("Curso C")
      .codigo("CCC")
      .novaInstancia();

  private static final List<Curso> todos = ImmutableList.<Curso> builder()
      .add(CURSO_A)
      .add(CURSO_B)
      .add(CURSO_C)
      .build();

  public static List<Curso> getTodos() {
    return todos;
  }

  CursosFalso() {
  }

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("BANCO.ALUNO");
  }

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static ConstrutorDeCursoFalso novo() {
    return new ConstrutorDeCursoFalso();
  }

}