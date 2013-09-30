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

    List<LocalidadeJson> jsons = transform(rows, new ToJson());

    return Reply.with(jsons).as(Json.class);
  }

  private class ToJson implements Function<Localidade, LocalidadeJson> {
    @Override
    public LocalidadeJson apply(Localidade input) {
      return new LocalidadeJson(input);
    }
  }

}