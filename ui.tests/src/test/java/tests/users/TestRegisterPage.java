package tests.users;

import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.excelUtils.ExcelUtil;
import utils.listeners.TestListener;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Listeners({ TestListener.class })

public class TestRegisterPage extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private RegisterPage registerPage;
    private String uniqueEmail;

    @BeforeTest
    public void setUp(){ //TODO: Not work on test suite mode
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeWebDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, 15);
        //this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.registerPage = new RegisterPage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        ExcelUtil.setExcelFileSheet("RegisterData");
    } //TODO: This on Base Page class

    @AfterTest
    public void tearDown(){
        this.driver.quit();
    }

    @Test
    public void registerWithValidCredentials_mustRegisterSuccessful_1() throws Exception {

        this.registerPage.directRegister(
                ExcelUtil.getCellData(1, 1),
                this.uniqueEmail,
                ExcelUtil.getCellData(1, 3),
                ExcelUtil.getCellData(1, 4)
        );

        //For report
        ExcelUtil.setRowNumber(1);
        ExcelUtil.setColumnNumber(5);

        Assert.assertEquals(
                "You have successfully signed up! Now you should be able to log in.", //TODO: Assert msgs in DDT
                this.registerPage.getNotificationMsg()
        );
    }

    @Test
    public void registerWithValidCredentials_mustRegisterSuccessful_1_testRedirect() throws Exception {
        this.registerPage.directRegister(
                ExcelUtil.getCellData(1, 1),
                this.uniqueEmail,
                ExcelUtil.getCellData(1, 3),
                ExcelUtil.getCellData(1, 4)
        );

        //For report
        ExcelUtil.setRowNumber(1);
        ExcelUtil.setColumnNumber(6);

        Thread.sleep(2000); //TODO: make with explicit wait
        Assert.assertEquals("http://localhost:4200/login", this.driver.getCurrentUrl());
    }

    @Test
    public void registerWithInvalidEmail_mustShownCorrectErrorMsg_2_fail() throws Exception {
        this.registerPage.directRegister(
                ExcelUtil.getCellData(2, 1),
                ExcelUtil.getCellData(2, 2),
                ExcelUtil.getCellData(2, 3),
                ExcelUtil.getCellData(2, 4)
        );

        //For report
        ExcelUtil.setRowNumber(2);
        ExcelUtil.setColumnNumber(5);

        Assert.assertEquals("Incorrect email!", this.registerPage.getNotificationMsg());
    }

    /*//@Test
    public void registerWithShortPassword_registerBtnMustBeUnavailable_3(){
        this.registerPage.directRegister("test", this.uniqueEmail, "1234", "1234");

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }

    //@Test
    public void registerWithMismatchConfirmPassword_registerBtnMustBeUnavailable_4(){
        this.registerPage.typeName("test");
        this.registerPage.typeEmail(this.uniqueEmail);
        this.registerPage.typePassword("123456");
        this.registerPage.typeConfirmPassword("asdfghj");

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }*/
}
