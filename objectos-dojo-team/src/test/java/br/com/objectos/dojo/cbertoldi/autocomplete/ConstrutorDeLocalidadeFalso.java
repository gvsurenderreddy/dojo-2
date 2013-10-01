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
package br.com.objectos.dojo.cbertoldi.autocomplete;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class ConstrutorDeLocalidadeFalso implements Localidade.Construtor {

  private String descricao;

  @Override
  public Localidade novaInstancia() {
    return new LocalidadeJdbc(this);
  }

  public ConstrutorDeLocalidadeFalso descricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  @Override
  public String getDescricao() {
    return descricao;
  }

}