package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    private WebDriver driver;

    protected BasePage(WebDriver driver){
        this.driver = driver;
    }

    public String getNotificationMsg(){
        WebElement notificationMsg = this.driver.findElement(By.className("toast-message"));
        return notificationMsg.getText();
    }
}
