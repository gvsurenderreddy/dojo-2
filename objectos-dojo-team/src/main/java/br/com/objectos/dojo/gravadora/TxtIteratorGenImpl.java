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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class TxtIteratorGenImpl implements TxtIteratorGen {

  @Override
  public TextIterator gerarDe(File file) {
    try {
      return new TxtDeIteratorImpl(file);
    } catch (FileNotFoundException e) {
      return null;
    }
  }

  private static class TxtDeIteratorImpl implements TextIterator {

    private final BufferedReader bufferedReader;

    public TxtDeIteratorImpl(File file) throws FileNotFoundException {
      FileReader fileReader = new FileReader(file);
      bufferedReader = new BufferedReader(fileReader);
    }

    @Override
    public boolean hasNext() {
      try {
        return bufferedReader.ready();
      } catch (IOException e) {
        return false;
      }
    }

    @Override
    public String next() {
      try {
        return bufferedReader.readLine();
      } catch (IOException e) {
        return "";
      }
    }

    @Override
    public void remove() {
    }

  }

}