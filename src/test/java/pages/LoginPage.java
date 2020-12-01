package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class LoginPage {

    public LoginPage abrirPagina() {
        Selenide.open("/login");
        return this;
    }

    public LoginPage parametrosAcesso(String email, String pass) {
        $("#emailId").setValue(email);
        $("#passId").setValue(pass);
        $(byText("Entrar")).click();
        return this;
    }

    public SelenideElement mensagensAlerta() {
        return $(".alert span");
    }

    public void clearSession() {
        executeJavaScript("localStorage.clear();");
    }
}
