package selenium.test.section.majorIssue;

import browser.BaseBrowser;
import browser.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ScooterMainPage;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaqAnswer {
    private BaseBrowser browser;
    private ScooterMainPage main;

    public static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(
                        "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                ),
                Arguments.of(
                        "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                ),
                Arguments.of(
                        "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
                ),
                Arguments.of(
                        "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                ),
                Arguments.of(
                        "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
                ),
                Arguments.of(
                        "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                ),
                Arguments.of(
                        "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
                ),
                Arguments.of(
                        "Я живу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                )
        );

    }

    @BeforeEach
    void setUp() {
        browser = BrowserFactory.createBrowser();
        main = new ScooterMainPage(browser.getDriver());

    }

    @AfterEach
    void tearDown() {
        browser.quit();
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void validationTextFAQ(String answer, String question) {
        browser.navigateToMain();
        main.scrollFaqQuestio();
        Assertions.assertEquals(main.getFaqAnswer(answer), question);

    }


}
