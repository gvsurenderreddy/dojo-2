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
public class UsuariosFalso implements EntitySet {

  public static final Usuario USUARIO_A = novo()
      .login("aaaass")
      .senha("po432NThcWbeFUhKeQ0LK+43783A=")
      .novaInstancia();

  public static final Usuario USUARIO_B = novo()
      .login("aaa")
      .senha("eIGsssINThcWbeFUhKeQ0LK+43783A==")
      .novaInstancia();

  public static final Usuario USUARIO_C = novo()
      .login("ass")
      .senha("eIGINThcWbeFUhKeQ0LK+43783A=--9898")
      .novaInstancia();

  private static List<Usuario> todos = ImmutableList.<Usuario> builder()
      .add(USUARIO_A)
      .add(USUARIO_B)
      .add(USUARIO_C)
      .build();

  public static List<Usuario> getTodos() {
    return todos;
  }

  UsuariosFalso() {
  }

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("BANCO.USUARIO");
  }

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static ConstrutorDeUsuarioFalso novo() {
    return new ConstrutorDeUsuarioFalso();
  }

}
