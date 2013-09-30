package br.com.objectos.dojo.cbertoldi.autocomplete;

import java.util.List;

import br.com.objectos.comuns.relational.jdbc.NativeSql;
import br.com.objectos.comuns.relational.search.Page;
import br.com.objectos.comuns.sitebricks.RequestWrapper;
import br.com.objectos.comuns.sitebricks.relational.PageList;
import br.com.objectos.comuns.sitebricks.relational.Pager;
import br.com.objectos.comuns.sitebricks.relational.PagerLoader;

import com.google.inject.Inject;
import com.google.inject.Provider;

class BuscarLocalidadeGuice implements BuscarLocalidade {

  private final Provider<NativeSql> sqlProvider;

  @Inject
  public BuscarLocalidadeGuice(Provider<NativeSql> sqlProvider) {
    this.sqlProvider = sqlProvider;
  }

  @Override
  public PageList<Localidade> pagePorTodos(RequestWrapper wrapper) {
    Page page = wrapper.getPage();

    List<Localidade> rows = newPager("*", wrapper)
        .listPage(page);

    Pager pager = newPager("count(*)", wrapper)
        .andLoadWith(new PagerLoader(wrapper))
        .single();

    return new PageList<Localidade>(rows, pager);
  }

  private NativeSql newPager(String what, RequestWrapper wrapper) {
    String localidade = wrapper.param("q");

    return newSelect(what)
        .add("where 1 = 1")
        .addIf("and PASSARO_BRANCO.LOCALIDADE like concat('%',?,'%')").paramNotNull(localidade)

        .add("order by")
        .add("PASSARO_BRANCO.LOCALIDADE");
  }

  private NativeSql newSelect(String what) {
    return sqlProvider.get()

        .add("select %s", what)
        .add("from PASSARO_BRANCO.LOCALIDADE")

        .andLoadWith(new LocalidadeLoader());
  }

}