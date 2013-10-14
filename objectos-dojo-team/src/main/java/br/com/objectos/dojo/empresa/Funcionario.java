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

import org.joda.time.LocalDate;

import br.com.objectos.comuns.relational.jdbc.Insertable;
import br.com.objectos.dojo.taguiar.Contrato;

/**
 * @author caio.petreanu@objectos.com.br (Caio Petreanu)
 */
public interface Funcionario extends Insertable {

  interface Construtor extends br.com.objectos.comuns.base.Construtor<Funcionario> {

    String getNome();

    String getMatricula();

    LocalDate getDataNascimento();

    LocalDate getDataAdmissao();

    LocalDate getDataDemissao();

    Superior getSuperior();

    Diretor getDiretor();

    Contrato getRegimeDeContratacao();

  }

  int getId();

  String getNome();

  String getMatricula();

  LocalDate getDataNascimento();

  LocalDate getDataAdmissao();

  LocalDate getDataDemissao();

  Superior getSuperior();

  Diretor getDiretor();

  Contrato getRegimeDeContratacao();

}