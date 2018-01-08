package pages.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class LoginPage extends BasePage {
    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginBtn = By.xpath("/html/body/app-root/div/div/div/login/form/button");

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void loginUser(String email, String password){
        this.driver.findElement(emailField).sendKeys(email);
        this.driver.findElement(passwordField).sendKeys(password);
        this.driver.findElement(loginBtn).click();
    }
}
