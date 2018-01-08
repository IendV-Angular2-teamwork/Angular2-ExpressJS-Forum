package tests.flowers;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.addNewFlowerPage.AddNewFlowerPage;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.drivers.ChooseDriver;
import utils.excelUtils.ExcelUtil;

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
        Thread.sleep(2000);
        this.driver.get("http://localhost:4200/new-flower");
    }

    @AfterMethod
    public void tearDown(){
        //this.driver.quit();
    }

    @Test
    public void test() throws InterruptedException { //TODO: Fix
        //this.driver.get("http://localhost:4200/new-flower");
        Thread.sleep(2000);
        this.addNewFlowerPage.addNewFlower("test","test","test","test","test");
    }
}
