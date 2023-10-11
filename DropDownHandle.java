package letskodeit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class DropDownHandle {
    WebDriver driver;
    String baseUrl;
    @AfterTest
     void driverClose(){
         driver.quit();
     }
    static void waitFor(int second) throws InterruptedException {
        Thread.sleep(1000*second);
    }

    public void doScroll(){

    }
    @BeforeTest
    public void setUp(){
       System.setProperty("webdriver.gecko.driver",
               "/Users/sadaqur.rahman/Documents/GitHub/LKIMavenSadiq/.idea/webdriver/geckodriver");
        driver = new FirefoxDriver();
        baseUrl = "https://www.letskodeit.com/practice";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS );
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }
    @Test
    public void RadioButtonTest() throws InterruptedException {
        WebElement bmwRadioBtn = driver.findElement(By.id("bmwradio"));
        bmwRadioBtn.click();
        waitFor(3);
        System.out.println("Radio Button is selected");

    }

    @Test
    public void checkBoxTest() throws InterruptedException {
        WebElement hondaCheckBox = driver.findElement(By.id("hondacheck"));
        hondaCheckBox.click();
        waitFor(3);
        System.out.println("Honda check box is marked");

        WebElement bmwCheckBox = driver.findElement(By.id("bmwcheck"));
        bmwCheckBox.click();
        waitFor(3);
        System.out.println("BMW check box is marked");
    }
    @Test
    public void switchWindow() throws InterruptedException {
        String parentWindow = driver.getWindowHandle();
        System.out.println("parent window"+parentWindow);
        WebElement openWindowBtn = driver.findElement(By.id("openwindow"));
        openWindowBtn.click();
        waitFor(3);

        Set<String>handles = driver.getWindowHandles();
        for(String handle : handles){
            System.out.println(handle);
            if(!handle.equals(parentWindow)){
                driver.switchTo().window(handle);
                driver.findElement(By.xpath("//a[contains(text(),'HOME')]")).click();
                waitFor(3);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        driver.findElement(By.xpath("//button[@id='openwindow']"));
        waitFor(3);
        System.out.println(driver.getCurrentUrl());
       //driver.close();
    }
    @Test
    public void switchTab() throws InterruptedException {

        driver.findElement(By.xpath("//a[@id='opentab']")).click();
        driver.findElement(By.xpath("//a[@id='opentab']")).click();
        //WebElement switchTabBtn = driver.findElement(By.id("opentab"));
        String mainTab = driver.getWindowHandle();
        List<String>childTabs = new ArrayList<>(driver.getWindowHandles());
        //childTabs.remove(mainTab);
        driver.switchTo().window(childTabs.get(1));
        waitFor(5);
        System.out.println(driver.getTitle());

        //String mainTab = driver1.getWindowHandle();
        //childTabs = {window1, window2, window3} if I want to individual window.
        //List<String>childTabs = new ArrayList<>(driver.getWindowHandles());
        //childTabs.remove(mainTab);
        driver.switchTo().window(childTabs.get(0));
        waitFor(1);
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        waitFor(3);
        //driver.close();
    }
    @Test

    public void carSelect() throws InterruptedException {
        WebElement carSelect = driver.findElement(By.id("carselect"));
        Select dropdown = new Select(carSelect);
        dropdown.selectByValue("benz");
        waitFor(3);
        System.out.println("Benz is selected from dropdown");
    }
    @Test
    public void multipleSelection() throws InterruptedException {
        WebElement multipleSelect = driver.findElement(By.id("multiple-select-example"));
        Select select = new Select(multipleSelect);
        select.selectByVisibleText("Apple");
        select.selectByVisibleText("Orange");
        select.selectByVisibleText("Peach");

        List<WebElement> listOfOptions = select.getAllSelectedOptions();
        for(WebElement obj : listOfOptions) {
            System.out.println(obj.getText());
            waitFor(3);

            select.deselectByVisibleText("Orange");
        }
        System.out.println("All element is selected after that Orange is deselected");
    }
    @Test
    public void autoSuggest() {
        driver.findElement(By.xpath("//input[@id='autosuggest']")).
                sendKeys("Practice makes perfect.");
        System.out.println("Auto suggestion is done");

    }
    @Test
    public void enabledAndDisabled() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='enabled-example-input']")).
                sendKeys("God bless America");
        Boolean enabled = driver.findElement(By.xpath("//input[@id='enabled-button']")).isEnabled();
        System.out.println("Field is enable "+enabled);;



    }
    @Test
    public void hideOrShow() throws InterruptedException {
       WebElement Hide =  driver.findElement(By.id("hide-textbox"));
       Hide.click();
       waitFor(3);
        System.out.println("Text box is hide");

       WebElement show = driver.findElement(By.id("show-textbox"));
       show.click();
       waitFor(3);
       driver.findElement(By.xpath("//input[@id='displayed-text']")).
               sendKeys("SRahman");
        System.out.println("Text box is show and text displayed");

    }
    @Test
    public void switchToAlert() throws InterruptedException {

        driver.findElement(By.id("name")).sendKeys("Alert Box ");;
        WebElement altBtn= driver.findElement(By.id("alertbtn"));
        altBtn.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        waitFor(3);
        alert.accept();
        System.out.println("Alert button is working");

        driver.findElement(By.id("name")).sendKeys("Alert Box ");
        WebElement cnfBtn = driver.findElement(By.id("confirmbtn"));
        cnfBtn.click();
        Alert confirm = driver.switchTo().alert();
        System.out.println(alert.getText());
        waitFor(3);
        confirm.dismiss();
        System.out.println("Confirm button is working");

    }

    @Test
    public void mouseHover() throws InterruptedException, MoveTargetOutOfBoundsException {
        Actions action = new Actions(driver);
        WebElement moushover = driver.findElement(By.xpath("//button[@id='mousehover']"));
        action.moveToElement(moushover).perform();
        waitFor(3);

    }

    @Test
    public void handleWebTable(){
        WebElement rowlist = driver.findElement(By.xpath("//table[@id='product']"));
        System.out.println("Number of rows "+ rowlist.getSize());
        Dimension rowsize = rowlist.getSize();

        //get number of cloumn
        WebElement columnlist = driver.findElement(By.xpath("//tbody/tr/th[1]"));
        System.out.println("Number of column "+ columnlist.getSize());
        Dimension columnsize =columnlist.getSize();

        //get particular data
        WebElement text = driver.findElement(By.xpath("//td[contains(text(),'Python Programming Language')]"));
        System.out.println(text);
    }
    @Test
    public void iFrameHandle() throws InterruptedException {
        driver.switchTo().frame("iframe-name"); //name of the whole iframe
        //driver.findElement(By.xpath("//iframe[@id='courses-iframe']")).click();
        driver.switchTo().defaultContent();
        waitFor(2);

        //driver.switchTo().frame("Home"); //name of the frame1
        driver.findElement(By.xpath("//a[contains(text(),'HOME')]")).click();
        driver.switchTo().defaultContent();
        waitFor(2);

        //driver.switchTo().frame("All Courses"); //name of the frame2
        driver.findElement(By.xpath("//a[contains(text(),'ALL COURSES')]")).click();
        driver.switchTo().defaultContent();
        driver.switchTo().window(driver.getCurrentUrl());
        //WebElement courses = driver.findElement(By.id("course-list"));
        //Select dropdown = new Select(courses);
        //dropdown.selectByValue("All");
       // dropdown.selectByVisibleText(" Test Automation");
        waitFor(3);
        System.out.println("test automation is selected from dropdown");


    }
}
