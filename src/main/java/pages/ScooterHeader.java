package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScooterHeader {

    WebDriver driver;

    public ScooterHeader(WebDriver driver) {
        this.driver = driver;
    }

    private final By scooterLabel = By.cssSelector("div.Header_Logo__23yGT a.Header_LogoScooter__3lsAR");
    private final By yandexLabel = By.cssSelector("div.Header_Logo__23yGT a.Header_LogoYandex__3TSOI");
    private final By createOrderButton = By.cssSelector("div.Header_Nav__AGCXC button.Button_Button__ra12g");
    private final By orderStatusButton = By.cssSelector("div.Header_Nav__AGCXC button.Header_Link__1TAG7");

    public void clickButtonSendOrder() {
        driver.findElement(createOrderButton).click();
    }

}
