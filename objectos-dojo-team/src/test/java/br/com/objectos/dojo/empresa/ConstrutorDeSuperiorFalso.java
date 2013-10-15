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
package br.com.objectos.dojo.empresa;

import org.joda.time.LocalDate;

import br.com.objectos.dojo.empresa.Diretor;
import br.com.objectos.dojo.empresa.Superior;
import br.com.objectos.dojo.empresa.SuperiorJdbc;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class ConstrutorDeSuperiorFalso implements Superior.Construtor {

  private String nome;
  private String matricula;
  private LocalDate dataNascimento;
  private LocalDate admissao;
  private LocalDate demissao;
  private Diretor diretor;

  @Override
  public Superior novaInstancia() {
    return new SuperiorJdbc(this);
  }
  public ConstrutorDeSuperiorFalso nome(String nome) {
    this.nome = nome;
    return this;
  }

  public ConstrutorDeSuperiorFalso matricula(String matricula) {
    this.matricula = matricula;
    return this;
  }

  public ConstrutorDeSuperiorFalso dataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
    return this;
  }

  public ConstrutorDeSuperiorFalso dataAdmissao(LocalDate admissao) {
    this.admissao = admissao;
    return this;
  }

  public ConstrutorDeSuperiorFalso dataDemissao(LocalDate demissao) {
    this.demissao = demissao;
    return this;
  }

  public ConstrutorDeSuperiorFalso diretor(Diretor diretor) {
    this.diretor = diretor;
    return this;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getMatricula() {
    return matricula;
  }

  @Override
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  @Override
  public LocalDate getAdmissao() {
    return admissao;
  }

  @Override
  public LocalDate getDemissao() {
    return demissao;
  }

  @Override
  public Diretor getDiretor() {
    return diretor;
  }

}