package com.unity;

import java.util.HashMap;
import java.util.Map;

public class Const {

    //Config data
    public static String browser_type = String.valueOf(browserConfig.CHROME);
    public static String base_url = "https://frontend.nopcommerce.com";
    public static String chromeDriverPathMac = "src/test/resources/webdriver/chromedriver";
    public static String chromeDriverPathWin = "src/test/resources/webdriver/chromedriver.exe";
    public static final int waitTime = 30;
    public static final int timeOutInSecs = 90;


    //Input Testing Data
    public static String invalid_amount = "900";
    public static String valid_amount = "2000";

    public static final Map<String, String> ExpectCategoryHashMap = new HashMap<String, String>();
    static {
        ExpectCategoryHashMap.put("Computers","Digital Storm");
        ExpectCategoryHashMap.put("Electronics","D5500");
        ExpectCategoryHashMap.put("Apparel", "Women T-Shirt");
        ExpectCategoryHashMap.put("Digital downloads", "Faith");
        ExpectCategoryHashMap.put("Books", "Pride");
        ExpectCategoryHashMap.put("Jewelry", "Ring");
        ExpectCategoryHashMap.put("Gift Cards", "Gift Card");
    }

    enum browserConfig {
        CHROME,
        FIREFOX,
        IE,
        EDGE

    }
    enum invalidValue {
        ABC,
        NUM,
        ZIPCODE
    }

    public final static String[] InvalidCharacterInputString = {"<>45>", "{}*&", "+_)","%5^&","@$#"};
    public final static String[] LessThanThreeCharInputString = {"1", "12", "00",",",".","/","-"};
}
