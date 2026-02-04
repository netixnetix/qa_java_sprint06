package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ScooterOrderForm {

    WebDriver driver;

    public ScooterOrderForm(WebDriver driver) {
        this.driver = driver;
    }

    //Форма заказа самоката[1]
    private final By titleFormOrder = By.className("Order_Header__BZXOb");
    private final By inputNameFormOrder = By.cssSelector("div.Order_Form__17u6u input[placeholder*='Имя']");
    private final By inputFamilyFormOrder = By.cssSelector("div.Order_Form__17u6u input[placeholder*='Фамилия']");
    private final By inputAdressFormOrder = By.cssSelector("div.Order_Form__17u6u input[placeholder*='Адрес: куда привезти заказ']");
    private final By inputMetroFormOrder = By.cssSelector("div.Order_Form__17u6u input[placeholder*='Станция метро']");
    private final By inputPhoneFormOrder = By.cssSelector("div.Order_Form__17u6u input[placeholder*='Телефон: на него позвонит курьер']");
    private final By buttonNext = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    //Форма заказа самоката[2]
    private final By inputDateDelivery = By.cssSelector("div.Order_Form__17u6u input[placeholder*='Когда привезти самокат']");
    private final By inputRentalPeriod = By.cssSelector("div.Order_Form__17u6u div.Dropdown-control");
    private final By colorCheckBoxBlack = By.cssSelector("div.Order_Form__17u6u input[id='black']");
    private final By colorCheckBoxGrey = By.cssSelector("div.Order_Form__17u6u input[id='grey']");
    private final By colorFieldGrey = By.cssSelector("div.Order_Checkboxes__3lWSI label[for='grey']");
    private final By colorFieldBlack = By.cssSelector("div.Order_Checkboxes__3lWSI label[for='black']");
    private final By inputCommentForDelivery = By.cssSelector("div.Order_Form__17u6u input[placeholder='Комментарий для курьера']");
    private final By buttonSendOrder = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    private final By buttonGoBackForFirstStep = By.xpath("//div[@class = 'Order_Buttons__1xGrp']//button[text()='Назад']");
    private final By stationTextLocator = By.className("Order_Text__2broi");

    //Форма подтвреждения заказа
    private final By confirmationHeader = By.cssSelector("div.Order_Modal__YZ-d3 div.Order_ModalHeader__3FDaJ");
    private final By confirmationButtonOK = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");
    private final By confirmationButtonNO = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Нет']");

    //Форма успешного заказа
    private final By successfulOrderHeader = By.cssSelector("div.Order_Modal__YZ-d3 div.Order_ModalHeader__3FDaJ");
    private final By successfulOrderButtonOK = By.xpath("//div[@class='Order_NextButton__1_rCA']//button[text()='Посмотреть статус']");


    public void clickConfirmationButtonOK() {
        driver.findElement(confirmationButtonOK).click();
    }

    public void clickGoBackForFirstStepButton() {
        driver.findElement(buttonGoBackForFirstStep).click();
    }

    public void clickConfirmationButtonNO() {
        driver.findElement(confirmationButtonNO).click();
    }

    public void clickSFButtonOK() {
        driver.findElement(successfulOrderButtonOK).click();
    }

    public String getTextSFHeader() {
        return driver.findElement(successfulOrderHeader).getText();
    }

    public void setUserFamily(String userFamily) {
        driver.findElement(inputFamilyFormOrder).sendKeys(userFamily);
    }

    public void setUsername(String userName) {
        driver.findElement(inputNameFormOrder).sendKeys(userName);
    }

    public void setAdressDelivery(String adressDelivery) {
        driver.findElement(inputAdressFormOrder).sendKeys(adressDelivery);
    }

    public void clickFieldMetroStation() {
        driver.findElement(inputMetroFormOrder).click();
    }

    public void setUserPhone(String phone) {
        driver.findElement(inputPhoneFormOrder).sendKeys(phone);
    }

    public void clickNext() {
        driver.findElement(buttonNext).click();
    }

    public void setDateDelivery(String dateDelivery) {
        driver.findElement(inputDateDelivery).sendKeys(dateDelivery + Keys.ENTER);
    }

    public void setInputRentalPeriod(String period) {
        driver.findElement(inputRentalPeriod).sendKeys(period);
    }

    public void clickToColorScooterField(String blackOrGreyColor) {

        if(blackOrGreyColor.toLowerCase() == "black"){
            driver.findElement(colorFieldBlack).click();
        }
        else if (blackOrGreyColor.toLowerCase() == "grey"){
            driver.findElement(colorFieldGrey).click();
        }
        else {
            throw new AssertionError("Указан неверный тип цвета. Ожидается grey или black");
        }

    }

    public void setCommentForDelivery(String comment) {
        driver.findElement(inputCommentForDelivery).sendKeys(comment);
    }

    public void clickButtonSendOrder() {
        driver.findElement(buttonSendOrder).click();
    }

    public void checkOrderFormAccessibly(){
        driver.findElement(titleFormOrder).isDisplayed();
        driver.findElement(inputNameFormOrder).isDisplayed();
        driver.findElement(inputFamilyFormOrder).isDisplayed();
        driver.findElement(inputAdressFormOrder).isDisplayed();
        driver.findElement(inputMetroFormOrder).isDisplayed();
        driver.findElement(inputPhoneFormOrder).isDisplayed();
        driver.findElement(buttonNext).isDisplayed();
    }

    public void checkConfirmationFormAccessibly(){
        driver.findElement(confirmationHeader).isDisplayed();
        driver.findElement(confirmationButtonOK).isDisplayed();
        driver.findElement(confirmationButtonNO).isDisplayed();

    }

    public void checkSuccessfulOrderFormAccessibly(){
        driver.findElement(successfulOrderHeader).isDisplayed();
        driver.findElement(successfulOrderButtonOK).isDisplayed();
    }

    public int checkCountStation(){
        List<WebElement> stationElements = driver.findElements(stationTextLocator);
        return stationElements.size();
    }

    public void chooseStationMetro(String metro){
        By stationLocator = By.xpath("//div[@class='Order_Text__2broi' and text()='" + metro + "']");
        driver.findElement(stationLocator).click();
    }

    public void chooseRentalDay(int days) {
        driver.findElement(inputRentalPeriod).click();

        String result;
        switch (days) {
            case 1:
                result = "сутки";
                break;
            case 2:
                result = "двое суток";
                break;
            case 3:
                result = "трое суток";
                break;
            case 4:
                result = "четверо суток";
                break;
            case 5:
                result = "пятеро суток";
                break;
            case 6:
                result = "шестеро суток";
                break;
            case 7:
                result = "семеро суток";
                break;
            default:
                result = "сутки";
                break;
        }

        By stationLocator = By.xpath(("//div[@class='Order_Form__17u6u']//div[@class='Dropdown-option' and text()='" + result + "']"));
        driver.findElement(stationLocator).click();
    }

    public void clearFieldFirsOrderingStep() {
        driver.findElement(inputNameFormOrder).clear();
        driver.findElement(inputFamilyFormOrder).clear();
        driver.findElement(inputAdressFormOrder).clear();
        driver.findElement(inputMetroFormOrder).clear();
        driver.findElement(inputPhoneFormOrder).clear();
    }

}
