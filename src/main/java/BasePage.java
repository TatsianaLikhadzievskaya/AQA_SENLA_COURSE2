import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    SelenideElement finishButton = $("#finish");
    SelenideElement confirmation = $("h2[class='complete-header']");
    SelenideElement cancelButton = $("#cancel");
    SelenideElement products = $("span[class='title']");

}
