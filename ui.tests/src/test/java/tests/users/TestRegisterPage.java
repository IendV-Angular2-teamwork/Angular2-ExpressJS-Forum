package tests.users;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.registerPage.RegisterPage;
import pages.registerPage.RegisterPageAsserter;
import tests.BaseTest;
import utils.excelUtils.ExcelUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestRegisterPage extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    //private RegisterPageAsserter registerPageAsserter;
    private String uniqueEmail;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeWebDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.registerPage = new RegisterPage(driver);
        //this.registerPageAsserter = new RegisterPageAsserter(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        ExcelUtil.setExcelFileSheet("RegisterData"); //TODO : Step 6
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }

    @Test
    public void registerWithValidCredentials_mustRegisterSuccessful_1(){

        this.registerPage.directRegister("test", this.uniqueEmail, "123456", "123456");

        Assert.assertEquals(
                "You have successfully signed up! Now you should be able to log in.",
                this.registerPage.getNotificationMsg()
        );
        //TODO: asserterClass
        //TODO: Data binding
    }

    @Test
    public void registerWithValidCredentials_mustRegisterSuccessful_1_testRedirect() throws InterruptedException {
        this.registerPage.directRegister("test", this.uniqueEmail, "123456", "123456");

        Thread.sleep(2000); //TODO: make with explicit wait
        Assert.assertEquals("http://localhost:4200/login", this.driver.getCurrentUrl());
    }

    @Test
    public void registerWithInvalidEmail_mustShownCorrectErrorMsg_2_fail(){
        this.registerPage.directRegister("test", "testMail.bg", "123456", "123456");

        Assert.assertEquals("Incorrect email!", this.registerPage.getNotificationMsg());
    }

    @Test
    public void registerWithShortPassword_registerBtnMustBeUnavailable_3(){
        this.registerPage.directRegister("test", this.uniqueEmail, "1234", "1234");

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }

    @Test
    public void registerWithMismatchConfirmPassword_registerBtnMustBeUnavailable_4(){
        this.registerPage.typeName("test");
        this.registerPage.typeEmail(this.uniqueEmail);
        this.registerPage.typePassword("123456");
        this.registerPage.typeConfirmPassword("asdfghj");

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }
}
