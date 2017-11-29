package pages.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    By emailField = By.id("email");
    By passwordField = By.id("password");
    By loginBtn = By.xpath("/html/body/app-root/div/div/div/login/form/button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void loginUser(String email, String password){
        this.driver.findElement(emailField).sendKeys(email);
        this.driver.findElement(passwordField).sendKeys(password);
        this.driver.findElement(loginBtn).click();
    }

    public String getNotificationMsg(){
        WebElement notificationMsg = driver.findElement(By.className("toast-message"));
        return notificationMsg.getText();
    }
}
