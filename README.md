# Extentreport
    This repository is basically on different ways of extent report creation integrated with TestNGListeners
    
    * Extentreport_listener :  This java file creates a simple report which implements IReporter listener and generates extent report 
                                at the end of suite run.
    
                                 Features included in this basic  Extent report:
                                     *collects all the passed and failed test cases from suite and creates separate test for each test method
                                     *creates logs for test cases which includes Status (pass,fail,skip),throwable error message
                                     *Attaches screenshot taken when a test case fails in the extent report 
                                     *Displays start time and end time of each test case
    
