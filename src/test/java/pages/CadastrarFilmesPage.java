package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.FilmeModel;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CadastrarFilmesPage {

    public SelenideElement mensagemExcecaoTitulo() {
        return $(".alert-danger span");
    }

    public CadastrarFilmesPage add() {
        $(".movie-add").click();
        return this;
    }

    public CadastrarFilmesPage pesquisar(String Value) {
        $("input[placeholder^=Pesquisar]").setValue(Value);
        $("#search-movie").click();
        return this;
    }

    public CadastrarFilmesPage create(FilmeModel filme) {
        $("input[name=title]").setValue(filme.titulo);
        this.selecionarStatus(filme.status);
        $("input[name=year]").setValue(filme.ano.toString());
        $("input[name=release_date]").setValue(filme.estreia);
        this.incluirAtores(filme.atores);
        $("textarea[name=overview]").setValue(filme.sinopse);
        this.upload((filme.capa));
        $("#create-movie").click();

        return this;
    }

    private void selecionarStatus(String status) {
        $("input[placeholder=Status]").click();
        $$("ul li span").findBy(text(status)).click();
    }

    private void incluirAtores(List<String> atores) {
        SelenideElement element = $(".cast");

        for (String ator : atores) {
            element.setValue(ator);
            element.sendKeys(Keys.ENTER);
        }
    }

    private void upload(File capa) {
        String jsScript = "document.getElementById('upcover').classList.remove('el-upload__input');";
        executeJavaScript(jsScript);

        $("#upcover").uploadFile(capa);
    }

    public ElementsCollection listaFilmesCadastrados() {
        return $$("table tbody tr");
    }
}
