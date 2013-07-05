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
package br.com.objectos.dojo.cpelissari_estagiario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
public class TxtIteratorGenImpl implements TxtIteratorGen {

  @Override
  public TxtIterator gerarDe(File arquivo) {
    return null;

  }

  public class TxtIteratorImpl implements TxtIterator {

    private final BufferedReader reader;

    public TxtIteratorImpl(File arquivo) throws FileNotFoundException {
      reader = Files.newReader(arquivo, Charsets.UTF_8);
    }

    @Override
    public boolean hasNext() {
      try {
        return reader.ready();
      } catch (IOException e) {
        return false;
      }
    }

    @Override
    public String next() {
      try {
        return reader.readLine();
      } catch (IOException e) {
        return " ";
      }
    }

    @Override
    public void remove() {
    }

  }

}