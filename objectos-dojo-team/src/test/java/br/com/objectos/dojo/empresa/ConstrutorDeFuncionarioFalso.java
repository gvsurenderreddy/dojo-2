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

import br.com.objectos.dojo.taguiar.Contrato;
import br.com.objectos.dojo.empresa.Diretor;
import br.com.objectos.dojo.empresa.Funcionario;
import br.com.objectos.dojo.empresa.Superior;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class ConstrutorDeFuncionarioFalso implements Funcionario.Construtor {

  private String nome;
  private String matricula;
  private LocalDate dataNascimento;
  private LocalDate dataAdmissao;
  private LocalDate dataDemissao;
  private Superior superior;
  private Diretor diretor;
  private Contrato regimeDeContratacao;

  @Override
  public Funcionario novaInstancia() {
    return new br.com.objectos.dojo.empresa.FuncionarioJdbc(this);
  }

  public ConstrutorDeFuncionarioFalso nome(String nome) {
    this.nome = nome;
    return this;
  }

  public ConstrutorDeFuncionarioFalso matricula(String matricula) {
    this.matricula = matricula;
    return this;
  }

  public ConstrutorDeFuncionarioFalso dataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
    return this;
  }

  public ConstrutorDeFuncionarioFalso dataAdmissao(LocalDate dataAdmissao) {
    this.dataAdmissao = dataAdmissao;
    return this;
  }

  public ConstrutorDeFuncionarioFalso dataDemissao(LocalDate dataDemissao) {
    this.dataDemissao = dataDemissao;
    return this;
  }

  public ConstrutorDeFuncionarioFalso superior(Superior superior) {
    this.superior = superior;
    return this;
  }

  public ConstrutorDeFuncionarioFalso diretor(Diretor diretor) {
    this.diretor = diretor;
    return this;
  }

  public ConstrutorDeFuncionarioFalso regimeDeContratacao(Contrato regimeDeContratacao) {
    this.regimeDeContratacao = regimeDeContratacao;
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
  public LocalDate getDataAdmissao() {
    return dataAdmissao;
  }

  @Override
  public LocalDate getDataDemissao() {
    return dataDemissao;
  }

  @Override
  public Superior getSuperior() {
    return superior;
  }

  @Override
  public Diretor getDiretor() {
    return diretor;
  }

  @Override
  public Contrato getRegimeDeContratacao() {
    return regimeDeContratacao;
  }

}
