/*
 * Copyright 2012 Objectos, Fábrica de Software LTDA.
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

import static br.com.objectos.dojo.empresa.FuncionariosFalso.FUNCIONARIO_1;
import static br.com.objectos.dojo.empresa.FuncionariosFalso.FUNCIONARIO_2;
import static br.com.objectos.dojo.empresa.FuncionariosFalso.FUNCIONARIO_3;
import static com.google.common.collect.Lists.transform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.ModuloDeTesteObjectosDojo;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

/**
 * @author caio.petreanu@objectos.com.br (Caio Petreanu)
 */
@Test
@Guice(modules = { ModuloDeTesteObjectosDojo.class })
public class TesteDeBuscarFuncionario {

  @Inject
  private BuscarFuncionario buscarFuncionario;

  @Inject
  private SqlUnit sqlUnit;

  @BeforeClass
  public void prepararSqlUnit() {
    sqlUnit.loadEntitySet(DiretoresFalso.class);
    sqlUnit.loadEntitySet(SuperioresFalso.class);
    sqlUnit.loadEntitySet(FuncionariosFalso.class);
  }

  public void busca_por_id() {
    Funcionario funcionario = FuncionariosFalso.FUNCIONARIO_1;
    String prova = new FuncionarioToString().apply(funcionario);

    int id = funcionario.getId();

    Funcionario pojo = buscarFuncionario.porId(id);
    String res = new FuncionarioToString().apply(pojo);

    assertThat(res, equalTo(prova));
  }

  public void busca_por_matricula() {
    Funcionario funcionario = FuncionariosFalso.FUNCIONARIO_1;
    String prova = new FuncionarioToString().apply(funcionario);

    String matricula = funcionario.getMatricula();
    Funcionario pojo = buscarFuncionario.porMatricula(matricula);
    String res = new FuncionarioToString().apply(pojo);

    assertThat(res, equalTo(prova));
  }

  public void busca_lista_por_superior() {
    List<Funcionario> contra = ImmutableList.of(FUNCIONARIO_1, FUNCIONARIO_2, FUNCIONARIO_3);
    List<String> prova = transform(contra, new FuncionarioToString());

    Superior superior = SuperioresFalso.SUPERIOR_1;
    List<Funcionario> list = buscarFuncionario.porSuperior(superior);
    List<String> res = transform(list, new FuncionarioToString());

    assertThat(res.size(), equalTo(3));
    assertThat(res, equalTo(prova));
  }

  public void busca_iterador_por_superior() {
    List<Funcionario> contra = ImmutableList.of(FUNCIONARIO_1, FUNCIONARIO_2, FUNCIONARIO_3);
    List<String> prova = transform(contra, new FuncionarioToString());

    Superior superior = SuperioresFalso.SUPERIOR_1;
    Iterator<Funcionario> iterator = buscarFuncionario.iterarPorFuncionario(superior);
    List<Funcionario> list = ImmutableList.copyOf(iterator);
    List<String> res = transform(list, new FuncionarioToString());

    assertThat(res.size(), equalTo(3));
    assertThat(res, equalTo(prova));
  }

  public void busca_por_diretor() {
    Funcionario funcionario = FuncionariosFalso.FUNCIONARIO_1;
    String prova = new FuncionarioToString().apply(funcionario);

    Diretor diretor = funcionario.getDiretor();
    Funcionario pojo = buscarFuncionario.porDiretor(diretor);
    String res = new FuncionarioToString().apply(pojo);

    assertThat(res, equalTo(prova));
  }

}