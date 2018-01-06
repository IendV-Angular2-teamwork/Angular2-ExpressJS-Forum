package tests.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.excelUtils.ExcelUtil;
import utils.listeners.TestListener;

import java.util.UUID;

@Listeners({ TestListener.class })

public class TestRegisterPage extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private String uniqueEmail;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeWebDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.registerPage = new RegisterPage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        ExcelUtil.setExcelFileSheet("RegisterData");
    } //TODO: This on Base Page class

    @AfterMethod
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
        ExcelUtil.setColumnNumber(6);

        Assert.assertEquals(
                ExcelUtil.getCellData(1,5),
                this.registerPage.getNotificationMsg()
        );
    }

    @Test
    public void registerWithValidCredentials_mustRegisterSuccessful_1_testRedirect() throws Exception {
        this.registerPage.directRegister(
                ExcelUtil.getCellData(2, 1),
                this.uniqueEmail,
                ExcelUtil.getCellData(2, 3),
                ExcelUtil.getCellData(2, 4)
        );

        //For report
        ExcelUtil.setRowNumber(2);
        ExcelUtil.setColumnNumber(6);

        Thread.sleep(2000);
        Assert.assertEquals(ExcelUtil.getCellData(2,5), this.driver.getCurrentUrl());
    }

    @Test
    public void registerWithInvalidEmail_mustShownCorrectErrorMsg_2_fail() throws Exception {
        this.registerPage.directRegister(
                ExcelUtil.getCellData(3, 1),
                ExcelUtil.getCellData(3, 2),
                ExcelUtil.getCellData(3, 3),
                ExcelUtil.getCellData(3, 4)
        );

        //For report
        ExcelUtil.setRowNumber(3);
        ExcelUtil.setColumnNumber(6);

        Assert.assertEquals(ExcelUtil.getCellData(3,5), this.registerPage.getNotificationMsg());
    }

    @Test
    public void registerWithShortPassword_registerBtnMustBeUnavailable_3() throws Exception {
        this.registerPage.directRegister(
                ExcelUtil.getCellData(4, 1),
                this.uniqueEmail,
                ExcelUtil.getCellData(4, 3),
                ExcelUtil.getCellData(4, 4)
        );

        //For report
        ExcelUtil.setRowNumber(4);
        ExcelUtil.setColumnNumber(6);

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }

    @Test
    public void registerWithMismatchConfirmPassword_registerBtnMustBeUnavailable_4() throws Exception {
        this.registerPage.directRegister(
                ExcelUtil.getCellData(5,1),
                this.uniqueEmail,
                ExcelUtil.getCellData(5,3),
                ExcelUtil.getCellData(5,4)
        );

        //For report
        ExcelUtil.setRowNumber(5);
        ExcelUtil.setColumnNumber(6);

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }
}
