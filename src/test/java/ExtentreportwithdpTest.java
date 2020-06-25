import BaseLayer.TestBase;
import UtilityLayer.GetExceldataintoObjectarray;
import UtilityLayer.Getexceldataintohashmaps;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;


@Listeners(ListenerLayer.ITestListenerwithdp.class)
public class ExtentreportwithdpTest extends TestBase implements ITest {


    GetExceldataintoObjectarray getdata=new GetExceldataintoObjectarray();
    Object[][] getdatafromxcel=null;
    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeMethod()
    public void setup(Method method, Object[] testdata, ITestContext ctx)

    {
        initialization();

        if (testdata.length > 0) {
            testName.set(method.getName() + "_" + testdata[1].toString());
            ctx.setAttribute("testName", testName.get());

        } else
            ctx.setAttribute("testName", method.getName());

    }


    @DataProvider
    public Object[][] getexceldata(Method m) throws IOException
    {

        Object[][] getdatafromxcel=  getdata.readexceldata();
        int filter_rows = 0;
        int filter_columns = getdatafromxcel[0].length;
        for (int iterator = 0; iterator < getdatafromxcel.length; iterator++) {
            if (m.getName().toString().equalsIgnoreCase(getdatafromxcel[iterator][0].toString())) {
                filter_rows++;
            }
        }

        Object[][] filterdata=new Object[filter_rows][filter_columns];
        int k=0;
        for(int i=0;i<getdatafromxcel.length;i++)
        {

            if(m.getName().toString().equalsIgnoreCase(getdatafromxcel[i][0].toString())) {
                for(int j=0;j<getdatafromxcel[0].length;j++) {

                    filterdata[k][j]=getdatafromxcel[i][j].toString();
                }
            k++;
            }
        }
        return filterdata;
    }

@Override
    public String getTestName() {
        return testName.get();
    }

   @Test(priority = 1,dataProvider = "getexceldata")
    public void verifygooglepagetitle(String TestMethod ,String Testcase,String Expected_title)
    {
        String actual=driver.getTitle();
      String expected=Expected_title;

        Assert.assertEquals(actual,expected,"Mismatch of title");
    }

    @Test(priority = 2,dataProvider = "getexceldata")
    public void verifygmailpagetitle(String TestMethod ,String Testcase,String Expected_title) throws InterruptedException {
        driver.findElement(By.linkText("Gmail")).click();
        String expexted=  driver.getTitle();
        Thread.sleep(3000);
        String actual="Gmail - Email from Google";
        Assert.assertEquals(actual,expexted,"Mismatch of title");
    }



}
