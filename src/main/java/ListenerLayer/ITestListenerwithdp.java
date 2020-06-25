package ListenerLayer;

import UtilityLayer.Emailablereport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerwithdp implements ITestListener {
    private static final String OUTPUT_FOLDER= "/test-output/";
    private  static  final String FILE_NAME="ExtentreportforITestListener.html";
    ExtentReports extent;
    String extentreportpath;
    Emailablereport emailablereport=new Emailablereport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void init(){

        extentreportpath=System.getProperty("user.dir") + OUTPUT_FOLDER + FILE_NAME;
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(extentreportpath);
        htmlReporter.config().setDocumentTitle("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setReportName("ExtentReports - Created by TestNG Listener");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getTestContext().getAttribute("testName").toString());
        test.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println((result.getTestContext().getAttribute("testName").toString()));
        test.get().pass("Test passed");

    }



    @Override
    public void onTestFailure(ITestResult result) {


        System.out.println((result.getTestContext().getAttribute("testName").toString()));
        test.get().fail(result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
init();
    }

    @Override
    public void onFinish(ITestContext context) {
extent.flush();
        teardown();

    }
    public void teardown()
    {

        emailablereport.sendemailwithattachment(extentreportpath);
    }
}
