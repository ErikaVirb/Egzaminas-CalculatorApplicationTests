import org.example.CalculatorMain;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditingExistingRecord {
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
    public void editExistingRecordPositive() {
        CalculatorMain.fillingLoginForm();
        CalculatorMain.clickButton(CalculatorMain.performedOperationsButton);
        CalculatorMain.waitForElementVisibility(CalculatorMain.changeButton);
        CalculatorMain.clickButton(CalculatorMain.changeButton);
        CalculatorMain.editCalculationValues("10","15","+","25");
        String actualResult = CalculatorMain.textForAssertion(CalculatorMain.modifiedFirstNr, CalculatorMain.modifiedPerformanceOp, CalculatorMain.modifiedSecondNr, CalculatorMain.modifiedResult);
        String expectedResult = "10+1525";
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test(priority = 2)
    public void editExistingRecordNegative() {
        CalculatorMain.fillingLoginForm();
        CalculatorMain.clickButton(CalculatorMain.performedOperationsButton);
        CalculatorMain.waitForElementVisibility(CalculatorMain.changeButton);
        CalculatorMain.clickButton(CalculatorMain.changeButton);
        CalculatorMain.editCalculationValues("-10","-15","+","25");
        String actualResult = CalculatorMain.textForAssertionNewUserLogin(CalculatorMain.modifyError);
        String expectedResult = "Whitelabel Error Page";
        Assert.assertEquals(actualResult, expectedResult);
    }
}
