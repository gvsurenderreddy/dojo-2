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
package br.com.objectos.dojo.faculdade;

import java.util.List;

import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * @author carolene.bertoldi@objectos.com.br (Carolene Reis Silva Bertoldi)
 */
class RelatorioDeAlunoProuniGenGuice implements RelatorioDeAlunoProuniGen {

  private final BuscarAluno buscarAluno;

  @Inject
  public RelatorioDeAlunoProuniGenGuice(BuscarAluno buscarAluno) {
    this.buscarAluno = buscarAluno;
  }

  @Override
  public RelatorioDeAlunoProuni gerarDe(Pedido pedido) {
    return new Construtor(pedido).novaInstancia();
  }

  @Override
  public List<RelatorioDeAlunoProuni> gerarDe(List<Pedido> pedidos) {
    List<RelatorioDeAlunoProuni> res = Lists.transform(pedidos, new ToRelatorioDeAlunoProuni());
    return ImmutableList.copyOf(res);
  }

  private class ToRelatorioDeAlunoProuni implements Function<Pedido, RelatorioDeAlunoProuni> {
    @Override
    public RelatorioDeAlunoProuni apply(Pedido input) {
      return gerarDe(input);
    }
  }

  private class Construtor implements RelatorioDeAlunoProuni.Construtor {

    private final Pedido pedido;

    public Construtor(Pedido pedido) {
      this.pedido = pedido;
    }

    @Override
    public RelatorioDeAlunoProuni novaInstancia() {
      return new RelatorioDeAlunoProuniPojo(this);
    }

    @Override
    public DateTime getPedido() {
      return pedido.getData();
    }

    @Override
    public DateTime getBaixa() {
      return new DateTime();
    }

    @Override
    public List<Aluno> getAlunos() {
      return buscarAluno.prouniPorSemestre(pedido.getPeriodo());
    }

  }

}