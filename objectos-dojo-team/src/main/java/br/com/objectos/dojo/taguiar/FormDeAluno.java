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

import br.com.objectos.comuns.relational.jdbc.EntityMapping;
import br.com.objectos.comuns.relational.jdbc.GeneratedKey;
import br.com.objectos.comuns.relational.jdbc.GeneratedKeyListener;
import br.com.objectos.comuns.relational.jdbc.Relational;
import br.com.objectos.comuns.sitebricks.form.Forms;
import br.com.objectos.comuns.sitebricks.json.EntityJson;

import com.google.inject.Inject;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.http.Put;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class FormDeAluno {

  private final Forms forms;

  @Inject
  public FormDeAluno(Forms forms) {
    this.forms = forms;
  }

  @Post
  public Reply<?> post() {
    return reply();
  }

  @Put
  public Reply<?> put() {
    return reply();
  }

  private Reply<?> reply() {
    return forms.of(PojoJson.class)

        .reply();
  }

  private static class PojoJson implements EntityJson {

    int id;
    String nome;
    String matricula;
    int curso;

    @Override
    public EntityMapping toMapping() {
      return Relational.table("BANCO.ALUNO")

          .id("ID", id, new GeneratedKeyListener() {
            @Override
            public void set(GeneratedKey key) {
              id = key.getInt();
            }
          })

          .col("NOME", nome)
          .col("MATRICULA", matricula)
          .col("CURSO_ID", curso);
    }

  }

}