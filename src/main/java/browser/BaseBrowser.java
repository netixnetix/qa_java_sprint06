package browser;

import org.openqa.selenium.WebDriver;

public abstract class BaseBrowser implements Browser {
    protected WebDriver driver;
    protected String baseURL;

    public BaseBrowser(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.baseURL = baseURL;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        driver.quit();
    }

    public void navigateToMain() {
        driver.get(baseURL);
    }

    public void navigateTo(String path) {
        driver.get(baseURL + path);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getBaseUrl() {
        return baseURL;
    }

}