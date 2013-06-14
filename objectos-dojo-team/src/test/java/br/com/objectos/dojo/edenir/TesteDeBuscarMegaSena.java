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
package br.com.objectos.dojo.edenir;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.joda.time.LocalDate;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class TesteDeBuscarMegaSena {

  private final BuscarMegaSena buscar = new BuscarMegaSena();

  public void deve_retornar_primeiro_registro() {
    LocalDate dataSorteio = new LocalDate("1996/03/11");
    int[] dezenas = { 04, 05, 30, 33, 41, 52 };
    MegaSenaPojo prova = new MegaSenaPojo(1, dataSorteio, dezenas);

    MegaSenaPojo res = buscar.primeiroRegistro();

    assertThat(res.getNumeroSorteio(), equalTo(prova.getNumeroSorteio()));
    assertThat(res.getDataSorteio(), equalTo(prova.getDataSorteio()));
    assertThat(res.getDezenas(), equalTo(prova.getDezenas()));
  }

}