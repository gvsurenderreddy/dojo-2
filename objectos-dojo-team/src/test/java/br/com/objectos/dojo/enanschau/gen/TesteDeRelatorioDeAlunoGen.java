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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.joda.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.ModuloDeTesteObjectosDojo;

import com.google.inject.Inject;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
@Test
@Guice(modules = ModuloDeTesteObjectosDojo.class)
public class TesteDeRelatorioDeAlunoGen {

  @Inject
  private RelatorioDeAlunoGen gen;

  @Inject
  private SqlUnit sqlUnit;

  @BeforeClass
  public void prepararSqlUnit() {
    sqlUnit.loadEntitySet(AlunosFalso.class);

    sqlUnit.truncate(DisciplinasFalso.class);
    sqlUnit.truncate(FaturasFalso.class);
  }

  public void deve_gerar_relatorio_de_aluno() {
    Aluno aluno = AlunosFalso.ALUNO_1;
    LocalDate vencimento = new LocalDate(2013, 8, 9);

    novaFatura(aluno, vencimento);
    novaFatura(aluno, vencimento);

    novaDisciplina(aluno);
    novaDisciplina(aluno);

    RelatorioDeAluno res = gen.gerarDe(aluno);

    assertThat(res.getNomeDoAluno(), equalTo(aluno.getNome()));
    assertThat(res.getFaturasEmAberto().size(), equalTo(2));
    assertThat(res.getDisciplinas().size(), equalTo(2));
  }

  public void deve_gerar_relatorio_de_aluno_sem_faturas_vencidas() {
    Aluno aluno = AlunosFalso.ALUNO_2;
    LocalDate vencimento = new LocalDate()
        .plusDays(30);

    novaFatura(aluno, vencimento);

    novaDisciplina(aluno);
    novaDisciplina(aluno);

    RelatorioDeAluno res = gen.gerarDe(aluno);

    assertThat(res.getNomeDoAluno(), equalTo(aluno.getNome()));
    assertThat(res.getFaturasEmAberto().size(), equalTo(0));
    assertThat(res.getDisciplinas().size(), equalTo(2));
  }

  private void novaDisciplina(Aluno aluno) {
    Disciplina disciplina = new ConstrutorDeDisciplinaFalso()
        .aluno(aluno)
        .nome("Disciplina XPTO")
        .novaInstancia();
    sqlUnit.add(disciplina).insert();
  }
  private void novaFatura(Aluno aluno, LocalDate vencimento) {
    Fatura fatura = new ConstrutorDeFaturaFalso()
        .aluno(aluno)
        .valor(500.5)
        .vencimento(vencimento)
        .novaInstancia();
    sqlUnit.add(fatura).insert();
  }

}