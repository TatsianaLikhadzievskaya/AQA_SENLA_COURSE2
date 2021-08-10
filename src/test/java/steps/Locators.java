package steps;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Locators {

    SelenideElement menuLink = $(By.id("react-burger-menu-btn"));
    SelenideElement signOutLink = $(By.id("logout_sidebar_link"));
    SelenideElement itemName = $("div[class='inventory_item_name']");
    SelenideElement itemPrice = $("div[class='inventory_details_price']");
}
