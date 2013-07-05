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
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.testng.annotations.Test;

import com.google.common.io.Resources;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
@Test
public class TesteDeTxtIteratorGen {

  private final TxtIteratorGen gen = new TxtIteratorGenImpl();

  public void deve_iterar_txt() throws URISyntaxException {
    URL url = Resources.getResource(getClass(), "/mega/Ler.txt");
    URI uri = url.toURI();
    File file = new File(uri);

    TxtIterator res = gen.gerarDe(file);

    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.next(), equalTo("Teste para arquivo de texto"));
    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.next(), equalTo("contém caracteres como ç"));
    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.hasNext(), is(true));
    assertThat(res.next(), equalTo("data: 05/07/2013"));
    assertThat(res.hasNext(), is(false));
    assertThat(res.hasNext(), is(false));
    assertThat(res.hasNext(), is(false));
  }

}