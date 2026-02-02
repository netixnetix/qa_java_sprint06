package browser;
import org.openqa.selenium.WebDriver;

public interface Browser {
    WebDriver getDriver();
    void navigateToMain();
    void navigateTo(String path);
    void quit();
    String getCurrentUrl();
    String getBaseUrl();
}