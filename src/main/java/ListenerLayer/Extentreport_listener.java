package ListenerLayer;

import BaseLayer.TestBase;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.*;

public class Extentreport_listener extends TestBase implements IReporter {
    ExtentReports extent;
    String outputDirectory;
    private static final String OUTPUT_FOLDER = "/test-output/";
    private static final String FILE_NAME = "Extentreport.html";

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();


        for( ISuite suite:suites)
        {
            Collection<ISuiteResult> Results=suite.getResults().values();
            for( ISuiteResult result:Results) {
                ITestContext context = result.getTestContext();

                buildtestcases(context.getPassedTests().getAllResults(),Status.PASS);
                buildtestcases(context.getFailedTests().getAllResults(),Status.FAIL);
                buildtestcases(context.getSkippedTests().getAllResults(),Status.SKIP);


            }

        }
        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }


        extent.flush();



    }
    public void buildtestcases(Set<ITestResult>resultsmap ,Status status) {
        ExtentTest test;

        for( ITestResult result:resultsmap) {

            test = extent.createTest(result.getMethod().getMethodName());

            if(status.toString().equalsIgnoreCase("fail")) {
                try {
//using relative path with ".." to avoid broken image when run in browsers
                    String screenshotname= concatenate+takescreenshot("Failedtestimage");

                    test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotname).build());

                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
            }
            else
            {
                test.log(status,"Test"+status.toString().toLowerCase()+"ed");
            }
            test.getModel().setStartTime(getTime(result.getStartMillis()));
           test.getModel().setEndTime(getTime(result.getEndMillis()));

        }

    }

    public void init()
    {
        
       ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+OUTPUT_FOLDER + FILE_NAME);
       htmlReporter.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setReportName("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setTheme(Theme.STANDARD);
         extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
    }
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}

