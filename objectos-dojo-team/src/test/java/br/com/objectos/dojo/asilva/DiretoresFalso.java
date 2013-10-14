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
package br.com.objectos.dojo.asilva;

import java.util.List;

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;
import br.com.objectos.dojo.empresa.Diretor;

import com.google.common.collect.ImmutableList;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class DiretoresFalso implements EntitySet {

  public static final Diretor DIRETOR_1 = novo()
      .nome("Diretor 1")
      .novaInstancia();
  public static final Diretor DIRETOR_2 = novo()
      .nome("Diretor 2")
      .novaInstancia();
  public static final Diretor DIRETOR_3 = novo()
      .nome("Diretor 3")
      .novaInstancia();

  private static final List<Diretor> todos = ImmutableList.<Diretor> builder()
      .add(DIRETOR_1)
      .add(DIRETOR_2)
      .add(DIRETOR_3)
      .build();

  public static List<Diretor> getTodos() {
    return todos;
  }

  DiretoresFalso() {
  }

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("DATABASE.DIRETOR");
  }

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static ConstrutorDeDiretorFalso novo() {
    return new ConstrutorDeDiretorFalso();
  }

}