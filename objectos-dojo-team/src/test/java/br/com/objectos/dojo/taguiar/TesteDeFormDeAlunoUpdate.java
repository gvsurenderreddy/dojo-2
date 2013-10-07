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
import static org.hamcrest.Matchers.not;

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
public class TesteDeFormDeAlunoUpdate extends TesteDeIntegracaoWeb {

  private static final String URL = "api/faculdade/crud/curso/direito/aluno";

  @Inject
  private BuscarAluno buscarAluno;

  @Override
  protected void prepararSqlUnit(SqlUnit sqlUnit) {
    sqlUnit.loadEntitySet(UsuariosFalso.class);

    sqlUnit.loadEntitySet(AlunosFalso.class);
  }

  public void put() {
    Usuario usuario = UsuariosFalso.USUARIO_A;

    Aluno aluno = AlunosFalso.ALUNO_1;
    int id = aluno.getId();
    String nome = "Luiz de Souza";

    Aluno antes = buscarAluno.porId(id);
    assertThat(antes.getNome(), not(equalTo(nome)));

    Map<String, Object> form = newHashMap();
    form.put("id", id);
    form.put("nome", nome);

    Map<String, String> cookies = login(usuario);
    WebResponse response = jsonClientOf(URL, cookies).put(form);
    FormResponseJson json = response.to(FormResponseJson.class).using(Json.class);
    assertThat(response.toString(), json.isValid(), is(true));

    Aluno res = buscarAluno.porId(id);
    assertThat(res.getNome(), equalTo(nome));
  }

}