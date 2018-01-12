package tests.flowers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.flowers.AddNewFlowerPage;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.drivers.ChooseDriver;
import utils.excelUtils.ExcelUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TestAddNewFlower extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private AddNewFlowerPage addNewFlowerPage;
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
        this.addNewFlowerPage = new AddNewFlowerPage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        this.email = this.uniqueEmail;
        this.password = "123456";
        this.name = "test";

        ExcelUtil.setExcelFileSheet("AddNewFlowerData");

        this.registerPage.directRegister(this.name, this.email, this.password, this.password);
        Thread.sleep(2000);
        this.driver.get("http://localhost:4200/login");
        Thread.sleep(2000);
        this.loginPage.loginUser(this.email,this.password);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = this.testName + ".png";
        FileUtils.copyFile(scrFile, new File("c:\\TestScreenshots\\AddNewFlower\\" + fileName));

        this.driver.quit();
    }

    @Test
    public void addValidFlower_addFlowerSuccessful_41() throws Exception {
        this.testName = ExcelUtil.getCellData(1,0);

        this.addNewFlowerPage.clickNewFlowerBtn();

        this.addNewFlowerPage.addNewFlower(
                ExcelUtil.getCellData(1,1),
                ExcelUtil.getCellData(1,2),
                ExcelUtil.getCellData(1,3),
                ExcelUtil.getCellData(1,4),
                ExcelUtil.getCellData(1,5)
        );

        ExcelUtil.setActualBehaviorCell(this.addNewFlowerPage.getNotificationMsg(), 1, 7);
        ExcelUtil.setStatusCell(1, 8);

        Assert.assertEquals(
                this.addNewFlowerPage.getNotificationMsg(),
                ExcelUtil.getCellData(1,6)
        );
    }

    @Test
    public void addValidFlower_addFlowerSuccessful_41_testRedirect() throws Exception {
        this.testName = ExcelUtil.getCellData(2,0);

        this.addNewFlowerPage.clickNewFlowerBtn();

        this.addNewFlowerPage.addNewFlower(
                ExcelUtil.getCellData(2,1),
                ExcelUtil.getCellData(2,2),
                ExcelUtil.getCellData(2,3),
                ExcelUtil.getCellData(2,4),
                ExcelUtil.getCellData(2,5)
        );

        ExcelUtil.setActualBehaviorCell(
                String.valueOf(this.driver.getCurrentUrl().contains(ExcelUtil.getCellData(2,6))),
                2,
                7);
        ExcelUtil.setStatusCell(2, 8);

        Assert.assertTrue(
                this.driver.getCurrentUrl().contains(ExcelUtil.getCellData(2,6))
        );
    }
}
