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

import org.joda.time.DateTime;

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;

import com.google.common.collect.ImmutableList;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class AlunosFalso implements EntitySet {

  private final static DateTime DATE_TIME = new DateTime();

  public static final Aluno ALUNO_1 = novo()
      .nome("Aluno 1")
      .matricula("AAAA1")
      .curso(CURSO_A)
      .dataDeCriacao(DATE_TIME.minusDays(8))
      .novaInstancia();

  public static final Aluno ALUNO_2 = novo()
      .nome("Aluno 2")
      .matricula("AAAA2")
      .curso(CURSO_A)
      .dataDeCriacao(DATE_TIME.minusDays(12))
      .novaInstancia();

  public static final Aluno ALUNO_3 = novo()
      .nome("Aluno 3")
      .matricula("AAAA3")
      .curso(CURSO_A)
      .dataDeCriacao(DATE_TIME.plusDays(8))
      .novaInstancia();

  private static List<Aluno> todos = ImmutableList.<Aluno> builder()
      .add(ALUNO_1)
      .add(ALUNO_2)
      .add(ALUNO_3)
      .build();

  public static List<Aluno> getTodos() {
    return todos;
  }

  AlunosFalso() {
  }

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("BANCO.ALUNO");
  }

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static ConstrutorDeAlunoFalso novo() {
    return new ConstrutorDeAlunoFalso();
  }

}
