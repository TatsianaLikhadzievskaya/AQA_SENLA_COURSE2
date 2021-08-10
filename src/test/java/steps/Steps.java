package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Steps {

    LoginPage loginPage = new LoginPage();
    Locators locators = new Locators();

    //login
    @Допустим("^открыта страница \"([^\"]*)\"$")
    public void openPage(String pageUrl) {
        loginPage.openLoginPage();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(pageUrl, currentUrl, "Открыта неверная страница");
    }

    @Когда("^в поле логин введено значение \"([^\"]*)\"$")
    public void fillLoginField(String userLogin) {

        loginPage.loginField.setValue(userLogin);
    }

    @И("^в поле пароль введено значение \"([^\"]*)\"$")
    public void fillPasswordField(String userPassword) {
        loginPage.passwordField.setValue(userPassword);
    }

    @И("нажата кнопка \"Login\"")
    public void clickSignButton() {
        loginPage.signInButton.click();
    }

    @Тогда("^выполнен переход на страницу \"([^\"]*)\"$")
    public void openProductPage(String siteUrl) {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(siteUrl, currentUrl, "Значения ссылок не сопадают");

    }

    //logout
    @Допустим("^авторизованный пользователь находится на странице \"([^\"]*)\"$")
    public void authorizeCheck(String productUrl) throws IOException {

        loginPage.openLoginPage();
        loginPage.login();

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(productUrl, currentUrl, "Пользователь не авторизован");
    }

    @Когда("^пользователь нажимает на всплывающее меню \"Open Menu\"")
    public void openSiteMenu() {

        locators.menuLink.click();
        Selenide.sleep(2000);
    }


    @И("^в меню выбирает ссылку \"Logout\"")
    public void LogOutLink() {
        locators.signOutLink.click();
    }


    @Тогда("^открывается страница входа \"([^\"]*)\"$")
    public void openLoginPage(String loginUrl) {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(loginUrl, currentUrl, "Переход на страницу логина не произошел");

    }

    //check items price

    @Допустим("^авторизованный пользователь находится на странице каталога \"([^\"]*)\"$")
    public void authorizePageItemCheck(String productUrl) throws IOException {

        loginPage.openLoginPage();
        loginPage.login();

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(productUrl, currentUrl, "Пользователь не авторизован");
    }


    @И("^выполнено нажатие на ссылку \"([^\"]*)\"$")
    public void clickOnItemLink(String link) {
        locators.itemName.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Тогда("^цена товара равна \"([^\"]*)\"$")
    public void checkItemPrice(String price) {
        String actualPriceInfo = locators.itemPrice.getText();
        assertTrue(actualPriceInfo.contains(price), "Актуальная и ожидаемая цена не совпадают");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}