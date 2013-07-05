import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.testng.annotations.Test;

import br.com.objectos.dojo.cpelissari_estagiario.LerTxt;
import br.com.objectos.dojo.cpelissari_estagiario.LerTxtImpl;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;

/**
 * @author cristiane.pelissari@objectos.com.br (Cristiane Iope Pelissari)
 */
@Test
public class TesteDeLerTxt {

  private final LerTxt ler = new LerTxtImpl();

  public void deve_ler_arquivo() throws URISyntaxException, IOException {
    URL url = Resources.getResource(getClass(), "/txt/ler_arquivo.txt");
    URI uri = url.toURI();

    File arquivo = new File(uri);

    String linha_do_arquivo = ler.ler(arquivo);

    String separador = System.getProperty("line.separator");

    String[] array_todos = linha_do_arquivo.split(separador);

    List<String> resp = ImmutableList.copyOf(array_todos);

    assertThat(resp.size(), equalTo(3));
    assertThat(resp.get(0), equalTo("arquivo para exercício"));
    assertThat(resp.get(1), equalTo("para fixar conhecimento /  ;"));
    assertThat(resp.get(2), equalTo("leia -me."));
  }

}