package seleniumTest.sectionMajorIssue;
import browser.Browser;
import browser.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import pages.ScooterMainPage;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaqAnswer {
    private Browser browser;
    private ScooterMainPage main;

    @BeforeEach
    void setUp() {
        browser = BrowserFactory.createBrowser();
        main = new ScooterMainPage(browser.getDriver());

    }

    @AfterEach
    void tearDown() {
        browser.quit();
    }

    @Test
    public void validationTextFAQ() {
        browser.navigateToMain();
        main.scrollFaqQuestio();
        Assertions.assertEquals(main.getFaqAnswer("Сколько это стоит? И как оплатить?"),"Сутки — 400 рублей. Оплата курьеру — наличными или картой." );
        Assertions.assertEquals(main.getFaqAnswer("Хочу сразу несколько самокатов! Так можно?"),"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." );
        Assertions.assertEquals(main.getFaqAnswer("Как рассчитывается время аренды?"),"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30." );
        Assertions.assertEquals(main.getFaqAnswer("Можно ли заказать самокат прямо на сегодня?"),"Только начиная с завтрашнего дня. Но скоро станем расторопнее." );
        Assertions.assertEquals(main.getFaqAnswer("Можно ли продлить заказ или вернуть самокат раньше?"),"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." );
        Assertions.assertEquals(main.getFaqAnswer("Вы привозите зарядку вместе с самокатом?"),"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится." );
        Assertions.assertEquals(main.getFaqAnswer("Можно ли отменить заказ?"),"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." );
        //Свалится на этом шаге из за опечатки в FAQ
        Assertions.assertEquals(main.getFaqAnswer("Я живу за МКАДом, привезёте?"),"Да, обязательно. Всем самокатов! И Москве, и Московской области." );


    }


}
