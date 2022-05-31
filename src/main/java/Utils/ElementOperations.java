package Utils;

import com.google.common.base.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import javax.xml.bind.Element;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ElementOperations {

    private WebDriver driver = null;
    private static Logger logger = LogManager.getLogger(ElementOperations.class);
    public ElementOperations(){
        driver = GlobalVariables.driver.get();
    }

    public ElementOperations(WebDriver driverFromInput){
        driver = driverFromInput;
    }

    public void clickElement(By xpath){
        WebElement element = driver.findElement(xpath);
        element.click();
    }

    public void EnterText(By xpath,String text, boolean shouldClearText){

        WebElement element = driver.findElement(xpath);
        if(shouldClearText){
            element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        }
        element.sendKeys(text);
    }

    public void checkABox(By xpath){

        WebElement element = driver.findElement(xpath);
        if(element.isSelected()){
            return;
        }
        element.click();
    }

    public void uncheckABox(By xpath){

        WebElement element = driver.findElement(xpath);
        if(!element.isSelected()){
            return;
        }
        element.click();
    }

    public void selectAValueFromDropdown(By xpath, boolean byText,String textOrValue){
        Select dropdown = new Select(driver.findElement(xpath));

        if(byText){
            dropdown.selectByVisibleText(textOrValue);
        }else{
            dropdown.selectByValue(textOrValue);
        }
    }
    public void uploadFile(By xpath, String filePath){

        WebElement uploadFileElement = driver.findElement(xpath);
        //verify whether the update is succesful or not
    }

    //wait methods

    public WebElement waitForTheAppearanceOfElement(By xpath, long timeoutInLong){

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInLong))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement elementToCheck = driver.findElement(xpath);
                if(elementToCheck.isDisplayed()){
                    logger.info("Element Found with Xpath: "+xpath);
                }
                return elementToCheck;
            }
        });

        return element;

    }

    public void waitForTheDisappearanceOfElement(By xpath, int timeoutInSeconds){

    }

    public void waitForTheElementToBeEnabled(By xpath, Long timeoutInLong){

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInLong))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class, ElementNotInteractableException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(xpath);
                if(element.isEnabled()){
                    logger.info("Element Found with Xpath: "+xpath+" and is Enabled");
                }else{
                    throw new ElementNotInteractableException("Element Not Interactable");
                }
                return element;
            }
        });
    }
}
