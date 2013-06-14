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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
@Test
public class TesteDeBuscarMegaSena {

  private final BuscarMegaSena buscar = new BuscarMegaSena();

  public void deve_retornar_primeiro_registro() {
    LocalDate dataSorteio = new LocalDate("1996-03-11");
    String resultado = "04 05 30 33 41 52";
    MegaSenaPojo prova = new MegaSenaPojo(1, dataSorteio, resultado);

    List<MegaSena> res = buscar.primeiroRegistro();

    MegaSena r01 = res.get(0);
    assertThat(r01.getNumeroSorteio(), equalTo(prova.getNumeroSorteio()));
    assertThat(r01.getDataSorteio(), equalTo(prova.getDataSorteio()));
    assertThat(r01.getResultado(), equalTo(prova.getResultado()));
  }

}