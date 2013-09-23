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

import static br.com.objectos.dojo.enanschau.gen.DisciplinasFalso.DISCIPLINA_1_ALUNO_1;
import static br.com.objectos.dojo.enanschau.gen.DisciplinasFalso.DISCIPLINA_2_ALUNO_1;
import static com.google.common.collect.Lists.transform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.sitebricks.FakeRequestWrapper;
import br.com.objectos.comuns.sitebricks.relational.PageList;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.ModuloDeTesteObjectosDojo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
@Test
@Guice(modules = ModuloDeTesteObjectosDojo.class)
public class TesteDeBuscarDisciplina {

  @Inject
  private BuscarDisciplina buscarDisciplina;

  @Inject
  private SqlUnit sqlUnit;

  @BeforeClass
  public void prepararSqlUnit() {
    sqlUnit.loadEntitySet(AlunosFalso.class);
    sqlUnit.truncate(DisciplinasFalso.class);
  }

  public void page_por_aluno_key() {
    Aluno aluno = AlunosFalso.ALUNO_1;
    AlunoKey alunoKey = new AlunoKey(aluno);

    List<Disciplina> contra = ImmutableList.of(DISCIPLINA_1_ALUNO_1, DISCIPLINA_2_ALUNO_1);
    List<String> prova = Lists.transform(contra, new DisciplinaToString());

    FakeRequestWrapper wrapper = new FakeRequestWrapper();
    wrapper.put("nome", "João");

    PageList<Disciplina> page = buscarDisciplina.pagerPorAlunoKeyKey(alunoKey, wrapper);
    List<Disciplina> list = page.getRows();
    List<String> res = transform(list, new DisciplinaToString());

    assertThat(res.size(), equalTo(2));
    assertThat(res, equalTo(prova));
  }

}