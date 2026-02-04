package browser;
import org.ini4j.Ini;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BrowserFactory {

    private static final String CONFIG_FILE = "src/config.ini";

    public static BaseBrowser createBrowser() {
        try {
            Ini ini = new Ini(new File(CONFIG_FILE));
            List<String> browserOptions = new ArrayList<>();
            String browserType = System.getProperty("browser", ini.get("browser", "type"));
            String baseURL = ini.get("environment", "url");
            browserOptions.add(ini.get("browser", "sandbox"));
            browserOptions.add(ini.get("browser", "headless"));
            browserOptions.add(ini.get("browser", "sharedMem"));

            switch (browserType.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(browserOptions);
                    return new ChromeBrowser(chromeOptions, baseURL);

                case "firefox":
                    FirefoxOptions firefoxOptions= new FirefoxOptions();
                    firefoxOptions.addArguments(browserOptions);

                    return new FireFoxBrowser(firefoxOptions, baseURL);

                default:
                    throw new IllegalArgumentException(
                            "Тип браузера не поддерживается: " + browserType
                    );
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать конфигурационный файл: " + CONFIG_FILE, e);
        }
    }




}