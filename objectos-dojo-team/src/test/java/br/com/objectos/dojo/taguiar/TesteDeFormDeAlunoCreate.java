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
package br.com.objectos.dojo.taguiar;

import static com.google.common.collect.Maps.newHashMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import br.com.objectos.comuns.sitebricks.form.FormResponseJson;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.faculdade.Aluno;

import com.google.inject.Inject;
import com.google.sitebricks.client.WebResponse;
import com.google.sitebricks.client.transport.Json;

/**
 * @author tiago.aguiar@objectos.com.br (Tiago Aguiar)
 */
@Test
public class TesteDeFormDeAlunoCreate extends TesteDeIntegracaoWeb {

  public static final String URL = "api/faculdade/crud/curso/direito/aluno";

  @Inject
  private BuscarAluno buscarAluno;

  @Override
  protected void prepararSqlUnit(SqlUnit sqlUnit) {
    sqlUnit.loadEntitySet(UsuariosFalso.class);

    sqlUnit.loadEntitySet(AlunosFalso.class);
  }

  public void post() {
    Usuario usuario = UsuariosFalso.USUARIO_A;
    Curso curso = CursosFalso.CURSO_B;

    String nome = "Robson de Souza";
    String matricula = "20120001";

    List<Aluno> alunos = buscarAluno.porCurso(curso);
    assertThat(alunos.size(), equalTo(0));

    Map<String, Object> form = newHashMap();
    form.put("nome", nome);
    form.put("matricula", matricula);
    form.put("curso", curso.getId());

    Map<String, String> cookies = login(usuario);
    WebResponse response = jsonClientOf(URL, cookies).post(form);
    FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
    assertThat(json.toString(), json.isValid(), is(true));

    alunos = buscarAluno.porCurso(curso);
    assertThat(alunos.size(), equalTo(1));

    Aluno res = alunos.get(0);
    assertThat(res.getNome(), equalTo(nome));
    assertThat(res.getMatricula(), equalTo(matricula));
    assertThat(res.getCurso().getId(), equalTo(curso.getId()));
    assertThat(res.getCurso(), equalTo(curso));
  }

}
