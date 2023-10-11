package letskodeit;

import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Practice {
    WebDriver driver;

    static void waitFor(int second) throws InterruptedException {
        Thread.sleep(1000*second);

    }
    @After
    void driverClose(){
        driver.quit();
    }
    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.gecko.driver",
                "/Users/sadaqur.rahman/Documents/GitHub/LKIMavenSadiq/.idea/webdriver/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.letskodeit.com/practice");
        driver.manage().window().maximize();
    }
    @Test
    public void testRadioBtn() throws InterruptedException {
        WebElement bmwRadioBtn = driver.findElement(By.id("bmwradio"));
        bmwRadioBtn.click();
        waitFor(5);
        System.out.println("BMW radio button is working");

        WebElement hondaRadioBtn = driver.findElement(By.id("hondaradio"));
        hondaRadioBtn.click();
        waitFor(5);
        System.out.println("Honda radio button is working");
        driverClose();
    }
    @Test
    public void switchTabHandle() throws InterruptedException {
        driver.findElement(By.xpath("//a[@id='opentab']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'INTERVIEW')]")).click();
        String mainTab = driver.getWindowHandle();
        List<String>childTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(childTabs.get(1));
        driver.switchTo().window(childTabs.get(0));
        driver.switchTo().window(mainTab);
        waitFor(5);
        System.out.println(driver.getTitle());
        driver.getCurrentUrl();
        //driverClose();

    }
    @Test
    public void autoSuggest() throws InterruptedException {
        WebElement suggest = driver.findElement(By.id("autosuggest"));
        suggest.sendKeys("Practice makes perfect");
        waitFor(5);
        System.out.println("Auto suggest is done");
        driverClose();
    }
}
