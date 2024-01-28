package Runner;

import Base.Setup;
import Page.DashboardPage;
import Page.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PIMTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage login;
    @BeforeTest
   public void dologin() throws IOException, ParseException {
        login = new LoginPage(driver);
        JSONObject userObject= Utils.loadJsonFile("./src/test/resources/user.json");
        String username= (String) userObject.get("username");
        String password= (String) userObject.get("password");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        login.dologin(username,password);

    }
    @Test(priority = 2,description = "Use can view existing Employee List")
    public void searchEmployeeinfo() throws InterruptedException {
        dashboardPage= new DashboardPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();
        Thread.sleep(5000);
        String isUserFound = driver.findElements(By.className("oxd-text--span")).get(12).getText();
        Thread.sleep(3000);
        System.out.println(isUserFound);
        Assert.assertTrue(isUserFound.contains("Records Found"));

//        JavascriptExecutor js= (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,500)");
    }
    @Test(priority = 3,description = "Use can search Employee by Employee Status")
    public void selectEmployeeStatus() throws InterruptedException {
        dashboardPage= new DashboardPage(driver);
        dashboardPage.dropdowns.get(0).click();
        dashboardPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dashboardPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dashboardPage.dropdowns.get(0).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Thread.sleep(1000);
        Utils.doscroll(driver,500);
        Thread.sleep(1000);
    }
}
