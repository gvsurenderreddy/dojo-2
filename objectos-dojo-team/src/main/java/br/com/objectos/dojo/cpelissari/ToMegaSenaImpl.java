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
package br.com.objectos.dojo.cpelissari;

import java.util.Iterator;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
public class ToMegaSenaImpl implements ToMegaSena {

  @Override
  public MegaSena of(String[] linha) {
    return new Construtor(linha).novaInstancia();
  }

  @Override
  public Iterator<MegaSena> transforme(Iterator<String[]> colunas) {
    return Iterators.transform(colunas, new ToImpl());
  }

  private class ToImpl implements Function<String[], MegaSena> {
    @Override
    public MegaSena apply(String[] input) {
      return of(input);
    }
  }

  private class Construtor implements MegaSena.Construtor {

    private final String[] linha;

    public Construtor(String[] linha) {
      this.linha = linha;
    }

    @Override
    public MegaSena novaInstancia() {
      return new MegaSenaPojo(this);
    }

    @Override
    public int getNumeroConcurso() {
      String col = linha[0];
      return Integer.parseInt(col);
    }

    @Override
    public LocalDate getDataSorteio() {
      String data = linha[1];
      DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
      return formatter.parseLocalDate(data);
    }

    @Override
    public String getResultado() {
      return linha[2];
    }

  }

}