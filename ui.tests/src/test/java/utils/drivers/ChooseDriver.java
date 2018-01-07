package utils.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChooseDriver {

    public static WebDriver setChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\utils\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }

    public static WebDriver setFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver", "src\\test\\java\\utils\\drivers\\geckodriver.exe");
        return new FirefoxDriver();
    }
}
