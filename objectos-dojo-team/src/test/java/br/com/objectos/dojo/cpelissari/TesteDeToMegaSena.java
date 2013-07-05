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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
@Test
public class TesteDeToMegaSena {

  private final ToMegaSena megaSena = new ToMegaSenaImpl();

  public void deve_transformar_array_em_objeto() {
    String[] linha = new String[] { "123", "05/07/2013", "01 02 03 04 05 06" };
    MegaSena res = megaSena.of(linha);

    assertThat(res.getNumeroConcurso(), equalTo(123));
    assertThat(res.getDataSorteio(), equalTo(new LocalDate(2013, 7, 5)));
    assertThat(res.getResultado(), equalTo("01 02 03 04 05 06"));
  }
}