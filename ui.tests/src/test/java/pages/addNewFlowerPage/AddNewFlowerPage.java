package pages.addNewFlowerPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class AddNewFlowerPage extends BasePage{
    private WebDriver driver;

    private By flowerNameField = By.id("name");
    private By categoryField = By.id("category");
    private By blossomField = By.id("blossom");
    private By priceField = By.id("price"); // TODO: catch arrow up and down
    private By imageField = By.id("image");
    private By addFlowerBtn = By.xpath("/html/body/app-root/div/div/div/flowers/form/button");

    public AddNewFlowerPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void addNewFlower(String flowerName, String category, String blossomType, String price, String imageUrl){
        this.driver.findElement(flowerNameField).sendKeys(flowerName);
        this.driver.findElement(categoryField).sendKeys(category);
        this.driver.findElement(blossomField).sendKeys(blossomType);
        this.driver.findElement(priceField).sendKeys(price);
        this.driver.findElement(imageField).sendKeys(imageUrl);

        this.driver.findElement(addFlowerBtn).click();
    }

    //public String getNotificationMsg(){
    //    WebElement notificationMsg = driver.findElement(By.className("toast-message"));
    //    return notificationMsg.getText();
    //}

    //TODO: isAddFlowerBtnClickable()
}
