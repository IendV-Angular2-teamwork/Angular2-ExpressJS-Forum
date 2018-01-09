package tests.users;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.drivers.ChooseDriver;
import utils.excelUtils.ExcelUtil;
import utils.listeners.TestListener;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Listeners({ TestListener.class })

public class TestRegisterPage extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private String uniqueEmail;
    private String testName;

    @BeforeMethod
    public void setUp(){
        this.driver = ChooseDriver.setChromeDriver();
        //this.driver = ChooseDriver.setFirefoxDriver();
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.registerPage = new RegisterPage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        ExcelUtil.setExcelFileSheet("RegisterData");
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = this.testName + ".png";
        FileUtils.copyFile(scrFile, new File("c:\\TestScreenshots\\RegisterPage\\" + fileName));

        this.driver.quit();
    }

    @Test
    public void registerWithValidCredentials_mustRegisterSuccessful_1() throws Exception {
        this.testName = ExcelUtil.getCellData(1, 0);

        this.registerPage.directRegister(
                ExcelUtil.getCellData(1, 1),
                this.uniqueEmail,
                ExcelUtil.getCellData(1, 3),
                ExcelUtil.getCellData(1, 4)
        );

        Thread.sleep(2000);

        ExcelUtil.setActualBehaviorCell(this.registerPage.getNotificationMsg(),1,6);
        ExcelUtil.setStatusCell(1, 7);

        Assert.assertEquals(
                ExcelUtil.getCellData(1,5),
                this.registerPage.getNotificationMsg()
        );
    }

    @Test
    public void registerWithValidCredentials_mustRegisterSuccessful_1_testRedirect() throws Exception {
        this.testName = ExcelUtil.getCellData(2,0);

        this.registerPage.directRegister(
                ExcelUtil.getCellData(2, 1),
                this.uniqueEmail,
                ExcelUtil.getCellData(2, 3),
                ExcelUtil.getCellData(2, 4)
        );

        ExcelUtil.setStatusCell(2,7);

        Thread.sleep(2000);
        ExcelUtil.setActualBehaviorCell(this.driver.getCurrentUrl(), 2, 6);
        Assert.assertEquals(ExcelUtil.getCellData(2,5), this.driver.getCurrentUrl());
    }

    @Test
    public void registerWithInvalidEmail_mustShownCorrectErrorMsg_2_fail() throws Exception {
        this.testName = ExcelUtil.getCellData(3,0);

        this.registerPage.directRegister(
                ExcelUtil.getCellData(3, 1),
                ExcelUtil.getCellData(3, 2),
                ExcelUtil.getCellData(3, 3),
                ExcelUtil.getCellData(3, 4)
        );

        ExcelUtil.setActualBehaviorCell(this.registerPage.getNotificationMsg(), 3,6);
        ExcelUtil.setStatusCell(3,7);

        Assert.assertEquals(ExcelUtil.getCellData(3,5), this.registerPage.getNotificationMsg());
    }

    @Test
    public void registerWithShortPassword_registerBtnMustBeUnavailable_3() throws Exception {
        this.testName = ExcelUtil.getCellData(4,0);

        this.registerPage.directRegister(
                ExcelUtil.getCellData(4, 1),
                this.uniqueEmail,
                ExcelUtil.getCellData(4, 3),
                ExcelUtil.getCellData(4, 4)
        );

        ExcelUtil.setActualBehaviorCell(this.registerPage.isRegisterBtnClickable().toString(),4,6);
        ExcelUtil.setStatusCell(4,7);

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }

    @Test
    public void registerWithMismatchConfirmPassword_registerBtnMustBeUnavailable_4() throws Exception {
        this.testName = ExcelUtil.getCellData(5,0);

        this.registerPage.directRegister(
                ExcelUtil.getCellData(5,1),
                this.uniqueEmail,
                ExcelUtil.getCellData(5,3),
                ExcelUtil.getCellData(5,4)
        );

        ExcelUtil.setActualBehaviorCell(this.registerPage.isRegisterBtnClickable().toString(),5,6);
        ExcelUtil.setStatusCell(5,7);

        Assert.assertFalse(this.registerPage.isRegisterBtnClickable());
    }
}
