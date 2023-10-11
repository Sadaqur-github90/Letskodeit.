package letskodeit;

import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Costco {

    WebDriver driver;

    static void waitFor(int second) throws InterruptedException {
        Thread.sleep(1000 * second);

    }

    @After
    void driverClose() {
        driver.quit();
    }

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver",
                "/Users/sadaqur.rahman/Documents/GitHub/LKIMavenSadiq/.idea/webdriver/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.costco.com/LogonForm");
        driver.manage().window().maximize();

    }
    @Test
    public void doRegister() throws NoSuchElementException {
        //driver.findElement(By.xpath("//a[@id='header_sign_in']")).click();
        //waitFor(5);
        driver.findElement(By.xpath("//a[@id='createAccount']")).click();
    }
}
