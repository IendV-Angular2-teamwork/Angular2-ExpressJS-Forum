package tests.homePage;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.StringUtil;
import org.codehaus.plexus.util.StringUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.addNewFlowerPage.AddNewFlowerPage;
import pages.homePage.HomePage;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;
import utils.drivers.ChooseDriver;
import utils.excelUtils.ExcelUtil;
import utils.listeners.TestListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Listeners({ TestListener.class })

public class TestHomePage extends BaseTest{
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private AddNewFlowerPage addNewFlowerPage;
    private HomePage homePage;
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

    @BeforeMethod
    public void setUp() throws InterruptedException {
        this.driver = ChooseDriver.setChromeDriver();
        //this.driver = ChooseDriver.setFirefoxDriver();
        this.driver.get("http://localhost:4200/register");
        this.driver.manage().window().maximize();
        this.loginPage = new LoginPage(driver);
        this.registerPage = new RegisterPage(driver);
        this.addNewFlowerPage = new AddNewFlowerPage(driver);
        this.homePage = new HomePage(driver);
        this.uniqueEmail = UUID.randomUUID() + "@gmail.com";

        this.email = this.uniqueEmail;
        this.password = "123456";
        this.name = "test";
        this.flowerName = "test rose";
        this.flowerCategory = "rosses";
        this.flowerBlossom = "red";
        this.flowerPrice = "100";
        this.flowerImageUrl = "http://www.mmtrbg.com/uploads/com_article/gallery/c5664e01e57f4edb2b69a47edd313d614dd4b3f3.jpg";

        ExcelUtil.setExcelFileSheet("LoginData"); //TODO

        this.registerPage.directRegister(this.name, this.email, this.password, this.password);
        Thread.sleep(2000);
        this.driver.get("http://localhost:4200/login");
        Thread.sleep(2000);
        this.loginPage.loginUser(this.email,this.password);
        Thread.sleep(5000);


        //for (int i = 0; i < 5; i++) {
        //    this.addNewFlowerPage.clickNewFlowerBtn();
        //    Thread.sleep(2000);
       ////    this.addNewFlowerPage.addNewFlower(
       //      this.flowerName,
       //      this.flowerCategory,
       //      this.flowerBlossom,
       //      this.flowerPrice,
       //      this.flowerImageUrl
       //    );
       //    Thread.sleep(5000);
       //}
    }

    @AfterMethod
    public void tearDown() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName = this.testName + ".png";
        FileUtils.copyFile(scrFile, new File("c:\\TestScreenshots\\HomePage\\" + fileName));

        this.driver.quit();
    }

    @Test
    public void checkCorrectAddedFlowers_55() throws InterruptedException {
        this.testName = "Check correct added flowers";

        this.homePage.clickHomePageBtn();

        Thread.sleep(2000);


        List<WebElement> flowerList = this.homePage.getAllFlowers();
        List<Integer> listId = new ArrayList<Integer>();

        for(WebElement f:flowerList){
            List<WebElement> ids = f.findElements(By.className("id"));
            for(WebElement id:ids){
                String[] arr = id.getText().split("\\s+");
                listId.add(Integer.parseInt(arr[1]));
            }
        }

        for(int id:listId){
            //System.out.println(id);
        }

        boolean test = false;
        for (int i = listId.size() - 1; i > 0; i--) {
            System.out.println(i + " = " + listId.get(i));
            if(i == listId.get(i)){
                test = true;
            }else{
                test = false;
            }
        }

        System.out.println("Result = " + test);

        //TODO: трябва да обърна листа и да изкарам последния елемент
        // след което, да го откарам това чудо в HomePage
        // и да направя подобно нещо и за другите полета
    }
}
