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
package br.com.objectos.dojo.taguiar;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class ConstrutorDeCursoFalso implements Curso.Construtor {

  private String nome;
  private String codigo;

  @Override
  public Curso novaInstancia() {
    return new CursoJdbc(this);
  }

  public ConstrutorDeCursoFalso nome(String nome) {
    this.nome = nome;
    return this;
  }

  public ConstrutorDeCursoFalso codigo(String codigo) {
    this.codigo = codigo;
    return this;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getCodigo() {
    return codigo;
  }

}