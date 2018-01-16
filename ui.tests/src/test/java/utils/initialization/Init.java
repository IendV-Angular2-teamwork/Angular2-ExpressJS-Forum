package utils.initialization;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.flowers.AddNewFlowerPage;
import pages.homePage.HomePage;
import pages.loginPage.LoginPage;
import pages.registerPage.RegisterPage;

import java.util.UUID;

public class Init {

    public static void registerNewUser(
            RegisterPage registerPage,
            String name,
            String email,
            String password
    ) throws InterruptedException {
        registerPage.directRegister(name, email, password, password);
        Thread.sleep(5000);
    }

    public static void registerNewUserAndLogin(
            RegisterPage registerPage,
            LoginPage loginPage,
            String name,
            String email,
            String password
    ) throws InterruptedException {
        registerPage.directRegister(name, email, password, password);
        Thread.sleep(2000);
        loginPage.loginUser(email, password);
        Thread.sleep(5000);
    }

    public static String addNewFlowerAndReturnFlowerId(
            AddNewFlowerPage addNewFlowerPage,
            HomePage homePage,
            String flowerName,
            String flowerCategory,
            String flowerBlossom,
            String flowerPrice,
            String flowerImageUrl
    ) throws InterruptedException {
        addNewFlowerPage.clickNewFlowerBtn();
        Thread.sleep(2000);
        addNewFlowerPage.addNewFlower(
                flowerName,
                flowerCategory,
                flowerBlossom,
                flowerPrice,
                flowerImageUrl
        );
        String flowerId = homePage.getFlowerIdCallOnFlowerDetailsPage();
        Thread.sleep(5000);

        return flowerId;
    }

    public static void registerAndLoginWithSecondUser(
            WebDriver driver,
            BasePage basePage,
            String password,
            RegisterPage registerPage,
            LoginPage loginPage
    ) throws Exception {
        basePage.clickLogoutBtn();
        Thread.sleep(2000);
        driver.get("http://localhost:4200/register");
        Thread.sleep(2000);

        String name = "secondTestUser";
        String email = UUID.randomUUID() + "@abv.bg";
        password = "123456";

        registerPage.directRegister(name, email, password, password);
        Thread.sleep(2000);
        loginPage.loginUser(email, password);
        Thread.sleep(2000);
    }
}
