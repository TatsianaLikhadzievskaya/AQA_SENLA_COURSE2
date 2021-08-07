import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    SelenideElement firstItem = $("button[data-test='add-to-cart-sauce-labs-backpack']");
    SelenideElement secondItem = $("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    SelenideElement openCartButton = $("span[class='shopping_cart_badge']");
    SelenideElement checkOutButton = $("button[data-test='checkout']");
    SelenideElement firstNameField = $("#first-name");
    SelenideElement lastNameField = $("#last-name");
    SelenideElement zipCodeField = $("#postal-code");
    SelenideElement continueButton = $("#continue");

    void purchaseTest() {

        //add first product to cart
        firstItem.click();
        //add second product to cart
        secondItem.click();
        //open cart with added products
        openCartButton.click();
        //checkout user data
        checkOutButton.click();
        firstNameField.sendKeys("John");
        lastNameField.sendKeys("Smith");
        zipCodeField.sendKeys("10017");
        //transfer to purchase overview
        continueButton.click();
    }
}
