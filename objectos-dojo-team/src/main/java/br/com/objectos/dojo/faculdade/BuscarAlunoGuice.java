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

import java.util.List;

import org.joda.time.DateTime;

import br.com.objectos.comuns.sitebricks.RequestWrapper;
import br.com.objectos.comuns.sitebricks.relational.PageList;
import br.com.objectos.dojo.taguiar.Curso;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
public class BuscarAlunoGuice implements BuscarAluno {

  @Override
  public List<Aluno> porCurso(Curso curso) {
    return null;
  }

  @Override
  public Aluno porId(int id) {
    return null;
  }

  @Override
  public PageList<Aluno> pagePorProuni(DateTime data, RequestWrapper wrapper) {
    return null;
  }

}