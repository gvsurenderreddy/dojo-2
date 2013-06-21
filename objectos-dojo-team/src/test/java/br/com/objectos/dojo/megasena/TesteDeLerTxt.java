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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.testng.annotations.Test;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
@Test
public class TesteDeLerTxt {

  private final LerTxt txt = new LerTxt();

  public void deve_ler_txt() throws IOException {
    InputStream is = new FileInputStream(
        "/home/asilva/kdo/projetos/objectos-dojo/objectos-dojo-team/src/test/resources/mega-sena.txt");
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);

    String s = br.readLine();

    while (s != null) {
      System.out.println(s);
      s = br.readLine();
    }
    br.close();
  }

  public void deve_ler_primeira_linha() throws IOException {
    String linha = "1;11/03/1996;04 05 30 33 41 52";

    File file = new File(
        "/objectos-dojo/objectos-dojo-team/src/test/resources/mega-sena.txt");

    String res = txt.lerDe(file);

    assertThat(linha, equalTo(res));
  }

  public void deve_ler_txt_2() throws IOException {
    File file = new File(
        "/home/eanschau/kdo/projetos/objectos-dojo/objectos-dojo-team/src/test/resources/mega-sena.txt");

    Iterator<String[]> res = txt.getArrayDeString(file);

  }

}