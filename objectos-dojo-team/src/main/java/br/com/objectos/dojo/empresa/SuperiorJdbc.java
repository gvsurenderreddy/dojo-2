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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;

import br.com.objectos.comuns.relational.jdbc.GeneratedKeyCallback;
import br.com.objectos.comuns.relational.jdbc.Insert;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class SuperiorJdbc implements Superior {

  private int id;
  private final String nome;
  private final String matricula;
  private final LocalDate dataNascimento;
  private final LocalDate admissao;
  private final LocalDate demissao;
  private final Diretor diretor;

  public SuperiorJdbc(Construtor construtor) {
    nome = construtor.getNome();
    matricula = construtor.getMatricula();
    dataNascimento = construtor.getDataNascimento();
    admissao = construtor.getAdmissao();
    demissao = construtor.getDemissao();
    diretor = construtor.getDiretor();
  }

  @Override
  public Insert getInsert() {
    return Insert.into("DATABASE.SUPERIOR")

        .value("NOME", nome)
        .value("MATRICULA", matricula)
        .value("DATA_NASCIMENTO", dataNascimento)
        .value("ADMISSAO", admissao)
        .value("DEMISSAO", dataNascimento)
        .value("DIRETOR", diretor.getId())

        .onGeneratedKey(new GeneratedKeyCallback() {

          @Override
          public void set(ResultSet rs) throws SQLException {
            id = rs.next() ? rs.getInt(1) : 0;
          }
        });
  }

  @Override
  public Integer getId() {
    return id;
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
  public LocalDate getAdmissao() {
    return admissao;
  }

  @Override
  public LocalDate getDemissao() {
    return demissao;
  }

  @Override
  public Diretor getDiretor() {
    return diretor;
  }

}