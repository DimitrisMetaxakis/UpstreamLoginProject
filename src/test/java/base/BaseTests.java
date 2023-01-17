package base;

import common.utilities.BaseActions;
import navigation.WindowManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProfilePage;

import java.time.Duration;

/**
 * Class contains logic that is executed before and after every test
 */
public class BaseTests {
    private WebDriver driver;

    protected BaseActions baseActions;
    protected WindowManager windowManager;
    protected LoginPage loginPage;


    /**
     * Initialize browser
     * Initialize Implicit Wait
     *
     * @param hostUrl found in testng.xml
     */
    @BeforeClass
    @Parameters({"hostUrl", "browser", "timeout"})
    public void setUp(String hostUrl, String browser, int timeout) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
        } // More browsers can be added
        driver.get(hostUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

        // Enable JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var script = document.createElement('script');script.src =\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\";document.getElementsByTagName('head')[0].appendChild(script);");

        baseActions = new BaseActions(driver);
        windowManager = new WindowManager(driver);
        loginPage = new LoginPage(driver);


    }

    /**
     * Close browser
     */
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}