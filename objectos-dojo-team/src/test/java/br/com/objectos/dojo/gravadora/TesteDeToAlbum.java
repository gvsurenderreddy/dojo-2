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

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
@Test
public class TesteDeToAlbum {

  private final ToAlbum album = new ToAlbumImpl();

  public void deve_tranformar_array_de_string_em_albuns() {
    String[] array = new String[] { "01", "15/07/2011", "Come to the well", "Casting Crowns" };

    Album res = album.of(array);

    assertThat(res.getSerie(), equalTo(1));
    assertThat(res.getdataLancamento(), equalTo(new LocalDate(2011, 7, 15)));
    assertThat(res.getNome(), equalTo("Come to the well"));
    assertThat(res.getNomeArtista(), equalTo("Casting Crowns"));
  }

}