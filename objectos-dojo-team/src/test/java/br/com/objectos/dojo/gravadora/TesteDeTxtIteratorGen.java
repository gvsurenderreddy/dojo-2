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
package br.com.objectos.dojo.gravadora;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
@Test
public class TesteDeTxtIteratorGen {

  private final TxtIteratorGen gen = new TxtIteratorGenImpl();

  private final AbrirTxt abrirTxt = new AbrirTxt();

  public void deve_iterar_txt() throws URISyntaxException {
    String url = "/br/com/objectos/dojo/gravadora/albuns.txt";
    File file = abrirTxt.getFile(url);

    TextIterator res = gen.gerarDe(file);

    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.next(), equalTo("01;15/07/2011;Come to the well;Casting Crowns"));

    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.next(), equalTo("02;12/05/1987;20 anos;Catedral"));

    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.next(), equalTo("03;20/08/2002;O tempo;Oficina G3"));

    assertThat(res.hasNext(), is(false));
    assertThat(res.hasNext(), is(false));
    assertThat(res.hasNext(), is(false));
  }

}