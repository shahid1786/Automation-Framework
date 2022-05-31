package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    static Logger logger = LogManager.getLogger(DriverFactory.class);
    public WebDriver createDriver(ChromeOptions options, String driverPath){

        WebDriver driver = null;

        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver(options);
        return driver;
    }

    public void quitDriver(WebDriver driver){
        logger.info("Quitting driver");
        driver.close();
        driver.quit();
    }
}
