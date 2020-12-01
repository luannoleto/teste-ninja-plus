package common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CadastrarFilmesPage;
import pages.LeftBar;
import pages.LoginPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {
    protected static LoginPage login;
    protected static LeftBar menu;
    protected static CadastrarFilmesPage cadastro;


    @BeforeMethod
    public void start() {

        Configuration.browser = "firefox";
        Configuration.baseUrl = "http://ninjaplus-web:5000";
        Configuration.startMaximized = true;
        Configuration.timeout = 30000;

        login = new LoginPage();
        menu = new LeftBar();
        cadastro = new CadastrarFilmesPage();
    }

    @AfterMethod
    public void finish() {
        // Tiramos Screenshot pelo Selenide
        String tempShot = screenshot("temp_shot");

        // Queremos transformar em binário para anexar no report do allure
        try {
            BufferedImage bimage = ImageIO.read(new File(tempShot));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(bimage, "png", baos);
            byte[] finalShot = baos.toByteArray();

            io.qameta.allure.Allure.addAttachment("Evidência", new ByteArrayInputStream(finalShot));

        } catch (Exception execao) {
            System.out.println("Deu erro ao anexar o Screenshot :(. Trace => " + execao.getMessage());
        }
    }
}
