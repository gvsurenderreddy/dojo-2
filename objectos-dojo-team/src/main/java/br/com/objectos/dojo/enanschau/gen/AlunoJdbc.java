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
package br.com.objectos.dojo.enanschau.gen;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.objectos.comuns.relational.jdbc.GeneratedKeyCallback;
import br.com.objectos.comuns.relational.jdbc.Insert;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class AlunoJdbc implements Aluno {

  private int id;
  private final String nome;
  private final String matricula;

  public AlunoJdbc(Construtor construtor) {
    nome = construtor.getNome();
    matricula = construtor.getMatricula();
  }

  @Override
  public Insert getInsert() {
    return Insert.into("DOJO.ALUNO")

        .value("NOME", nome)
        .value("MATRICULA", matricula)

        .onGeneratedKey(new GeneratedKeyCallback() {
          @Override
          public void set(ResultSet rs) throws SQLException {
            id = rs.next() ? rs.getInt(1) : 0;
          }
        });
  }

  void setId(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
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

}