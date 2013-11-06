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
package br.com.objectos.dojo.cbertoldi.oo.estado.comportamento.sobrecarga;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.Test;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
@Test
public class TesteDeCDToString {

  int id = 1;
  String codigo = "CD";
  String descricao = "CD1";
  TipoDeCategoria categoria = TipoDeCategoria.CATEGORIA_3;
  double valor = 25.99;
  String album = "Album1";
  String artista = "Artista1";
  String genero = "Genero1";

  CD cd = new CDPojo(id, codigo, descricao, categoria, valor, album, artista, genero);
  private final CDToString cdToString = new CDToString();

  public void lista_produto() {
    List<String> res = cdToString.listar(cd);

    assertThat(res.size(), equalTo(4));
    assertThat(res.get(0), equalTo("CODIGO = CD"));
    assertThat(res.get(1), equalTo("DESCRICAO = CD1"));
    assertThat(res.get(2), equalTo("CATEGORIA = CATEGORIA_3"));
    assertThat(res.get(3), equalTo("VALOR = R$ 25.99"));
  }

  public void cast_exption() {
    CD _cd = cdToString.to(cd);

    assertThat(cd.toString(), equalTo(_cd.toString()));
  }

}