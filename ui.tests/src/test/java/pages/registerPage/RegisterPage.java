package pages.registerPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private WebDriver driver;

    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("confirmPassword");
    private By registerBtn = By.xpath("/html/body/app-root/div/div/div/register/form/button");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void typeName(String name){
        this.driver.findElement(nameField).sendKeys(name);
    }

    public void typeEmail(String email){
        this.driver.findElement(emailField).sendKeys(email);
    }

    public void typePassword(String password){
        this.driver.findElement(passwordField).sendKeys(password);
    }

    public void typeConfirmPassword(String confirmPassword){
        this.driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    private void clickRegisterBtn(){
        this.driver.findElement(registerBtn).click();
    }

    public void directRegister(String name, String email, String password, String confirmPassword){
        typeName(name);
        typeEmail(email);
        typePassword(password);
        typeConfirmPassword(confirmPassword);
        clickRegisterBtn();
    }


    public String getNotificationMsg(){
        WebElement notificationMsg = driver.findElement(By.className("toast-message"));
        return notificationMsg.getText();
    }

    public Boolean isRegisterBtnClickable(){
        WebElement registerButton = this.driver.findElement(registerBtn);
        return registerButton.isEnabled();
    }
}
