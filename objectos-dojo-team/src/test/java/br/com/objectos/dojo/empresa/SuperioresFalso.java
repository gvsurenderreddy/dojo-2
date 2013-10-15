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
package br.com.objectos.dojo.empresa;

import static br.com.objectos.dojo.empresa.DiretoresFalso.DIRETOR_1;
import static br.com.objectos.dojo.empresa.DiretoresFalso.DIRETOR_2;
import static br.com.objectos.dojo.empresa.DiretoresFalso.DIRETOR_3;

import java.util.List;

import org.joda.time.LocalDate;

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;
import br.com.objectos.dojo.empresa.Superior;

import com.google.common.collect.ImmutableList;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class SuperioresFalso implements EntitySet {

  public static final Superior SUPERIOR_1 = novo()
      .nome("Superior 1")
      .matricula("0001")
      .dataNascimento(new LocalDate(1960, 1, 1))
      .dataAdmissao(new LocalDate(2002, 12, 10))
      .dataDemissao(new LocalDate(2004, 12, 10))
      .diretor(DIRETOR_1)
      .novaInstancia();
  public static final Superior SUPERIOR_2 = novo()
      .nome("Superior 2")
      .matricula("0002")
      .dataNascimento(new LocalDate(1959, 1, 1))
      .dataAdmissao(new LocalDate(2001, 12, 10))
      .dataDemissao(new LocalDate(2005, 12, 10))
      .diretor(DIRETOR_2)
      .novaInstancia();
  public static final Superior SUPERIOR_3 = novo()
      .nome("Superior 3")
      .matricula("0003")
      .dataNascimento(new LocalDate(1958, 1, 1))
      .dataAdmissao(new LocalDate(2000, 12, 10))
      .dataDemissao(new LocalDate(2006, 12, 10))
      .diretor(DIRETOR_3)
      .novaInstancia();

  private static List<Superior> todos = ImmutableList.<Superior> builder()
      .add(SUPERIOR_1)
      .add(SUPERIOR_2)
      .add(SUPERIOR_3)
      .build();

  public static List<Superior> getTodos() {
    return todos;
  }

  SuperioresFalso() {
  }

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("DATABASE.SUPERIOR");
  }

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static ConstrutorDeSuperiorFalso novo() {
    return new ConstrutorDeSuperiorFalso();
  }

}