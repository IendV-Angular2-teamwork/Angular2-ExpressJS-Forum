package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    private WebDriver driver;

    private By addNewFlowerBtn = By.xpath("/html/body/app-root/div/nav-bar/nav/a[2]");
    private By homePageBtn = By.xpath("/html/body/app-root/div/nav-bar/nav/a[1]");

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public String getNotificationMsg(){
        WebElement notificationMsg = this.driver.findElement(By.className("toast-message"));
        return notificationMsg.getText();
    }

    public void clickNewFlowerBtn(){
        this.driver.findElement(addNewFlowerBtn).click();
    }

    public void clickHomePageBtn(){
        this.driver.findElement(homePageBtn).click();
    }
}
