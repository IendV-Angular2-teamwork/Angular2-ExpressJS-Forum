package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;
import utils.excelUtils.ExcelUtil;

import java.util.UUID;

public class BasePage {
    private WebDriver driver;

    private By addNewFlowerBtn = By.xpath("/html/body/app-root/div/nav-bar/nav/a[2]");
    private By homePageBtn = By.xpath("/html/body/app-root/div/nav-bar/nav/a[1]");
    private By logoutBtn = By.xpath("/html/body/app-root/div/nav-bar/nav/a[3]");

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

    public void clickLogoutBtn(){
        this.driver.findElement(logoutBtn).click();
    }

    public String getFlowerIdCallOnFlowerDetailsPage(){
        String[] urlArr =  this.driver.getCurrentUrl().split("\\/+");
        return urlArr[4];
    }
}
