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

import static com.google.common.collect.Lists.transform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.ModuloDeTesteObjectosDojo;
import br.com.objectos.dojo.cpetreanu.Funcionario;
import br.com.objectos.dojo.cpetreanu.Superior;

import com.google.common.base.Optional;
import com.google.inject.Inject;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
@Test
@Guice(modules = { ModuloDeTesteObjectosDojo.class })
public class TesteDeCacheDeFuncionario {

  @Inject
  private CacheDeFuncionario cache;

  @Inject
  private SqlUnit sqlUnit;

  @BeforeClass
  public void prepararSqlUnit() {
    sqlUnit.loadEntitySet(DiretoresFalso.class);
    sqlUnit.loadEntitySet(SuperioresFalso.class);

    sqlUnit.loadEntitySet(FuncionariosFalso.class);
  }

  public void por_id() {
    Funcionario funcionario = FuncionariosFalso.FUNCIONARIO_1;
    String prova = new FuncionarioToString().apply(funcionario);

    int id = funcionario.getId();
    Optional<Funcionario> optional = cache.porId(id);
    Funcionario pojo = optional.get();
    String res = new FuncionarioToString().apply(pojo);

    assertThat(res, equalTo(prova));
  }

  public void lista_por_superior_key() {
    List<Funcionario> contra = FuncionariosFalso.getTodos();
    List<String> prova = transform(contra, new FuncionarioToString());

    Funcionario funcionario = FuncionariosFalso.FUNCIONARIO_1;
    Superior superior = funcionario.getSuperior();
    SuperiorKey superiorKey = new SuperiorKey(superior);
    List<Funcionario> list = cache.porSuperiorKey(superiorKey);
    List<String> res = transform(list, new FuncionarioToString());

    assertThat(res.size(), equalTo(1));
    assertThat(res, equalTo(prova));
  }

}