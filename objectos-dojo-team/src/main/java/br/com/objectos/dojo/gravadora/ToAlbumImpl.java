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

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class ToAlbumImpl implements ToAlbum {

  @Override
  public Album of(String[] array) {
    return new Construtor(array).novaInstancia();
  }

  private class Construtor implements br.com.objectos.dojo.gravadora.Album.Construtor {

    private final String[] linhas;

    public Construtor(String[] linhas) {
      this.linhas = linhas;
    }

    @Override
    public Album novaInstancia() {
      return new AlbumPojo(this);
    }

    @Override
    public int getSerie() {
      String serie = linhas[0];
      return Integer.parseInt(serie);
    }

    @Override
    public LocalDate getdataLancamento() {
      String data = linhas[1];
      DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
      LocalDate _data = dtf.parseLocalDate(data);
      return _data;
    }

    @Override
    public String getNome() {
      String nome = linhas[2];
      return nome;
    }

    @Override
    public String getNomeArtista() {
      String artista = linhas[3];
      return artista;
    }

  }

}