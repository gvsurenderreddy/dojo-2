@Test
@Guice(modules = { ModuloDeTestePassaroBranco.class })
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
    List<Localidade> contra = AcoesFalso.getTodos();
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