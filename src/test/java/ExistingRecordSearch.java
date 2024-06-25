import org.example.CalculatorMain;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExistingRecordSearch {
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
    public void searchForExistingRecordPositive() {
        CalculatorMain.fillingLoginForm();
    }
    @Test(priority = 2)
    public void searchForExistingRecordNegative() {
        CalculatorMain.fillingLoginForm();
    }
}
