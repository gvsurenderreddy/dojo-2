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

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
@Test
public class TesteDeLerTxt {

  private final LerArquivoTexto ler = new LerArquivoTextoImpl();

  public void deve_ler_txt() throws URISyntaxException {
    URL url = Resources.getResource(getClass(), "albuns.txt");
    URI uri = url.toURI();
    File file = new File(uri);

    String txt = ler.lerTxt(file);
    String sep = System.getProperty("line.separator");

    String[] array = txt.split(sep);
    List<String> res = ImmutableList.copyOf(array);

    assertThat(res.size(), equalTo(3));
    assertThat(res.get(0), equalTo("01;15/07/2011;Come to the well;Casting Crowns"));
    assertThat(res.get(1), equalTo("02;12/05/1987;20 anos;Catedral"));
    assertThat(res.get(2), equalTo("03;20/08/2002;O tempo;Oficina G3"));
  }

}