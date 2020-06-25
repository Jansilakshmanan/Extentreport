# Extentreport
    This repository is basically on different ways of extent report creation integrated with TestNGListeners
    
    Feautures included:
          1.Creating a basic extent report with IReporter Listener as well as ITestListener.
          2.Sending the Extent report to recepient mail id via gmail using JavaMailAPI.
          3.Sending the report to list of recipients--->to,cc,bcc which are retrieved from properties file.
          4.Passing an excel sheet as a dataprovider.
             *Using same dataprovidrr excel sheet for multiple test cases.
             *checking if currently executing test method and excel test method name are same .If so,passing matching rows alone to respective test cases.
             *  All the tests will have the same dataprovider excel dataprovider. For example the test1 will take the data from TestData sheet which have row where the testname                  is test1. If there are multiple rows with test1 as test name , framework will consider it as this as multiple iterations for a test case.
          5. Creating extent test case for different instances of same test methods.
                 Creating customized test case name such as testmethod1_testcasename .For.eg: testmethod name is:verifygooglepagetitle and test cases are for chrome browser and                  firefox for same test method,then we can create separate extent test such as Verifygooglepagetitle_chrome,verifygooglepagetitle_firefox
              This is done by:
                 i)Passing Method class object ,test data and ITestContext to @BeforeMethod
                 ii)Retreiving test case name form testdata and and appending with currently executing testmethod name.
                 iii)Implementing ITest  inteface and overriding gettestname() method with new testcasename.
                 iv)Setting new testcase name to ITestContext attrribute of ITestListener.
                 v)While creating Extent test,getting test case name using result.getTestContext().getAttribute("testName").
                 vi)Sending emailable report as an attachment.
                 
         Things to note:
                   create a Thread Local String object to store your test case name.
                   And then override the above test case name with new customized test case name and set it to ITestContext.
                   
              
                    
                 
                
            
          
    
    * Extentreport_listener :  This java file creates a simple report which implements IReporter listener and generates extent report 
                                at the end of suite run.
    
                                 Features included in this basic  Extent report:
                                     *collects all the passed and failed test cases from suite and creates separate test for each test method
                                     *creates logs for test cases which includes Status (pass,fail,skip),throwable error message
                                     *Attaches screenshot taken when a test case fails in the extent report 
                                     *Displays start time and end time of each test case
                                     
         
         Steps involved:
          1.Create a Listner calss that implements IReporter Listener 
          2.Now,add this listener class to the Test class wherer the test methods are written.
          3.In the listener class,using generateReport method,get all the passed and failed tests in the suite.
          4.Create ExtentSparkReporter class to create a htmlreport and attach the report to the Extent class.
          5.Create separate Extent test in extent report for each test cases using Extent test featue of Extent class.
          6.Capture Screenshot and get the path of the screenshot saved.
          7.Get the screenshot from the path using createScreenCaptureFromPath() which is in the MediaEntityBuilder class.
          8.Log the screenshots and other details in extent report using test.log.
          
          Errors  Encountered:
          
          1.Broken image of screenshot when viewed the extent report in browser thought image got displayed when run in terminal
          
          Solution:
          
            ****Always use Relative path while appending screenshots in a html report.
                 Concatenate ".."along with the relative path of folder in which screenshot is saved.
                 For e.g: Path should be --> "../test-output/Screenshots/"+filename+System.currentTimeMillis()+".jpg"
            
            
            
    
