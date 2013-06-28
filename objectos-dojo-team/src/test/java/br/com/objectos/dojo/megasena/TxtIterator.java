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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class TxtIterator implements Iterator<String> {

  private final BufferedReader br;
  private String line;

  public TxtIterator(BufferedReader br) {
    this.br = br;
    line = tryReadLine();
  }

  @Override
  public boolean hasNext() {
    return line != null;
  }

  @Override
  public String next() {
    String res = line;

    line = tryReadLine();

    return res;
  }

  @Override
  public void remove() {
  }

  private String tryReadLine() {
    try {
      return br.readLine();
    } catch (IOException e) {
      return "";
    }
  }

}