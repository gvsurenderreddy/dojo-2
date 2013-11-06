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
package br.com.objectos.dojo.cbertoldi.oo.estado.comportamento.sobrecarga;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class CDPojo extends ProdutoPojo implements CD {

  private final String album;

  private final String artista;

  private final String genero;

  CDPojo(int id,
         String codigo,
         String descricao,
         TipoDeCategoria categoria,
         double valor,
         String album,
         String artista,
         String genero) {
    super(id, codigo, descricao, categoria, valor);
    this.album = album;
    this.artista = artista;
    this.genero = genero;
  }

  @Override
  public String getAlbum() {
    return album;
  }

  @Override
  public String getArtista() {
    return artista;
  }

  @Override
  public String getGenero() {
    return genero;
  }

}