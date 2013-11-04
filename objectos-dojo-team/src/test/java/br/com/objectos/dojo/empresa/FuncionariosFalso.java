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
import static br.com.objectos.dojo.empresa.SuperioresFalso.SUPERIOR_1;
import static br.com.objectos.dojo.empresa.SuperioresFalso.SUPERIOR_2;

import java.util.List;

import org.joda.time.LocalDate;

import br.com.objectos.comuns.testing.jdbc.EntitySet;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.comuns.testing.jdbc.Truncate;
import br.com.objectos.dojo.taguiar.Contrato;

import com.google.common.collect.ImmutableList;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class FuncionariosFalso implements EntitySet {

  public static final Funcionario FUNCIONARIO_1 = novo()
      .nome("Funcionario 1")
      .matricula("T0033000")
      .dataNascimento(new LocalDate(1980, 6, 01))
      .dataAdmissao(new LocalDate(2004, 12, 10))
      .dataDemissao(new LocalDate(2012, 1, 3))
      .superior(SUPERIOR_1)
      .diretor(DIRETOR_1)
      .regimeDeContratacao(Contrato.ESTAGIO)
      .novaInstancia();
  public static final Funcionario FUNCIONARIO_2 = novo()
      .nome("Funcionario 2")
      .matricula("00112")
      .dataNascimento(new LocalDate(1982, 6, 01))
      .dataAdmissao(new LocalDate(2004, 10, 10))
      .dataDemissao(new LocalDate(2012, 3, 3))
      .superior(SUPERIOR_1)
      .diretor(DIRETOR_2)
      .regimeDeContratacao(Contrato.ESTAGIO)
      .novaInstancia();
  public static final Funcionario FUNCIONARIO_3 = novo()
      .nome("Funcionario 3")
      .matricula("T0033010")
      .dataNascimento(new LocalDate(1980, 6, 01))
      .dataAdmissao(new LocalDate(2007, 12, 10))
      .dataDemissao(new LocalDate(2012, 6, 3))
      .superior(SUPERIOR_1)
      .diretor(DIRETOR_3)
      .regimeDeContratacao(Contrato.CLT)
      .novaInstancia();

  public static final Funcionario FUNCIONARIO_4 = novo()
      .nome("Funcionario 4")
      .matricula("T00330500")
      .dataNascimento(new LocalDate(1976, 6, 01))
      .dataAdmissao(new LocalDate(2005, 12, 10))
      .dataDemissao(new LocalDate(2012, 12, 3))
      .superior(SUPERIOR_2)
      .diretor(DIRETOR_1)
      .regimeDeContratacao(Contrato.CLT)
      .novaInstancia();
  public static final Funcionario FUNCIONARIO_5 = novo()
      .nome("Funcionario 5")
      .matricula("001123")
      .dataNascimento(new LocalDate(1982, 6, 01))
      .dataAdmissao(new LocalDate(1999, 10, 10))
      .dataDemissao(new LocalDate(2012, 5, 3))
      .superior(SUPERIOR_2)
      .diretor(DIRETOR_2)
      .regimeDeContratacao(Contrato.ESTAGIO)
      .novaInstancia();
  public static final Funcionario FUNCIONARIO_6 = novo()
      .nome("Funcionario 6")
      .matricula("T0033011")
      .dataNascimento(new LocalDate(1980, 6, 01))
      .dataAdmissao(new LocalDate(2007, 11, 10))
      .dataDemissao(new LocalDate(2012, 9, 3))
      .superior(SUPERIOR_2)
      .diretor(DIRETOR_3)
      .regimeDeContratacao(Contrato.CLT)
      .novaInstancia();

  public static List<Funcionario> todos = ImmutableList.<Funcionario> builder()
      .add(FUNCIONARIO_1)
      .add(FUNCIONARIO_2)
      .add(FUNCIONARIO_3)

      .add(FUNCIONARIO_4)
      .add(FUNCIONARIO_5)
      .add(FUNCIONARIO_6)
      .build();

  public static List<Funcionario> getTodos() {
    return todos;
  }

  FuncionariosFalso() {
  }

  @Override
  public void truncate(Truncate truncate) {
    truncate.table("DATABASE.FUNCIONARIO");
  }

  @Override
  public void load(SqlUnit sqlUnit) {
    sqlUnit.batchInsert(todos);
  }

  private static ConstrutorDeFuncionarioFalso novo() {
    return new ConstrutorDeFuncionarioFalso();
  }

}
