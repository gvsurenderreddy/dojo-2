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
package br.com.objectos.dojo.megasena;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Iterator;
import java.util.List;

import org.joda.time.LocalDate;

import com.google.common.primitives.Ints;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
class MegaSenaLoader {

  private final LerTxt lerTxt = new LerTxt();

  public List<MegaSena> carregarTodos() {
    List<MegaSena> list = newArrayList();

    Iterator<String[]> arrayDeString = lerTxt.getArrayDeString();
    while (arrayDeString.hasNext()) {
      MegaSena megaSena = new Loader(arrayDeString.next()).novaInstancia();
      list.add(megaSena);
    }
    return list;
  }

  private class Loader implements MegaSena.Construtor {

    private final String[] resultados;

    public Loader(String[] resultados) {
      this.resultados = resultados;
    }

    @Override
    public MegaSena novaInstancia() {
      return new MegaSenaPojo(getNumeroSorteio(), getDataSorteio(), getResultado());
    }

    @Override
    public int getNumeroSorteio() {
      String _numeroSorteio = resultados[0];
      int val = Ints.tryParse(_numeroSorteio);
      return val;
    }

    @Override
    public LocalDate getDataSorteio() {
      String data = resultados[1];
      return new LocalDate(data);
    }

    @Override
    public String getResultado() {
      String resultado = resultados[2];
      return resultado;
    }

  }

}