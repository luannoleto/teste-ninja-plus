package tests;

import com.codeborne.selenide.conditions.Text;
import common.BaseTest;
import libs.Database;
import models.FilmeModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class FilmeTests extends BaseTest {

    private Database db;

    @BeforeMethod
    public void setup() {
        login
                .abrirPagina()
                .parametrosAcesso("luan@ninjaplus.com", "123456");

        menu.UsuarioLogado().shouldHave(text("Luan"));
    }

    @BeforeSuite
    public void resetarFilmesCadastrados() {
        db = new Database();
        db.resetFilmes();
    }

    @Test
    public void DeveCadastrarNovoFilme() {
        FilmeModel dadosFilme = new FilmeModel(
                "Os Vingadores - Guerra Infinita",
                "Disponível",
                2018,
                "12/11/2020",
                Arrays.asList("Robert Downey", "Chris Hemsworth", "Chris Evans", "Mark Ruffalo", "Samuel Jackson"),
                "Loki, o irmão de Thor, ganha acesso ao poder ilimitado do cubo cósmico "
                        + "ao roubá-lo de dentro das instalações da S.H.I.E.L.D.",
                "capa_Vingadores.jpg"
        );

        cadastro.add();
        cadastro.create(dadosFilme);
        cadastro.listaFilmesCadastrados().findBy(text(dadosFilme.titulo)).shouldBe(visible);
    }

    @Test
    public void DeveVerificarTituloDuplicado() {
        FilmeModel dadosFilme = new FilmeModel(
                "Os Vingadores - Guerra Infinita",
                "Disponível",
                2018,
                "12/11/2020",
                Arrays.asList("Robert Downey", "Chris Hemsworth", "Chris Evans", "Mark Ruffalo", "Samuel Jackson"),
                "Loki, o irmão de Thor, ganha acesso ao poder ilimitado do cubo cósmico "
                        + "ao roubá-lo de dentro das instalações da S.H.I.E.L.D.",
                "capa_Vingadores.jpg"
        );

        cadastro.add();
        cadastro.create(dadosFilme);
        cadastro.mensagemExcecaoTitulo().shouldHave(new Text("Oops - Este titulo já existe no NinjaPlus."));
    }

    @Test
    public void DeveRetornarUnicoFilmePesquisado() {
        cadastro.pesquisar("Os Vingadores").listaFilmesCadastrados().shouldHaveSize(1);
    }

    @Test
    public void DeveRetornarDoisFilmesPesquisado() {
        cadastro.pesquisar("Batman").listaFilmesCadastrados().shouldHaveSize(2);
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}
