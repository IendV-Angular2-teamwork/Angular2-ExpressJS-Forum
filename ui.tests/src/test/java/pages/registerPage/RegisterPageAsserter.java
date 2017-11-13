package pages.registerPage;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class RegisterPageAsserter {

    private WebDriver driver;

    public RegisterPageAsserter(WebDriver driver){
        this.driver = driver;
    }

    public void assertRegisterWithValidCredentials(RegisterPage registerPage){
        Assert.assertEquals("bla bla", registerPage.getNotificationMsg());
    }
}
