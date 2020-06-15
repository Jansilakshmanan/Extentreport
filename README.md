# Extentreport
    This repository is basically on different ways of extent report creation integrated with TestNGListeners
    
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
            
            
            
    
