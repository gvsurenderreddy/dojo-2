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

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
class ProdutoPojo implements Produto {

  private final int id;
  private final String codigo;
  private final String descricao;
  private final TipoDeCategoria categoria;
  private final double valor;

  ProdutoPojo(int id,
              String codigo,
              String descricao,
              TipoDeCategoria categoria,
              double valor) {
    this.id = id;
    this.codigo = codigo;
    this.descricao = descricao;
    this.categoria = categoria;
    this.valor = valor;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public double getValor() {
    return valor;
  }

  @Override
  public String getCodigo() {
    return codigo;
  }

  @Override
  public String getDescricao() {
    return descricao;
  }

  @Override
  public TipoDeCategoria getCategoria() {
    return categoria;
  }

  @Override
  public List<String> list() {
    List<String> list = newArrayList();

    Double _valor = new Double(valor);

    list.add(String.format("CODIGO = %s", codigo));
    list.add(String.format("DESCRICAO = %s", descricao));
    list.add(String.format("CATEGORIA = %s", categoria));
    list.add(String.format("VALOR = R$ %s", _valor));

    return list;
  }

}