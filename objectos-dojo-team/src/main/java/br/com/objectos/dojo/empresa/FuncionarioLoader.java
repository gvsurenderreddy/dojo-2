/*
* Copyright 2011 Objectos, Fábrica de Software LTDA.
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

import java.sql.ResultSet;

import org.joda.time.LocalDate;

import br.com.objectos.comuns.relational.jdbc.ResultSetWrapper;
import br.com.objectos.comuns.relational.search.ResultSetLoader;
import br.com.objectos.dojo.taguiar.Contrato;

/**
 * @author caio.petreanu@objectos.com.br (Caio C. Petreanu)
 */
public class FuncionarioLoader implements ResultSetLoader<Funcionario> {

  @Override
  public Funcionario load(ResultSet resultSet) {
    ResultSetWrapper rs = new ResultSetWrapper("FUNCIONARIO", resultSet);
    return new Loader(rs).novaInstancia();
  }

  private class Loader implements Funcionario.Construtor {

    private final ResultSetWrapper rs;

    public Loader(ResultSetWrapper rs) {
      this.rs = rs;
    }

    @Override
    public Funcionario novaInstancia() {
      FuncionarioJdbc impl = new FuncionarioJdbc(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }
    @Override
    public String getNome() {
      return rs.getString("NOME");
    }

    @Override
    public String getMatricula() {
      return rs.getString("MATRICULA");
    }

    @Override
    public LocalDate getDataNascimento() {
      return rs.getLocalDate("DATA_NASCIMENTO");
    }

    @Override
    public LocalDate getDataAdmissao() {
      return rs.getLocalDate("ADMISSAO");
    }

    @Override
    public LocalDate getDataDemissao() {
      return rs.getLocalDate("DEMISSAO");
    }

    @Override
    public Superior getSuperior() {
      ResultSet resultSet = rs.getResultSet();
      return new FuncionarioLoaderSupervisor().load(resultSet);
    }

    @Override
    public Diretor getDiretor() {
      ResultSet resultSet = rs.getResultSet();
      return new FuncionarioLoaderDiretor().load(resultSet);
    }

    @Override
    public Contrato getRegimeDeContratacao() {
      int idx = rs.getInt("CONTRATO");
      Contrato[] contratos = Contrato.values();
      Contrato contrato = contratos[idx];
      return contrato;
    }
  }

}
