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
package br.com.objectos.dojo.cbertoldi.oo;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
class ProdutoToString {

  public List<String> listar(Produto produto) {

    List<String> list = newArrayList();

    TipoDeCategoria categoria = produto.getCategoria();
    list.add(String.format("CODIGO = %s", produto.getCodigo()));
    list.add(String.format("DESCRICAO = %s", produto.getDescricao()));
    list.add(String.format("CATEGORIA = %s", categoria.toString()));
    list.add(String.format("VALOR = R$ %d", produto.getValor()));

    return list;
  }

}