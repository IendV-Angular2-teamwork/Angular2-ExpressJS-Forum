package tests.flowers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.flowers.AddNewFlowerPage;
import pages.flowers.FlowerDetailsPage;
import pages.homePage.HomePage;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.drivers.ChooseDriver;
import utils.excelUtils.ExcelUtil;
import utils.listeners.TestListener;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Listeners({ TestListener.class })

public class TestBuyFlower extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private AddNewFlowerPage addNewFlowerPage;
    private HomePage homePage;
    private FlowerDetailsPage flowerDetailsPage;
    private String uniqueEmail;
    private String email;
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
        this.flowerDetailsPage = new FlowerDetailsPage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        ExcelUtil.setExcelFileSheet("BuyFlowerData");

        this.email = this.uniqueEmail;
        this.password = "123456";
        this.name = ExcelUtil.getCellData(1,1);

        this.flowerName = ExcelUtil.getCellData(1, 3);
        this.flowerCategory = ExcelUtil.getCellData(1,4);
        this.flowerBlossom = ExcelUtil.getCellData(1,5);
        this.flowerPrice = ExcelUtil.getCellData(1,6);
        this.flowerImageUrl = ExcelUtil.getCellData(1,7);

        this.registerPage.directRegister(this.name, this.email, this.password, this.password);
        Thread.sleep(2000); //TODO: and other classes
        this.loginPage.loginUser(this.email,this.password);
        Thread.sleep(5000);



        this.addNewFlowerPage.clickNewFlowerBtn();
        Thread.sleep(2000);
        this.addNewFlowerPage.addNewFlower(
                this.flowerName,
                this.flowerCategory,
                this.flowerBlossom,
                this.flowerPrice,
                this.flowerImageUrl
        );
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = this.testName + ".png";
        FileUtils.copyFile(scrFile, new File("c:\\TestScreenshots\\DetailsPage\\" + fileName));

       // this.driver.quit();
    }

    @Test
    public void buyFlower_buyingSuccessful() throws Exception {
        this.testName = ExcelUtil.getCellData(1,0);
        ///////////
        this.flowerDetailsPage.clickLogoutBtn();
        Thread.sleep(2000);
        this.driver.get("http://localhost:4200/register");
        Thread.sleep(2000);

        this.name = ExcelUtil.getCellData(1,2);
        this.email = UUID.randomUUID() + "@abv.bg";
        this.password = "123456";

        this.registerPage.directRegister(this.name, this.email, this.password, this.password);
        Thread.sleep(2000);
        this.loginPage.loginUser(this.email, this.password);
        Thread.sleep(2000);
        /////////////

        this.homePage.enterDetailsPageLastAddedFlower();
        Thread.sleep(2000);
        this.flowerDetailsPage.makeRequestForFlower(
                ExcelUtil.getCellData(1, 8),
                ExcelUtil.getCellData(1,9),
                ExcelUtil.getCellData(1, 10)
        );

        ExcelUtil.setActualBehaviorCell(this.addNewFlowerPage.getNotificationMsg(), 1, 12);
        ExcelUtil.setStatusCell(1, 13);

        Assert.assertEquals(
                this.addNewFlowerPage.getNotificationMsg(),
                ExcelUtil.getCellData(1,11)
        );
    }
}
