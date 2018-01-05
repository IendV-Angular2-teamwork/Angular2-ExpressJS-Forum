package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utils.listeners.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners({ TestListener.class })

public abstract class BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    //Global test data excel file
    public static final String testDataExcelFileName = "testdata.xlsx";
}
