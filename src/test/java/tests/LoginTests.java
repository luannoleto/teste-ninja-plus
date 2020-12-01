package tests;

import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class LoginTests extends BaseTest {

    @DataProvider(name = "login-alerts")
    public Object[] loginProvider() {
        return new Object[][]{
                {"luan@ninjaplus.com", "abc123", "Usuário e/ou senha inválidos"},
                {"404@gmail.com", "123456", "Usuário e/ou senha inválidos"},
                {"", "abc123", "Opps. Cadê o email?"},
                {"luan@ninjaplus.com", "", "Opps. Cadê a senha?"},
        };
    }

    @Test
    public void deveAutenticarUsuario() {
        login
                .abrirPagina()
                .parametrosAcesso("luan@ninjaplus.com", "123456");

        menu.UsuarioLogado().shouldHave(text("Luan"));
    }

    // DDT (Data Driven Testing)
    @Test(dataProvider = "login-alerts")
    public void deveExibirMensagemDeAlertaLogin(String email, String pass, String expectAlert) {
        login
                .abrirPagina()
                .parametrosAcesso(email, pass)
                .mensagensAlerta().shouldHave(text(expectAlert));
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}