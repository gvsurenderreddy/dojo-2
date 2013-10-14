/*
* Copyright 2011 Objectos, Fábrica de Software LTDA.
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
package br.com.objectos.dojo.empresa;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import br.com.objectos.comuns.relational.jdbc.Insert;

/**
 * @author caio.petreanu@objectos.com.br (Caio C. Petreanu)
 */
public class SuperiorVazio implements Superior {

  @Override
  public Integer getId() {
    throw new UnsupportedOperationException();
  }
  @Override
  public String getNome() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getMatricula() {
    throw new UnsupportedOperationException();
  }

  @Override
  public LocalDate getDataNascimento() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DateTime getAdmissao() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DateTime getDemissao() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Diretor getDiretor() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Insert getInsert() {
    throw new UnsupportedOperationException();
  }

}
