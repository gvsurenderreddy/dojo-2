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
package br.com.objectos.dojo.asilva;

import br.com.objectos.dojo.empresa.Superior;

import com.google.common.base.Objects;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class SuperiorKey {

  private final int id;

  public SuperiorKey(int id) {
    this.id = id;
  }

  public SuperiorKey(Superior superior) {
    this.id = superior.getId();
  }

  public static SuperiorKey of(Superior superior) {
    return new SuperiorKey(superior);
  }

  public int getId() {
    return id;
  }

  @Override
  public final int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (obj instanceof SuperiorKey) {
      final SuperiorKey that = (SuperiorKey) obj;
      return this.id == that.id;
    } else {
      return false;
    }
  }

}