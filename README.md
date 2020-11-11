# ui_searchtest_automation

1. This is a maven project, Including ui test under test folder, please run maven clean install get all the library.
2. Need to make sure your chromedriver in the correct path (please check the Const chromeDriverPath variable, make the correct path and the download the chromedriver) 
  Or you may include the ChromeDriver location in your system PATH environment variable
 (If not specified WebDriver will search your system PATH environment variable for locating the chromedriver)
3. How to run test suite?
   Run maven test. 
In the Maven tool window, under the Lifecycle node, right-click run, start to run
In command line: mvn test
   Run testng.xml (Which is in /resources/testng.xml), select testng.xml file, mouse right click run.
4. How to run single test?
 	(1)In the Maven tool window, under the Lifecycle node, right-click the test goal.
   From the context menu, select Create 'name of the module/project and name of a goal'.
   	(2)In command line: mvn -Dtestname test
 5.  Run test cases via testng.bat
  Example
set projectLocation=C:\ %projectLocation%set classpath=%projectLocation%\bin;%projectLocation%\lib\*java org.testng.TestNG %projectLocation%\testng.xmlpause
  And in build step give the name of your TestNG.bat in Jenkin, add test description.
  6. You may also run test suite at Jenkins or sourceLab.
  7. You may change testng.xml config to control your regression test or unit test.
Add suite name and run ui and api test cases separately
  8. This project will use chromedriver , if you want to use different Browser, you may change at BaseTest, there is the instruction.

Note: To use a different WebDriver you need to specify the driver path in the file system and then instantiate it. For example, if you want to use IE then here is what you'd need to do: System.setProperty("webdriver.ie.driver", "path/to/IEDriver");
WebDriver driver = new InternetExplorerDriver();
  9.Check the test results
Could be check via your IDE control window
