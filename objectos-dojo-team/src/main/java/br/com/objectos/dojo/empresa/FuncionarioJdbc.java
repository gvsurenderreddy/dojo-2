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
import java.sql.SQLException;

import org.joda.time.LocalDate;

import br.com.objectos.comuns.relational.jdbc.GeneratedKeyCallback;
import br.com.objectos.comuns.relational.jdbc.Insert;
import br.com.objectos.dojo.taguiar.Contrato;

/**
 * @author caio.petreanu@objectos.com.br (Caio C. Petreanu)
 */
public class FuncionarioJdbc implements Funcionario {

  private int id;
  private final String nome;
  private final String matricula;
  private final LocalDate dataNascimento;
  private final LocalDate dataAdmissao;
  private final LocalDate dataDemissao;
  private final Superior superior;
  private final Diretor diretor;
  private final Contrato regimeDeContratacao;

  public FuncionarioJdbc(Construtor construtor) {
    nome = construtor.getNome();
    matricula = construtor.getMatricula();
    dataNascimento = construtor.getDataNascimento();
    dataAdmissao = construtor.getDataAdmissao();
    dataDemissao = construtor.getDataDemissao();
    superior = construtor.getSuperior();
    diretor = construtor.getDiretor();
    regimeDeContratacao = construtor.getRegimeDeContratacao();

  }

  @Override
  public Insert getInsert() {
    return Insert.into("DATABASE.FUNCIONARIO")

        .value("NOME", nome)
        .value("MATRICULA", matricula)
        .value("DATA_NASCIMENTO", dataNascimento)
        .value("ADMISSAO", dataAdmissao)
        .value("DEMISSAO", dataDemissao)
        .value("REGIME_CONTRATACAO", regimeDeContratacao.ordinal())
        .value("SUPERIOR_ID", superior.getId())
        .value("DIRETOR_ID", diretor.getId())

        .onGeneratedKey(new GeneratedKeyCallback() {

          @Override
          public void set(ResultSet rs) throws SQLException {
            id = rs.next() ? rs.getInt(1) : 0;
          }
        })

    ;
  }

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getMatricula() {
    return matricula;
  }

  @Override
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  @Override
  public LocalDate getDataAdmissao() {
    return dataAdmissao;
  }

  @Override
  public LocalDate getDataDemissao() {
    return dataDemissao;
  }

  @Override
  public Superior getSuperior() {
    return superior;
  }

  @Override
  public Diretor getDiretor() {
    return diretor;
  }

  @Override
  public Contrato getRegimeDeContratacao() {
    return regimeDeContratacao;
  }

}
