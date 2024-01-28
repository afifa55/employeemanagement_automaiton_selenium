package Base;

import Page.DashboardPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeTest
    public void basesetup() {

        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
    @AfterTest
    public void closedriver(){
        DashboardPage dashboardPage= new DashboardPage(driver);
        dashboardPage.dologout();
        driver.close();
    }
}
