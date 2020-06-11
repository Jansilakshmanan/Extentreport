import BaseLayer.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;


@Listeners({ListenerLayer.Extentreport_listener.class})

public class ExtentReportListenerTest extends TestBase {
    @BeforeMethod
    public void setup()
    {
        initialization();
    }
    @Test
    public void verifygooglepagetitle()
    {
        String actual=driver.getTitle();
        String expected="Google";
        Assert.assertEquals(actual,expected,"Mismatch of title");
    }
    @Test
    public void verifygooglepagetitle1()
    {
        String actual=driver.getTitle();
        String expected="Googles";
        Assert.assertEquals(actual,expected,"Mismatch of title");
    }
    @Test
    public void verifygmailpagetitle() throws InterruptedException {
        driver.findElement(By.linkText("Gmail")).click();
      String expexted=  driver.getTitle();
      Thread.sleep(3000);
      String actual="Gmail - Email from Google";
      Assert.assertEquals(actual,expexted,"Mismatch of title");
    }

}
