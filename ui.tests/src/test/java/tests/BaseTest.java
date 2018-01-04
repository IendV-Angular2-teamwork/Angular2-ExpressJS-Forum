package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    //Global test data excel file
    public static final String testDataExcelFileName = "testData.xlsx";
}
