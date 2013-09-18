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
public class ConstrutorDeUsuarioFalso implements Usuario.Construtor {

  private String login;
  private String senha;

  @Override
  public Usuario novaInstancia() {
    return new UsuarioJdbc(this);
  }

  public ConstrutorDeUsuarioFalso login(String login) {
    this.login = login;
    return this;
  }

  public ConstrutorDeUsuarioFalso senha(String senha) {
    this.senha = senha;
    return this;
  }

  @Override
  public String getLogin() {
    return login;
  }

  @Override
  public String getSenha() {
    return senha;
  }

}