public class ServicoDeLocalidadeJson {

  private final BuscarLocalidade buscarLocalidade;

  @Inject
  public ServicoDeLocalidadeJson(BuscarLocalidade buscarLocalidade) {
    this.buscarLocalidade = buscarLocalidade;
  }

  @Get
  public Reply<?> get(Request request) {
    PassaroBrancoRequestWrapper wrapper = new PassaroBrancoRequestWrapper(request);

    PageList<Localidade> list = buscarLocalidade.pagePorTodos(wrapper);
    List<Localidade> rows = list.getRows();

    return Reply.with(rows).as(Json.class);
  }

}