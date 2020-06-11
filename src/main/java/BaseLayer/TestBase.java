package BaseLayer;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestBase {
    public static WebDriver driver;
    public void initialization() {
        System.setProperty("webdriver.chrome.driver", "C:\\jansi_webdrivers\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.ca/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }
    public static String takescreenshot(String filename){

             String path = "C:\\Selenium_projects\\ExtentReport\\test-output\\Screenshots\\"+filename+System.currentTimeMillis()+".jpg";
             File src=  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
             File dest=new File(path);
             try {
                 FileUtils.copyFile(src, dest);
                }
             catch (IOException e)
              {
                  e.printStackTrace();
                  }
             System.out.println(path);
             return path;
    }
}




