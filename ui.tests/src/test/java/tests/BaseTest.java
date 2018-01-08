package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.drivers.ChooseDriver;
import utils.listeners.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners({ TestListener.class })

public abstract class BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    //Global test data excel file
    public static final String testDataExcelFileName = "testData.xlsx";
}
