import Utils.DriverFactory;
import Utils.ElementOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestFile {

    static Logger logger = LogManager.getLogger(TestFile.class);
    public static void main(String args[]){

        DriverFactory driverFactory = new DriverFactory();
        System.out.println(System.getProperty("user.dir"));
        WebDriver driver = driverFactory.createDriver(new ChromeOptions(),System.getProperty("user.dir")+"\\Tools\\chromedriver.exe");

        driver.get("https://only-testing-blog.blogspot.com/2014/01/textbox.html?m=1");
        driver.manage().window().maximize();

        WebElement field1 = driver.findElement(By.xpath(".//input[@id='text1']"));
        field1.sendKeys("field1");

        ElementOperations elementOperations = new ElementOperations(driver);
        String field2Xpath = ".//input[@id='text2']";
        WebElement field2 = elementOperations.waitForTheAppearanceOfElement(By.xpath(field2Xpath),30L);
        elementOperations.waitForTheElementToBeEnabled(By.xpath(field2Xpath),30L);
        field2.sendKeys("Field2");

        logger.info("Debug Point");

        driver.close();
        driver.quit();

    }
}
