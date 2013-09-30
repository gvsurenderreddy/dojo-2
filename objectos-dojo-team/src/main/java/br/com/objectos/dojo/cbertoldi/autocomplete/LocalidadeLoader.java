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

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.objectos.comuns.relational.jdbc.ResultSetWrapper;
import br.com.objectos.comuns.relational.search.ResultSetLoader;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class LocalidadeLoader implements ResultSetLoader<Localidade> {

  @Override
  public Localidade load(ResultSet resultSet) throws SQLException {
    ResultSetWrapper rs = new ResultSetWrapper("LOCALIDADE", resultSet);
    return new Loader(rs).novaInstancia();
  }

  private class Loader implements Localidade.Construtor {

    private final ResultSetWrapper rs;

    public Loader(ResultSetWrapper rs) {
      this.rs = rs;
    }

    @Override
    public Localidade novaInstancia() {
      LocalidadeJdbc impl = new LocalidadeJdbc(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getDescricao() {
      return rs.getString("DESCRICAO");
    }

  }

}