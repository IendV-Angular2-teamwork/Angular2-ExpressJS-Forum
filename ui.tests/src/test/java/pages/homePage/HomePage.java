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

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<WebElement> getAllFlowers(){
        List<WebElement> flowerList = this.driver.findElements(flowersUl);
        return flowerList;
    }
}
