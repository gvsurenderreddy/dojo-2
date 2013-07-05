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
package br.com.objectos.dojo.cpelissari_estagiario;

import org.joda.time.LocalDate;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
public class EstagiarioPojo implements Estagiario {

  private final String nome;
  private final LocalDate dataDeAdm;
  private final double salario;

  public EstagiarioPojo(Construtor construtor) {
    nome = construtor.getNome();
    dataDeAdm = construtor.getDataDeAdm();
    salario = construtor.getSalario();
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public LocalDate getDataDeAdm() {
    return dataDeAdm;
  }

  @Override
  public double getSalario() {
    return salario;
  }

}
