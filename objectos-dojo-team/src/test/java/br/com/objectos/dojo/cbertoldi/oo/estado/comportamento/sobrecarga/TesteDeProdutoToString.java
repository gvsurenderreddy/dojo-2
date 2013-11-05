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

import br.com.objectos.dojo.cbertoldi.oo.estado.comportamento.sobrecarga.Produto;
import br.com.objectos.dojo.cbertoldi.oo.estado.comportamento.sobrecarga.ProdutoPojo;
import br.com.objectos.dojo.cbertoldi.oo.estado.comportamento.sobrecarga.ProdutoToString;
import br.com.objectos.dojo.cbertoldi.oo.estado.comportamento.sobrecarga.TipoDeCategoria;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
@Test
public class TesteDeProdutoToString {

  private final ProdutoToString produtoToString = new ProdutoToString();

  public void lista_produto() {
    int id = 1;
    String codigo = "PD1";
    String descricao = "Produto1";
    TipoDeCategoria categoria = TipoDeCategoria.CATEGORIA_1;
    double valor = 100.99;

    Produto produto = new ProdutoPojo(id, codigo, descricao, categoria, valor);
    List<String> res = produtoToString.listar(produto);

    assertThat(res.size(), equalTo(4));
    assertThat(res.get(0), equalTo("CODIGO = PD1"));
    assertThat(res.get(1), equalTo("DESCRICAO = Produto1"));
    assertThat(res.get(2), equalTo("CATEGORIA = CATEGORIA_1"));
    assertThat(res.get(3), equalTo("VALOR = R$ 100.99"));
  }

}