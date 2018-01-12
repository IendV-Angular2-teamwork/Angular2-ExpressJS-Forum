package pages.flowers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class FlowerDetailsPage extends BasePage {
    private WebDriver driver;

    private By buyFlowerBtn = By.xpath("/html/body/app-root/div/div/div/flower-details/div[3]/button");
    private By deleteFlowerBtn = By.xpath("/html/body/app-root/div/div/div/flower-details/div[4]/button");
    private By continueBuyBtn = By.xpath("/html/body/app-root/div/div/div/purchase/div/button[2]");
    private By cancelOrderBtn = By.xpath("/html/body/app-root/div/div/div/purchase/div/button[1]");
    private By addressField = By.id("adress");
    private By emailField = By.id("email");
    private By paymentMethodSelect = By.id("paymentMethods");
    private By makeRequestBtn = By.xpath("/html/body/app-root/div/div/div/purchase/div[2]/form/button");

    public FlowerDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void makeRequestForFlower(String address, String email, String paymentMethod){
        JavascriptExecutor js = ((JavascriptExecutor) this.driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        this.driver.findElement(buyFlowerBtn).click();
        this.driver.findElement(continueBuyBtn).click();
        this.driver.findElement(addressField).sendKeys(address);
        this.driver.findElement(emailField).sendKeys(email);
        new Select(this.driver.findElement(paymentMethodSelect)).selectByVisibleText(paymentMethod);
        this.driver.findElement(makeRequestBtn).click();
    }



}
