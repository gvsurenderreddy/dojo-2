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

import java.util.Iterator;
import java.util.List;

import br.com.objectos.dojo.asilva.SuperiorKey;

import com.google.inject.ImplementedBy;

/**
 * @author caio.petreanu@objectos.com.br (Caio Petreanu)
 */
@ImplementedBy(BuscarFuncionarioGuice.class)
public interface BuscarFuncionario {

  Funcionario porId(int id);

  Funcionario porMatricula(String matricula);

  List<Funcionario> porSuperior(Superior superior);

  Iterator<Funcionario> iterarPorFuncionario(Superior superior);

  Funcionario porDiretor(Diretor diretor);

  List<Funcionario> porSuperiorKey(SuperiorKey key);

}
