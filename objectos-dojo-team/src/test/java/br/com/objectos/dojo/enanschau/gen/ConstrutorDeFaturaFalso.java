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

import org.joda.time.LocalDate;

import br.com.objectos.dojo.enanschau.gen.Fatura.Construtor;

/**
 * @author edenir.anschau@objectos.com.br (Edenir Norberto Anschau)
 */
public class ConstrutorDeFaturaFalso implements Construtor {

  private Aluno aluno;
  private int numero;;
  private double valor;
  private LocalDate vencimento;

  @Override
  public Fatura novaInstancia() {
    return null;
  }

  public ConstrutorDeFaturaFalso aluno(Aluno aluno) {
    this.aluno = aluno;
    return this;
  }

  public ConstrutorDeFaturaFalso numero(int numero) {
    this.numero = numero;
    return this;
  }

  public ConstrutorDeFaturaFalso valor(double valor) {
    this.valor = valor;
    return this;

  }

  public ConstrutorDeFaturaFalso vencimento(LocalDate vencimento) {
    this.vencimento = vencimento;
    return this;
  }

  @Override
  public Aluno getAluno() {

    return aluno;
  }

  @Override
  public int getNumero() {
    return numero;
  }

  @Override
  public double getValor() {
    return valor;
  }

  @Override
  public LocalDate getVencimento() {
    return vencimento;
  }

}