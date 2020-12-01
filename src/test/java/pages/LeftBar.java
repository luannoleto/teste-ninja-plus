package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LeftBar {
    public SelenideElement UsuarioLogado() {
        return $(".user .info span");
    }
}
