import org.example.CalculatorMain;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewRecord {
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
    public void createNewRecordPositive() {
        CalculatorMain.fillingLoginForm();
        CalculatorMain.fillInputWithNumbersAndGetResult("16","2","-");
        String actualResult = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.countResult);
        CalculatorMain.waitForElementVisibility(CalculatorMain.countResult);
        String expectedResult = "16 / 2 = 8";
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test(priority = 2)
    public void createNewRecordNegative() {
        CalculatorMain.fillingLoginForm();
        CalculatorMain.fillInputWithNumbersAndGetResult("-16","2","-");
        String actualResult = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.validationError);
        String expectedResult = "Validacijos klaida: skaičius negali būti neigiamas";
        Assert.assertEquals(actualResult, expectedResult);
    }

}
