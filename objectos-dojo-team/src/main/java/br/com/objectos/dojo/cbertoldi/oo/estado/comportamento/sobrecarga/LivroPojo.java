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

import org.joda.time.Years;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class LivroPojo extends ProdutoPojo implements Livro {

  private final String autor;

  private final String editora;

  private final Years ano;

  LivroPojo(int id,
            String codigo,
            String descricao,
            TipoDeCategoria categoria,
            double valor,
            String autor,
            String editora,
            Years ano) {
    super(id, codigo, descricao, categoria, valor);
    this.autor = autor;
    this.editora = editora;
    this.ano = ano;
  }

  @Override
  public String getAutor() {
    return autor;
  }

  @Override
  public String getEditora() {
    return editora;
  }

  @Override
  public Years getAno() {
    return ano;
  }

}