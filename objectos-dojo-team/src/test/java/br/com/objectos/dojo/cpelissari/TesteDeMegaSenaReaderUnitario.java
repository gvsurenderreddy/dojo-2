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
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.Iterators;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
@Test
public class TesteDeMegaSenaReaderUnitario {

  private MegaSenaReader reader;

  private Assert asserts;

  @BeforeClass
  public void prepararReader() {
    TxtIteratorGen txtIteratorGen = new TxtIteratorGenFalso();
    ToArrayDeString toArrayString = new ToArrayStringFalso();
    ToMegaSena toMegaSena = new ToMegaSenaFalso();

    reader = new MegaSenaReaderImpl(txtIteratorGen, toArrayString, toMegaSena);
  }

  @BeforeMethod
  public void prepararTest() {
    asserts = new Assert();
  }

  public void deve_gerar_iterator_mega_sena() throws URISyntaxException {
    File arquivo = TxtsFalso.getFile("/mega/MegaSena.txt");

    Iterator<MegaSena> iter = reader.read(arquivo);

    assertThat(iter, notNullValue());

    assertThat(asserts.fileRecebido, equalTo(arquivo));
    assertThat(asserts.toArrayStringOk, equalTo(true));
    assertThat(asserts.toMegaSenaOk, equalTo(true));
  }

  private class Assert {
    File fileRecebido;
    boolean toArrayStringOk;
    boolean toMegaSenaOk;
  }

  private class TxtIteratorGenFalso implements TxtIteratorGen {
    @Override
    public TxtIterator gerarDe(File file) {
      asserts.fileRecebido = file;
      return new TxtIteratorVazio();
    }
  }

  private class ToArrayStringFalso implements ToArrayDeString {
    @Override
    public String[] toArray(String linha) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<String[]> transform(Iterator<String> linhas) {
      asserts.toArrayStringOk = true;
      return Iterators.emptyIterator();
    }
  }

  private class ToMegaSenaFalso implements ToMegaSena {
    @Override
    public MegaSena of(String[] linha) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<MegaSena> transforme(Iterator<String[]> colunas) {
      asserts.toMegaSenaOk = true;
      return Iterators.emptyIterator();
    }
  }

  private class TxtIteratorVazio implements TxtIterator {

    private final Iterator<String> delegate = Iterators.emptyIterator();

    @Override
    public boolean hasNext() {
      return delegate.hasNext();
    }

    @Override
    public String next() {
      return delegate.next();
    }

    @Override
    public void remove() {
      delegate.remove();
    }
  }

}