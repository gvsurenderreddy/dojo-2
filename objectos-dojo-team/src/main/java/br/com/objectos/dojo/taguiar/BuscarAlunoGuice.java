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

import br.com.objectos.comuns.relational.jdbc.NativeSql;
import br.com.objectos.dojo.faculdade.Aluno;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
class BuscarAlunoGuice implements BuscarAluno {

  private final Provider<NativeSql> sqlProvider;

  @Inject
  BuscarAlunoGuice(Provider<NativeSql> sqlProvider) {
    this.sqlProvider = sqlProvider;
  }
  @Override
  public Aluno porId(int id) {
    return newSelect()

        .add("where ALUNO.ID = ?").param(id)

        .single();
  }

  @Override
  public List<Aluno> porCurso(Curso curso) {
    return null;
  }

  private NativeSql newSelect() {
    return sqlProvider.get()

        .add("select *")
        .add("")
        .add("")
        .add("");

  }

}