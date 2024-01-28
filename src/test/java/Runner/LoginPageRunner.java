package Runner;

import Base.Setup;
import Page.DashboardPage;
import Page.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Utils;

import java.io.IOException;

public class LoginPageRunner extends Setup {
    LoginPage login;
    @Test(priority = 1,description = "Use can do login successfully")
    public void dologin() throws IOException, ParseException {
        login = new LoginPage(driver);
        JSONObject userObject= Utils.loadJsonFile("./src/test/resources/user.json");
        String username= (String) userObject.get("username");
        String password= (String) userObject.get("password");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        login.dologin(username,password);


        WebElement headerText= driver.findElement(By.tagName("h6"));
        String header_actual= headerText.getText();
        String header_expected="Dashboard";
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(header_actual,header_expected);
        WebElement profilePictureElement= driver.findElement(By.className("oxd-userdropdown-img"));
        softAssert.assertTrue(profilePictureElement.isDisplayed());
        softAssert.assertAll();

    }


}
