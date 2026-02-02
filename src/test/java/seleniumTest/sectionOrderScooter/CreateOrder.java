package seleniumTest.sectionOrderScooter;
import browser.Browser;
import browser.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ScooterHeader;
import pages.ScooterMainPage;
import pages.ScooterOrderForm;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateOrder {

    private Browser browser;
    private ScooterOrderForm orderForm;
    private ScooterHeader header;
    private ScooterMainPage main;
    LocalDate currentDate = LocalDate.now();
    LocalDate nextMonthsDate = currentDate.plusMonths(1);

    static Stream<Arguments> provideOrderData() {
        return Stream.of(
                Arguments.of("Рубироид", "Сибиряк", "Москва, Абрикосовая, д.4, кв 72", "Черкизовская", "+79995038801", 7, "black", "Очень Быстро привези самокат Очень Быстро привези самокат Очень Быстро привези самокат Очень Быстро привези самокат Очень Быстро привези самокат", 2),
                Arguments.of("Анна", "Иванова", "Московская область, Мытищи , Литейный, д. 15", "Сокольники", "+79111234567", 1, "grey", " ", 31)
        );
    }

    @BeforeEach
    void setUp() {
        browser = BrowserFactory.createBrowser();
        orderForm = new ScooterOrderForm(browser.getDriver());
    }

    @AfterEach
    void tearDown() {
        browser.quit();
    }

    @Test
    public void openOrderBylink() {
        browser.navigateTo("order");
        orderForm.checkOrderFormAccessibly();
    }

    @Test
    public void openOrderFormByHeaderButton() {
        header = new ScooterHeader(browser.getDriver());
        browser.navigateToMain();
        header.clickButtonSendOrder();
        assertEquals(browser.getCurrentUrl(), browser.getBaseUrl()+"order");
        orderForm.checkOrderFormAccessibly();
    }

    @Test
    public void openOrderFormByMainPage() {
        main = new ScooterMainPage(browser.getDriver());
        browser.navigateToMain();
        main.scrollTocSendOrderButton();
        main.clickSendOrderButton();
        assertEquals(browser.getCurrentUrl(), browser.getBaseUrl()+"order");
        orderForm.checkOrderFormAccessibly();
    }

    @ParameterizedTest
    @MethodSource("provideOrderData")
    public void createOrder(
            String username,
            String userFamily,
            String address,
            String metroStation,
            String phone,
            int rentalDays,
            String color,
            String comment,
            int plusDeliveryDay
    ) {

        // 1. Переход к форме заказа
        browser.navigateTo("order");

        // 2. Проверка доступности формы
        orderForm.checkOrderFormAccessibly();

        // 3. Заполнение полей
        orderForm.setUsername(username);
        orderForm.setUserFamily(userFamily);
        orderForm.setAdressDelivery(address);

        // 4. Выбор станции метро
        orderForm.clickFieldMetroStation();
        Assertions.assertEquals(orderForm.checkCountStation(), 225);
        orderForm.chooseStationMetro(metroStation);

        // 5. Телефон и переход к следующему шагу
        orderForm.setUserPhone(phone);
        orderForm.clickNext();

        // 6. Дата доставки
        LocalDate deliveryDate = currentDate.plusDays(plusDeliveryDay);
        orderForm.setDateDelivery(deliveryDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));


        // 7. Срок аренды и цвет
        orderForm.chooseRentalDay(rentalDays);
        orderForm.clickToColorScooterField(color);

        // 8. Комментарий и отправка
        orderForm.setCommentForDelivery(comment);
        orderForm.clickButtonSendOrder();


        // 9. Шаг подтверждения
        orderForm.checkConfirmationFormAccessibly();
        orderForm.clickConfirmationButtonOK();

        //10.Инф о успешном заказе
        orderForm.checkSuccessfulOrderFormAccessibly();
        Assertions.assertEquals(orderForm.getTextSFHeader(), "Заказ оформлен\n" +
                "Номер заказа: .  Запишите его:\n" +
                "пригодится, чтобы отслеживать статус");
        orderForm.clickSFButtonOK();
        browser.getCurrentUrl();
    }

    @ParameterizedTest
    @MethodSource("provideOrderData")
    public void createOrderByEditOrderingForm(
            String username,
            String userFamily,
            String address,
            String metroStation,
            String phone,
            int rentalDays,
            String color,
            String comment,
            int plusDeliveryDay
    ) {

        // 1. Переход к форме заказа
        browser.navigateTo("order");

        // 2. Заполнение полей
        orderForm.setUsername("Тестовое Имя");
        orderForm.setUserFamily("Южанский");
        orderForm.setAdressDelivery("Переславль-Залесский, Дом Царя Берендея");
        orderForm.clickFieldMetroStation();
        orderForm.chooseStationMetro(metroStation);
        orderForm.setUserPhone("555551111111");

        // 2. Переход к следующему шагу, заполнение предварительных данных
        orderForm.clickNext();
        LocalDate deliveryDate = currentDate.plusDays(plusDeliveryDay);
        orderForm.setDateDelivery(deliveryDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        orderForm.chooseRentalDay(rentalDays);
        orderForm.clickToColorScooterField(color);
        orderForm.setCommentForDelivery(comment);
        orderForm.clickButtonSendOrder();

        //3. Не даем подтверждение заказа
        orderForm.clickConfirmationButtonNO();
        orderForm.clickGoBackForFirstStepButton();
        orderForm.clearFieldFirsOrderingStep();

        //4. Перезаполняем поля
        orderForm.setUsername(username);
        orderForm.setUserFamily(userFamily);
        orderForm.setAdressDelivery(address);
        orderForm.clickFieldMetroStation();
        orderForm.chooseStationMetro(metroStation);
        orderForm.setUserPhone(phone);

        orderForm.clickNext();
        orderForm.clickButtonSendOrder();
        orderForm.checkConfirmationFormAccessibly();
        orderForm.clickConfirmationButtonOK();
        //Инф о успешном заказе
        orderForm.checkSuccessfulOrderFormAccessibly();
        Assertions.assertEquals(orderForm.getTextSFHeader(), "Заказ оформлен\n" +
               "Номер заказа: .  Запишите его:\n" +
                "пригодится, чтобы отслеживать статус");
    }

}
