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
package br.com.objectos.dojo.enanschau.gen;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public enum DisciplinaStatus {

  TODAS("Todos"),
  MATRICULADO("Matriculado") {
    @Override
    public Boolean matriculadoValue() {
      return Boolean.TRUE;
    }

    @Override
    public Boolean depedenciaValue() {
      return Boolean.FALSE;
    }
  },

  DEPENDENCIA("Com dependência") {

    @Override
    public Boolean matriculadoValue() {
      return Boolean.FALSE;
    }

    @Override
    public Boolean depedenciaValue() {
      return Boolean.TRUE;
    }

  };

  private final String descricao;

  private DisciplinaStatus(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public Boolean matriculadoValue() {
    return null;
  }

  public Boolean depedenciaValue() {
    return null;
  }

}