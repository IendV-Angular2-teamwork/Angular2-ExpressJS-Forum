package pages.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class HomePage extends BasePage {
    private WebDriver driver;

    private By showStatisticBtn = By.xpath("/html/body/app-root/div/div/div/home/div/div/button");
    private By searchBarField = By.id("searchParam");
    private By searchBtn = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/div/search/div/form/button");
    private By flowersUl = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/ul");
    private By lastAddedFlowerName = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/ul/li[1]/h3");
    private By lastAddedFlowerCategory = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/ul/li[1]/p[2]");
    private By lastAddedFlowerId = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/ul/li[1]/p[3]");
    private By lastAddedFlowerCreator = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/ul/li[1]/p[4]");
    private By lastAddedFlowerImage = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/ul/li[1]/img");
    private By viewDetailsBtnLastAddedFlower = By.xpath("/html/body/app-root/div/div/div/home/div/div/div/ul/li[1]/h3/button");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<WebElement> getAllFlowers(){
        List<WebElement> flowerList = this.driver.findElements(flowersUl);
        return flowerList;
    }

    public String getLastFlowerName(){
        String[] nameArr =  this.driver.findElement(lastAddedFlowerName).getText().split("\\s+");
        return nameArr[2];
    }

    public String getLastFlowerCategory(){
        String[] categoryArr =  this.driver.findElement(lastAddedFlowerCategory).getText().split("\\s+");
        return  categoryArr[1];
    }

    public String getLastFlowerId(){
        String[] idArr =  this.driver.findElement(lastAddedFlowerId).getText().split("\\s+");
        return  idArr[1];
    }

    public String getLastFlowerCreatorEmail(){
        String[] emailArr =  this.driver.findElement(lastAddedFlowerCreator).getText().split("\\s+");
        return  emailArr[2];
    }

    public String getLastFlowerImageUrl(){
        return this.driver.findElement(lastAddedFlowerImage).getAttribute("src");
    }

    public void enterDetailsPageLastAddedFlower(){
        this.driver.findElement(viewDetailsBtnLastAddedFlower).click();
    }
}
