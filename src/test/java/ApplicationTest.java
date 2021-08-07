
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ApplicationTest {

    @BeforeEach
    public void setUp() {
        Configuration.startMaximized = true;
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    @Order(1)
    public void loginTest() throws IOException {

        //open saucedemo.com
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();

        //user login
        loginPage.login();

        //check user logon to succeed
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl, "User logon failed");
    }

    @Test
    @Order(2)
    public void confirmPurchaseTest() throws IOException {

        LoginPage loginPage = new LoginPage();
        BasePage base = new BasePage();
        ProductPage productPage = new ProductPage();
        loginPage.openLoginPage();
        loginPage.login();
        productPage.purchaseTest();

        //confirm purchase
        base.finishButton.click();
        Selenide.sleep(2000);

        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", base.confirmation.getText(), "Purchase is not completed");
    }

    @Test
    @Order(3)
    public void cancelPurchaseTest() throws IOException {

        LoginPage loginPage = new LoginPage();
        BasePage base = new BasePage();
        ProductPage productPage = new ProductPage();
        loginPage.openLoginPage();
        loginPage.login();
        productPage.purchaseTest();

        //cancel purchase
        base.cancelButton.click();
        Selenide.sleep(2000);

        Assertions.assertEquals("PRODUCTS", base.products.getText(), "Purchase is not cancelled");
    }

    @Test
    @Order(4)
    public void removeItemTest() throws IOException {

        LoginPage loginPage = new LoginPage();
        ProductPage productPage = new ProductPage();
        loginPage.openLoginPage();
        loginPage.login();

        //add first product to cart
        productPage.firstItem.click();
        //add second product to cart
        productPage.secondItem.click();
        //open cart with added products
        productPage.openCartButton.click();
        sleep(2000);

        //remove products from cart
        ElementsCollection productCollection = $$("div[class='cart_item']");

        for (SelenideElement element : productCollection) {
                element.find(byText("Remove")).click();
        }

        Assertions.assertEquals(0, productCollection.size(), "cart is not empty");
    }

        @Test
        @Order(5)
        public void countItemTest () throws IOException {

            LoginPage loginPage = new LoginPage();
            ProductPage productPage = new ProductPage();
            loginPage.openLoginPage();
            loginPage.login();
            productPage.purchaseTest();
            Selenide.sleep(2000);

            //count items totalcost
            ElementsCollection countCollection = $$("div[class='cart_item']");
            System.out.println(countCollection.size());

            double totalCost=0;
            double itemCost;
            for (SelenideElement element : countCollection) {
                String itemCostText = element.find(byCssSelector("div[class='inventory_item_price']")).getText();
                itemCost= Double.parseDouble(itemCostText.substring(1));
                totalCost += itemCost;
            }

            String actualText = $("div[class='summary_subtotal_label']").getText();
            double actual= Double.parseDouble(actualText.substring(13));
            Assertions.assertEquals(totalCost, actual);
        }

    }


