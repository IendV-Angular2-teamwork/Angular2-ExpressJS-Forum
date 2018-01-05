package tests.users;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import tests.BaseTest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestLoginPage extends BaseTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private String uniqueEmail;
    private String email;
    private String password;

    @BeforeClass
    public void setUp(){
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

        //this.registerPage.directRegister("test", this.email, this.password, this.password);

        //Thread.sleep(2000);

        this.driver.get("http://localhost:4200/login");
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }

    @Test
    public void loginWithValidCredentials_loginSuccessful_26(){
        this.loginPage.loginUser(this.email, this.password);

        Assert.assertEquals("You have successfully logged in!", this.loginPage.getNotificationMsg());
    }

    @Test
    public void loginWithInvalidEmail_mustShownNotificationAndStayAtLoginPage_27_fail(){
        this.loginPage.loginUser("test@test.bg", this.password);

        Assert.assertEquals("Incorrect email or password", this.loginPage.getNotificationMsg());
        Assert.assertEquals("http://localhost:4200/login", this.driver.getCurrentUrl());

        //TODO: Optimization here, must have only one assert
    }

    @Test
    public void loginWithInvalidPassword_mustShownNotificationAndStayAtLoginPage_28_fail(){
        this.loginPage.loginUser(this.email, "qwerty");

        Assert.assertEquals("Incorrect email or password", this.loginPage.getNotificationMsg());
        Assert.assertEquals("http://localhost:4200/login", this.driver.getCurrentUrl());

        //TODO: Optimization here, must have only one assert
    }
}
