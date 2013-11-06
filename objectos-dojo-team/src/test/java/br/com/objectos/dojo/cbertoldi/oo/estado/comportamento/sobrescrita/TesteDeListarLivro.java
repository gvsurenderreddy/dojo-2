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
package br.com.objectos.dojo.cbertoldi.oo.estado.comportamento.sobrescrita;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.joda.time.Years;
import org.testng.annotations.Test;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
@Test
public class TesteDeListarLivro {

  public void lista_produto() {
    int id = 1;
    String codigo = "L";
    String descricao = "Livro2";
    TipoDeCategoria categoria = TipoDeCategoria.CATEGORIA_2;
    double valor = 54.99;
    String autor = "Autor1";
    String editora = "Editora1";
    Years ano = Years.years(2000);

    Livro livro = new LivroPojo(id, codigo, descricao, categoria, valor, autor, editora, ano);
    List<String> res = livro.list();

    assertThat(res.size(), equalTo(7));
    assertThat(res.get(0), equalTo("CODIGO = L"));
    assertThat(res.get(1), equalTo("DESCRICAO = Livro2"));
    assertThat(res.get(2), equalTo("CATEGORIA = CATEGORIA_2"));
    assertThat(res.get(3), equalTo("VALOR = R$ 54.99"));
    assertThat(res.get(4), equalTo("AUTOR = Autor1"));
    assertThat(res.get(5), equalTo("EDITORA = Editora1"));
    assertThat(res.get(6), equalTo("ANO = 2000"));
  }

}