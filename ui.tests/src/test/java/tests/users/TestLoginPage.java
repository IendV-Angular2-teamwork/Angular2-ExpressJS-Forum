package tests.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.excelUtils.ExcelUtil;
import utils.listeners.TestListener;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Listeners({ TestListener.class })

public class TestLoginPage extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private String uniqueEmail;
    private String email;
    private String password;
    private String name;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeWebDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.loginPage = new LoginPage(driver);
        this.registerPage = new RegisterPage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        this.email = this.uniqueEmail;
        this.password = "123456";
        this.name = "test";

        ExcelUtil.setExcelFileSheet("LoginData");

        this.registerPage.directRegister(this.name, this.email, this.password, this.password);

        Thread.sleep(2000);

        this.driver.get("http://localhost:4200/login");
    }

    @AfterMethod
    public void tearDown(){
        this.driver.quit();
    }

    @Test
    public void loginWithValidCredentials_loginSuccessful_26() throws Exception {
        this.loginPage.loginUser(
                this.email,
                this.password
        );

        ExcelUtil.setRowNumber(1);
        ExcelUtil.setColumnNumber(4);

        Assert.assertEquals(
                ExcelUtil.getCellData(1, 3),
                this.loginPage.getNotificationMsg()
        );
    }

    @Test
    public void loginWithInvalidEmail_mustShownNotificationAndStayAtLoginPage_27() throws Exception {
        this.loginPage.loginUser(
                ExcelUtil.getCellData(2, 1),
                this.password
        );

        ExcelUtil.setRowNumber(2);
        ExcelUtil.setColumnNumber(4);

        Assert.assertEquals(
                ExcelUtil.getCellData(2, 3),
                this.loginPage.getNotificationMsg()
        );
    }

    @Test
    public void loginWithInvalidEmail_mustShownNotificationAndStayAtLoginPage_27_testRedirect_fail() throws Exception {
        this.loginPage.loginUser(
                ExcelUtil.getCellData(3,1),
                this.password
        );

        ExcelUtil.setRowNumber(3);
        ExcelUtil.setColumnNumber(4);

        Assert.assertEquals(
                ExcelUtil.getCellData(3,3),
                this.driver.getCurrentUrl()
        );
    }

    /*@Test
    public void loginWithInvalidPassword_mustShownNotificationAndStayAtLoginPage_28_fail(){
        this.loginPage.loginUser(this.email, "qwerty");

        Assert.assertEquals("Incorrect email or password", this.loginPage.getNotificationMsg());
        Assert.assertEquals("http://localhost:4200/login", this.driver.getCurrentUrl());

        //TODO: Optimization here, must have only one assert
    }*/
}
