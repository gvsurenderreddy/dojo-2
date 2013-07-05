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

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
@Test
public class TesteDeToArrayDeString {

  private final ToArrayDeString array = new ToArrayDeStringImpl();

  public void deve_transformar_string_em_array_de_string() {
    String linha = "01;15/07/2011;Come to the well;Casting Crowns";

    String[] arrayDeString = array.gerarArray(linha);
    List<String> res = ImmutableList.copyOf(arrayDeString);

    assertThat(res.get(0), equalTo("01"));
    assertThat(res.get(1), equalTo("15/07/2011"));
    assertThat(res.get(2), equalTo("Come to the well"));
    assertThat(res.get(3), equalTo("Casting Crowns"));
  }

}