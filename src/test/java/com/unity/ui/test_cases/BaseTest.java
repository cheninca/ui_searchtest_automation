package com.unity.ui.test_cases;

import com.unity.Const;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;

    public BaseTest()  {

        String os = (System.getProperty("os.name"));

        if (os.equalsIgnoreCase("Mac OS X"))
            System.setProperty("webdriver.chrome.driver", Const.chromeDriverPathMac);
        else System.setProperty("webdriver.chrome.driver", Const.chromeDriverPathWin);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(chromeOptions);
        Dimension d = new Dimension(1200,800);
        driver.manage().window().setSize(d);
    }

    public void setUp() {

        driver.get(Const.base_url + "/");
        System.out.println("Start testing on "+driver.getCurrentUrl());
        driver.manage().timeouts().implicitlyWait(Const.waitTime, TimeUnit.SECONDS);
        driver.switchTo().frame(0);
    }

    public void tearDown() throws Exception {
        driver.quit();
    }

    public void cleanup() throws Exception {
        driver.close();
    }

}
