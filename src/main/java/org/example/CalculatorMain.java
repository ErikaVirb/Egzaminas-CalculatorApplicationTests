package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CalculatorMain {
    public static WebDriver browser;
    public static final int WAIT_DURATION_SECONDS = 7;
    public static By createNewAccountLink = By.cssSelector("a[href=\"/registruoti\"]");
    public static By titleTextForNewAccountPage = By.cssSelector(".form-signin-heading");
    public static By titleTextForLogin = By.cssSelector(".form-heading");
    public static By loginFormName = By.cssSelector("input[placeholder=\"Prisijungimo vardas\"]");
    public static By loginFormPassword = By.cssSelector("input[placeholder=\"Slaptažodis\"]");
    public static By registrationFormNameField = By.id("username");
    public static By registrationFormPasswordField = By.id("password");
    public static By registrationFormPasswordConfirmationField = By.id("passwordConfirm");
    public static By createUserAccountButton = By.cssSelector("button[type=\"submit\"]");
    public static By registrationNameFieldErrorMesage = By.id("username.errors");
    public static By registrationPasswordFieldErrorMesage = By.id("password.errors");
    public static By loginErrorMessage = By.cssSelector("body > div:nth-child(5) > form:nth-child(1) > div:nth-child(2) > span:nth-child(4)");
    public static By checkLoginTitle = By.cssSelector("body h3");
    public static By inputFirstNumberField = By.id("sk1");
    public static By inputSecondNumberField = By.id("sk2");
    public static By dropdownOptions = By.cssSelector("select[name=\"zenklas\"]");
    public static By countButton = By.xpath("//input[@value=\"skaičiuoti\"]");
    public static By countResult = By.xpath("//h4[contains(text(),\"2\")]");
    public static By validationError = By.cssSelector(".error");
    public static By performedOperationsButton = By.cssSelector("a[href=\"/skaiciai\"]");
    public static By changeButton = By.xpath("(//a[contains(text(),'Keisti')])[1]");
    public static By modifyFirstNumber = By.xpath("(//input[@name='sk1'])[1]");
    public static By modifySecondNumber = By.xpath("(//input[@name='sk2'])[1]");
    public static By modifyResultNumber = By.xpath("//input[@name=\"rezult\"]");
    public static By modifyUpdateButton = By.cssSelector("input[value=\"Atnaujinti\"]");
    public static By modifyPerformeOperationsButton = By.cssSelector("input[value=\"+\"]");
    public static By modifiedFirstNr = By.xpath("//td[normalize-space()=\"10\"]");
    public static By modifiedSecondNr = By.xpath("//td[normalize-space()=\"15\"]");
    public static By modifiedResult = By.xpath("//td[normalize-space()=\"25\"]");
    public static By modifiedPerformanceOp = By.xpath("//td[normalize-space()=\"+\"]");
    public static By modifyError = By.cssSelector("body h1");

    public static void main(String[] args) {
        System.out.println("Puslapis - Skaičiai. Egzaminas:");
        System.out.println();
    }
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver126.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        browser = new ChromeDriver(chromeOptions);
        browser.get("http://localhost:8080/");
    }
    public static void closeBrowser() {
//        if (browser != null) {
//            browser.quit();
//        }
    }
    public static void waitForElementVisibility(By locator){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(WAIT_DURATION_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void clickOnLink(By locator){
        browser.findElement(locator).click();
    }
    public static void clickButton(By locator){
        browser.findElement(locator).click();
    }
//    public static boolean isEnabledDisplayed(WebElement element) {
//        return element.isDisplayed() && element.isEnabled();
//    }
    public static String textForAssertionNewUserLogin(By locator){
        WebElement element = browser.findElement(locator);
        return element.getText();
    }
    public static String gettingTextFromElement(By by){
        waitForElementVisibility(by);
        return browser.findElement(by).getText();
    }
    public static String textForAssertion(By first, By perform, By second, By result){
        return gettingTextFromElement(first) + gettingTextFromElement(perform) + gettingTextFromElement(second) + gettingTextFromElement(result);
    }
//    public static void sendKeysFirstNumber(String keyword){
//        WebElement searchInput = browser.findElement(inputFirstNumberField);
//        searchInput.sendKeys(keyword);
//    }
//    public static void sendKeysSecondNumber(String keyword){
//        WebElement searchInput = browser.findElement(inputSecondNumberField);
//        searchInput.sendKeys(keyword);
//    }
    public static void sendKeysName(String keyword){
        WebElement searchInput = browser.findElement(registrationFormNameField);
        searchInput.sendKeys(keyword);
    }
    public static void sendKeysPassword(String keyword){
        WebElement searchInput = browser.findElement(registrationFormPasswordField);
        searchInput.sendKeys(keyword);
    }
    public static void sendKeysPasswordConfirmation(String keyword){
        WebElement searchInput = browser.findElement(registrationFormPasswordConfirmationField);
        searchInput.sendKeys(keyword);
    }
    public static void fillingRegistrationForm(){
        CalculatorMain.waitForElementVisibility(registrationFormNameField);
        CalculatorMain.sendKeysName("Adamal");
        CalculatorMain.sendKeysPassword("kamikadzė1233");
        CalculatorMain.sendKeysPasswordConfirmation("kamikadzė1233");
        CalculatorMain.clickButton(createUserAccountButton);
    }
    public static void fillingRegistrationFormNegative(){
        CalculatorMain.waitForElementVisibility(registrationFormNameField);
        CalculatorMain.sendKeysName("am");
        CalculatorMain.sendKeysPassword("ka");
        CalculatorMain.sendKeysPasswordConfirmation("tu");
        CalculatorMain.clickButton(createUserAccountButton);
    }
    public static void sendKeysLoginName(String keyword){
        WebElement searchInput = browser.findElement(loginFormName);
        searchInput.sendKeys(keyword);
    }
    public static void sendKeysLoginPassword(String keyword){
        WebElement searchInput = browser.findElement(loginFormPassword);
        searchInput.sendKeys(keyword);
    }
    public static void fillingLoginForm(){
        CalculatorMain.waitForElementVisibility(loginFormName);
        CalculatorMain.sendKeysLoginName("Adamas");
        CalculatorMain.sendKeysLoginPassword("kamikadzė123");
        CalculatorMain.clickButton(createUserAccountButton);
    }
    public static void fillingLoginFormNegative() {
        CalculatorMain.waitForElementVisibility(loginFormName);
        CalculatorMain.sendKeysLoginName("Adam");
        CalculatorMain.sendKeysLoginPassword("kamikadzė123");
        CalculatorMain.clickButton(createUserAccountButton);
    }
    public static void choseTransactionSign(By locator){
        WebElement dropdownElement = browser.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Dalyba");
    }
    public static void fillInputWithNumbersAndGetResult(String numberOne, String numberTwo, String result){
        WebElement firstNumber = browser.findElement(inputFirstNumberField);
        firstNumber.clear();
        firstNumber.sendKeys(numberOne);
        WebElement secondNumber = browser.findElement(inputSecondNumberField);
        secondNumber.clear();
        secondNumber.sendKeys(numberTwo);
        choseTransactionSign(dropdownOptions);
        clickButton(countButton);
    }
    public static void editCalculationValues(String numberOne,String numberTwo,String performeOperation, String result){
        WebElement firstNumber = browser.findElement(modifyFirstNumber);
        firstNumber.clear();
        firstNumber.sendKeys(numberOne);

        WebElement operation = browser.findElement(modifyPerformeOperationsButton);
        operation.clear();
        operation.sendKeys(performeOperation);

        WebElement secondNumber = browser.findElement(modifySecondNumber);
        secondNumber.clear();
        secondNumber.sendKeys(numberTwo);

        WebElement resultNumber = browser.findElement(modifyResultNumber);
        resultNumber.clear();
        resultNumber.sendKeys(result);
        clickButton(modifyUpdateButton);
    }

    public static void takeScreenshots() {
        String name = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(new Date());
        File imgFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(imgFile, new File("src/test/screenshots/" + name + "_screenshot.png"));
        } catch (IOException error) {
            System.out.println("Nepavyko padaryti screenshot. Placiau:" + error.getMessage());
        }
    }
}
