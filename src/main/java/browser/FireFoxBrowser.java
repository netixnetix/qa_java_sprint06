package browser;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxBrowser extends BaseBrowser {
    public FireFoxBrowser(FirefoxOptions options, String baseURL) {
        super(new FirefoxDriver(options), baseURL);  //
    }
}