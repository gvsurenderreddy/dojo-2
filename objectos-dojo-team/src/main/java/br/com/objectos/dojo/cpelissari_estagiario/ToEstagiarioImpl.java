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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
public class ToEstagiarioImpl implements ToEstagiario {

  @Override
  public Estagiario of(String[] linha) {
    return new Construtor(linha).novaInstancia();
  }

  private class Construtor implements Estagiario.Construtor {

    private final String[] linhas;

    public Construtor(String[] linhas) {
      this.linhas = linhas;
    }

    @Override
    public Estagiario novaInstancia() {
      return new EstagiarioPojo(this);
    }

    @Override
    public String getNome() {
      String nome = linhas[0];
      return nome;
    }

    @Override
    public LocalDate getDataDeAdm() {
      String data = linhas[1];
      DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
      LocalDate _data = dtf.parseLocalDate(data);
      return _data;
    }

    @Override
    public double getSalario() {
      String salario = linhas[2];
      return Double.parseDouble(salario);
    }

  }

}