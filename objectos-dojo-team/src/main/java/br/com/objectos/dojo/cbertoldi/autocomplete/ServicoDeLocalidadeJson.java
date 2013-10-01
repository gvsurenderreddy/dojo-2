package br.com.objectos.dojo.cbertoldi.autocomplete;

import java.util.List;

import br.com.objectos.comuns.sitebricks.DefaultRequestWrapper;
import br.com.objectos.comuns.sitebricks.relational.PageList;

import com.google.inject.Inject;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Get;

public class ServicoDeLocalidadeJson {

  private final BuscarLocalidade buscarLocalidade;

  @Inject
  public ServicoDeLocalidadeJson(BuscarLocalidade buscarLocalidade) {
    this.buscarLocalidade = buscarLocalidade;
  }

  @Get
  public Reply<?> get(Request request) {
    DefaultRequestWrapper wrapper = new DefaultRequestWrapper(request);

    PageList<Localidade> list = buscarLocalidade.pagePorTodos(wrapper);
    List<Localidade> rows = list.getRows();

    return Reply.with(rows).as(Json.class);
  }

}