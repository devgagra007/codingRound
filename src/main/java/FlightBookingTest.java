import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

    WebDriver driver = new ChromeDriver();


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        setDriverPath();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        
        click(By.id("OneWay"));
        clearAndSend(By.id("FromTag"), "Bangalore");
        
        //wait for the auto complete options to appear for the origin

        waitFor(2000);
        click(By.tagName("li"), 0);
        clearAndSend(By.id("toTag"), "Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(2000);
        //select the first item from the destination auto complete list
        click(By.tagName("li"), 0);
        click(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a"));

        //all fields filled in. Now click on search
        click(By.id("SearchBtn"));

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.close();

    }


    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
