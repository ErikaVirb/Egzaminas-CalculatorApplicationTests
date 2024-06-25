import org.example.CalculatorMain;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExistingUserLogin {
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
    @Test(priority = 1)
    public void existingUserLoginPositive(){
        String actualTitle = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.titleTextForLogin);
        String expectedTitle = "Prisijungimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        CalculatorMain.fillingLoginForm();
        String actualUser = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.checkLoginTitle);
        String expectedUser = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertEquals(actualUser, expectedUser);
    }
    @Test (priority = 2)
    public void existingUserLoginNegative(){
        String actualTitle = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.titleTextForLogin);
        String expectedTitle = "Prisijungimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        CalculatorMain.fillingLoginFormNegative();
        String actualErrorMessage = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.loginErrorMessage);
        String expectedErrorMessage1 = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage1);
    }

}
// LIKO ATASKAITŲ DARYMĄ PASIDARYTI !!!!!!!!!!!!!!! (run -> Edit Configurations ->
// " + " -> TestNG -> Class: "...Test" -> Listeners -> " + " ->
// EmailableReporter2 -> OK -> Applay -> OK