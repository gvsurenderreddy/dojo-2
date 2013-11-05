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

import java.util.List;

import org.joda.time.Years;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
class LivroToString extends ProdutoToString {

  public List<String> listar(Livro livro) {
    List<String> list = super.listar(livro);

    Years ano = livro.getAno();

    list.add(String.format("AUTOR = %s", livro.getAutor()));
    list.add(String.format("EDITORA = %s", livro.getEditora()));
    list.add(String.format("ANO = %d", ano.getYears()));

    return list;
  }

}