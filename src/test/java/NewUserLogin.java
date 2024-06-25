import org.checkerframework.checker.units.qual.C;
import org.example.CalculatorMain;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class NewUserLogin {

    @BeforeMethod
    public static void setUp() {
        CalculatorMain.setup();
    }

    @AfterMethod
    public static void closeBrowser(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            CalculatorMain.takeScreenshots();
        }
        CalculatorMain.closeBrowser();
    }
    @Test (priority = 1)
    public void registrationOfNewUserPositive(){
        CalculatorMain.waitForElementVisibility(CalculatorMain.createNewAccountLink);
        CalculatorMain.clickOnLink(CalculatorMain.createNewAccountLink);
        String actualTitle = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.titleTextForNewAccountPage);
        String expectedTitle = "Naujos paskyros sukūrimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        CalculatorMain.fillingRegistrationForm();
        String actualUser = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.checkLoginTitle);
        String expectedUser = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertEquals(actualUser, expectedUser);
    }
    @Test (priority = 2)
    public void registrationOfNewUserNegative(){
        CalculatorMain.waitForElementVisibility(CalculatorMain.createNewAccountLink);
        CalculatorMain.clickOnLink(CalculatorMain.createNewAccountLink);
        String actualTitle = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.titleTextForNewAccountPage);
        String expectedTitle = "Naujos paskyros sukūrimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        CalculatorMain.fillingRegistrationFormNegative();
        String actualErrorMessage = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.registrationNameFieldErrorMesage);
        String expectedErrorMessage = "Privaloma įvesti nuo 3 iki 32 simbolių";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        String actualErrorMessage1 = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.registrationPasswordFieldErrorMesage);
        String expectedErrorMessage1 = "Privaloma įvesti bent 3 simbolius";
        Assert.assertEquals(actualErrorMessage1, expectedErrorMessage1);
    }
}
// LIKO ATASKAITŲ DARYMĄ PASIDARYTI !!!!!!!!!!!!!!! (run -> Edit Configurations ->
// " + " -> TestNG -> Class: "...Test" -> Listeners -> " + " ->
// EmailableReporter2 -> OK -> Applay -> OK
