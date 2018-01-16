package tests.homePage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.flowers.AddNewFlowerPage;
import pages.homePage.HomePage;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.drivers.ChooseDriver;
import utils.excelUtils.ExcelUtil;
import utils.initialization.Init;
import utils.listeners.TestListener;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Listeners({ TestListener.class })

public class TestHomePage extends BaseTest{
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private AddNewFlowerPage addNewFlowerPage;
    private HomePage homePage;
    private String uniqueEmail;
    private String password;
    private String name;
    private String testName;
    private String flowerName;
    private String flowerCategory;
    private String flowerBlossom;
    private String flowerPrice;
    private String flowerImageUrl;
    private String flowerId;

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = ChooseDriver.setChromeDriver();
        //this.driver = ChooseDriver.setFirefoxDriver();
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.loginPage = new LoginPage(driver);
        this.registerPage = new RegisterPage(driver);
        this.addNewFlowerPage = new AddNewFlowerPage(driver);
        this.homePage = new HomePage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        ExcelUtil.setExcelFileSheet("AddNewFlowerForTestHomePage");

        this.password = "123456";
        this.name = ExcelUtil.getCellData(1,1);

        this.flowerName = ExcelUtil.getCellData(1, 3);
        this.flowerCategory = ExcelUtil.getCellData(1,4);
        this.flowerBlossom = ExcelUtil.getCellData(1,5);
        this.flowerPrice = ExcelUtil.getCellData(1,6);
        this.flowerImageUrl = ExcelUtil.getCellData(1,7);

        Init.registerNewUserAndLogin(this.registerPage, this.loginPage, this.name, this.uniqueEmail, this.password);

        this.flowerId = Init.addNewFlowerAndReturnFlowerId(
                this.addNewFlowerPage,
                this.homePage,
                this.flowerName,
                this.flowerCategory,
                this.flowerBlossom,
                this.flowerPrice,
                this.flowerImageUrl
        );
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = this.testName + ".png";
        FileUtils.copyFile(scrFile, new File("c:\\TestScreenshots\\HomePage\\" + fileName));

        //this.driver.quit();
    }

    @Test
    public void checkCorrectAddedFlower_successfulAddedAndDisplayedFlower_55() throws Exception {
        this.testName = ExcelUtil.getCellData(1,0);

        this.homePage.clickHomePageBtn();

        Thread.sleep(2000);

        boolean isAllMatch = this.homePage.getLastFlowerName().equals(this.flowerName) &&
                    this.homePage.getLastFlowerCategory().equals(this.flowerCategory) &&
                    this.homePage.getLastFlowerId().equals(this.flowerId) &&
                    this.homePage.getLastFlowerCreatorEmail().equals(this.uniqueEmail) &&
                    this.homePage.getLastFlowerImageUrl().equals(this.flowerImageUrl);

        ExcelUtil.setActualBehaviorCell(String.valueOf(isAllMatch), 1, 9);
        ExcelUtil.setStatusCell(1, 10);

        Assert.assertTrue(isAllMatch);
    }
}
