package br.com.objectos.dojo.cbertoldi.autocomplete;

import static com.google.common.collect.Lists.transform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.objectos.comuns.sitebricks.FakeRequestWrapper;
import br.com.objectos.comuns.sitebricks.relational.PageList;
import br.com.objectos.comuns.testing.jdbc.SqlUnit;
import br.com.objectos.dojo.ModuloDeTesteObjectosDojo;

import com.google.inject.Inject;

@Test
@Guice(modules = { ModuloDeTesteObjectosDojo.class })
public class TesteDeBuscarLocalidade {

  @Inject
  private BuscarLocalidade buscar;

  @Inject
  private SqlUnit sqlUnit;

  @BeforeClass
  public void prepararSqlUnit() {
    sqlUnit.loadEntitySet(LocalidadesFalso.class);
  }

  public void page_por_todos() {
    List<Localidade> contra = LocalidadesFalso.getTodos();
    List<String> prova = transform(contra, new LocalidadeToString());

    FakeRequestWrapper wrapper = new FakeRequestWrapper();
    PageList<Localidade> page = buscar.pagePorTodos(wrapper);

    List<Localidade> list = page.getRows();
    List<String> res = transform(list, new LocalidadeToString());

    assertThat(res.size(), equalTo(6));
    assertThat(prova, equalTo(res));
  }

  public void filtro_por_codigo_asc() {
    Localidade localidade = LocalidadesFalso.SAO_PAULO_SP;
    String prova = new LocalidadeToString().apply(localidade);

    FakeRequestWrapper wrapper = new FakeRequestWrapper();
    wrapper.put("q", localidade.getDescricao());

    PageList<Localidade> pager = buscar.pagePorTodos(wrapper);
    List<Localidade> list = pager.getRows();
    List<String> res = transform(list, new LocalidadeToString());

    assertThat(res.size(), equalTo(1));
    assertThat(res.get(0), equalTo(prova));
  }

}