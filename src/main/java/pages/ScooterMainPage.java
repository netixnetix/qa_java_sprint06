package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ScooterMainPage {

    private WebDriver driver;

    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By createOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");
    private final By questionButton(String questionText) {
        return By.xpath(String.format(
                "//div[@data-accordion-component='AccordionItemButton' and text()='%s']",
                questionText
        ));
    }

    private By answerPanel(String questionText) {
        return By.xpath(String.format(
                "//div[@data-accordion-component='AccordionItem'][.//div[@data-accordion-component='AccordionItemButton' and text()='%s']]//div[@data-accordion-component='AccordionItemPanel']",
                questionText
        ));
    }

    public String getFaqAnswer(String questionText) {
        WebElement questionBtn = driver.findElement(questionButton(questionText));
        questionBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answerPanel(questionText)));
        return driver.findElement(answerPanel(questionText)).getText();
    }


    public void clickSendOrderButton() {
        driver.findElement(createOrderButton).click();
    }

    public void scrollTocSendOrderButton() {
        WebElement element = driver.findElement(createOrderButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    public void scrollFaqQuestio() {
        WebElement lastElement = driver.findElement(By.tagName("body"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(false);",
                lastElement
        );


    }

}
