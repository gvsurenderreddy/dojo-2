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
package br.com.objectos.dojo.cbertoldi.autocomplete;

import java.util.List;

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;

import com.google.common.collect.ImmutableList;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class LocalidadesFalso implements EntitySet {

  public static final Localidade EUNAPOLIS_BA = novo()
      .descricao("Eunápolis")
      .novaInstancia();

  public static final Localidade PORTO_SEGURO_BA = novo()
      .descricao("Porto Seguro")
      .novaInstancia();

  public static final Localidade SAO_PAULO_SP = novo()
      .descricao("São Paulo")
      .novaInstancia();

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("PASSARO_BRANCO.LOCALIDADE");
  }

  private static final List<Localidade> todos = ImmutableList.<Localidade> builder()
      .add(EUNAPOLIS_BA)
      .add(PORTO_SEGURO_BA)
      .add(SAO_PAULO_SP)
      .build();

  public static List<Localidade> getTodos() {
    return todos;
  }

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static final ConstrutorDeLocalidadeFalso novo() {
    return new ConstrutorDeLocalidadeFalso();
  }

}