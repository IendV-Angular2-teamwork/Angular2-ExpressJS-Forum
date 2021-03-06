package tests.users;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.drivers.ChooseDriver;
import utils.excelUtils.ExcelUtil;
import utils.initialization.Init;
import utils.listeners.TestListener;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Listeners({ TestListener.class })

public class TestLoginPage extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private String uniqueEmail;
    private String email;
    private String password;
    private String name;
    private String testName;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        this.driver = ChooseDriver.setChromeDriver();
        //this.driver = ChooseDriver.setFirefoxDriver();
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.loginPage = new LoginPage(driver);
        this.registerPage = new RegisterPage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";
        this.password = "123456";
        this.name = "test";

        ExcelUtil.setExcelFileSheet("LoginData");

        Init.registerNewUser(this.registerPage, this.name, this.uniqueEmail, this.password);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = this.testName + ".png";
        FileUtils.copyFile(scrFile, new File("c:\\TestScreenshots\\LoginPage\\" + fileName));

        this.driver.quit();
    }

    @Test
    public void loginWithValidCredentials_loginSuccessful_26() throws Exception {
        this.testName = ExcelUtil.getCellData(1,0);

        this.loginPage.loginUser(
                this.uniqueEmail,
                this.password
        );

        Thread.sleep(2000);

        ExcelUtil.setActualBehaviorCell(this.loginPage.getNotificationMsg(),1,4);
        ExcelUtil.setStatusCell(1,5);

        Assert.assertEquals(
                ExcelUtil.getCellData(1, 3),
                this.loginPage.getNotificationMsg()
        );
    }

    @Test
    public void loginWithInvalidEmail_mustShownNotificationAndStayAtLoginPage_27() throws Exception {
        this.testName = ExcelUtil.getCellData(2,0);

        this.loginPage.loginUser(
                ExcelUtil.getCellData(2, 1),
                this.password
        );

        ExcelUtil.setActualBehaviorCell(this.loginPage.getNotificationMsg(),2,4);
        ExcelUtil.setStatusCell(2,5);

        Assert.assertEquals(
                ExcelUtil.getCellData(2, 3),
                this.loginPage.getNotificationMsg()
        );
    }

    @Test
    public void loginWithInvalidEmail_mustShownNotificationAndStayAtLoginPage_27_testRedirect_fail() throws Exception {
        this.testName = ExcelUtil.getCellData(3,0);

        this.loginPage.loginUser(
                ExcelUtil.getCellData(3,1),
                this.password
        );

        ExcelUtil.setActualBehaviorCell(this.driver.getCurrentUrl(),3,4);
        ExcelUtil.setStatusCell(3,5);

        Assert.assertEquals(
                ExcelUtil.getCellData(3,3),
                this.driver.getCurrentUrl()
        );
    }

    @Test
    public void loginWithInvalidPassword_mustShownNotificationAndStayAtLoginPage_28() throws Exception {
        this.testName = ExcelUtil.getCellData(4,0);

        this.loginPage.loginUser(
                this.uniqueEmail,
                ExcelUtil.getCellData(4,2)
        );

        ExcelUtil.setActualBehaviorCell(this.loginPage.getNotificationMsg(),4, 4);
        ExcelUtil.setStatusCell(4,5);

        Assert.assertEquals(
                ExcelUtil.getCellData(4,3),
                this.loginPage.getNotificationMsg());

    }

    @Test
    public void loginWithInvalidPassword_mustShownNotificationAndStayAtLoginPage_28_testRedirect_fail() throws Exception {
        this.testName = ExcelUtil.getCellData(5,0);

        this.loginPage.loginUser(
                this.uniqueEmail,
                ExcelUtil.getCellData(5,2)
        );

        ExcelUtil.setActualBehaviorCell(this.driver.getCurrentUrl(),5,4);
        ExcelUtil.setStatusCell(5,5);

        Assert.assertEquals(
                ExcelUtil.getCellData(5,3),
                this.driver.getCurrentUrl());
    }
}
