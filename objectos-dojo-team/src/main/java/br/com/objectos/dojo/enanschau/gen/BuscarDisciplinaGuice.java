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
package br.com.objectos.dojo.enanschau.gen;

import java.util.List;

import br.com.objectos.comuns.relational.jdbc.NativeSql;
import br.com.objectos.comuns.relational.search.Page;
import br.com.objectos.comuns.sitebricks.RequestWrapper;
import br.com.objectos.comuns.sitebricks.relational.PageList;
import br.com.objectos.comuns.sitebricks.relational.Pager;
import br.com.objectos.comuns.sitebricks.relational.PagerLoader;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
class BuscarDisciplinaGuice implements BuscarDisciplina {

  private final Provider<NativeSql> sqlProvider;

  @Inject
  public BuscarDisciplinaGuice(Provider<NativeSql> sqlProvider) {
    this.sqlProvider = sqlProvider;
  }

  @Override
  public List<Disciplina> porAluno(Aluno aluno) {
    return newSelect("*")

        .add("where DISCIPLINA.ALUNO_ID = ?").param(aluno.getId())
        .list();
  }

  @Override
  public PageList<Disciplina> pagerPorAlunoKeyKey(AlunoKey alunoKey, RequestWrapper wrapper) {
    Page page = wrapper.getPage();

    List<Disciplina> rows = newPager("*", alunoKey, wrapper)
        .listPage(page);

    Pager pager = newPager("count(*)", alunoKey, wrapper)
        .andLoadWith(new PagerLoader(wrapper))
        .single();

    return new PageList<Disciplina>(rows, pager);
  }

  private NativeSql newPager(String what, AlunoKey alunoKey, RequestWrapper wrapper) {
    String nome = wrapper.param("nome");

    return newSelect(what)

        .add("where DISCIPLINA.ALUNO_ID = ?").param(alunoKey.getId())

        .addIf("and ALUNO.NOME like concat('%', ?, '%')").paramNotNull(nome)

        .add("order by")
        .add("ALUNO.NOME");
  }

  private NativeSql newSelect(String what) {
    return sqlProvider.get()

        .add("select %s", what)
        .add("from FACULDADE.DISCPLINA")

        .add("join FACULDADE.ALUNO")
        .add("on DISCPLINA.ALUNO_ID = ALUNO.ID")

        .andLoadWith(new DisciplinaLoader());
  }

}