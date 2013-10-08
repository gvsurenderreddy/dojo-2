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
package br.com.objectos.dojo.faculdade;

import org.testng.annotations.BeforeClass;

import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.faculdade.BuscarAluno;
import br.com.objectos.dojo.taguiar.AlunosFalso;

import com.google.inject.Inject;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class TesteDeBuscarAluno {

  @Inject
  private BuscarAluno buscarAluno;

  @Inject
  private SqlUnit sqlUnit;

  @BeforeClass
  public void prepararSqlUnit() {
    sqlUnit.loadEntitySet(AlunosFalso.class);
  }

}