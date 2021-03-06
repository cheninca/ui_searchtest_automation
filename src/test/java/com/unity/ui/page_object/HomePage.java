package com.unity.ui.page_object;

import com.unity.Const;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class HomePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, Const.timeOutInSecs);

    @FindBy(xpath="//*[contains(@id,'small-searchterms')]")
    public WebElement searchBox;

    @FindBy(how= How.ID, using="small-searchterms")
    public WebElement wb_inputSearch;

    @FindBy(xpath="//input[contains(@type,'submit')]")
    public WebElement wb_BtnSearch;

    @FindBy(xpath="//h2[contains(@class,'product-title')]")
    public WebElement wb_listTitlesResultSearch;

    @FindBy(xpath="//div[contains(@class,'warning')]")
    public WebElement wb_divWarningMessage;

    @FindBy(xpath="//div[contains(@class,'warning')][contains(text(),'minimum length')]")
    public WebElement wb_divMinimumLengthWarningMessage;

    @FindBy(xpath="//div[contains(@class,'no-result')][contains(text(),'No')]")
    public WebElement wb_divNoResultText;

    @FindBy(xpath="//*[contains(text(),'temporarily unavailable')]")
    public WebElement wb_divResourceUnavailableError;

    @FindBy(xpath="//*[contains(@id,'ui-id-1')]//span")
    public WebElement wb_spanSearchHintText;

    @FindBy(xpath="//h2[contains(text(),'Welcome')]")
    public WebElement wb_h2HomePageTitle;


    @FindBy(xpath="(//h2[contains(@class,'product-title')]//a)[1]")
    public WebElement wb_h2ProductTitleFirstItem;

    By byProductTitleList = By.xpath("//h2[contains(@class,'product-title')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSearchButton(){
        wb_BtnSearch.click();
    }

    public void enterSearchText(String inputString){
        wb_inputSearch.clear();
        wb_inputSearch.sendKeys(inputString);
        wb_inputSearch.sendKeys(Keys.RETURN);
    }

    public void enterSearchTextViaEnter(String inputString){
        wb_inputSearch.clear();
        wb_inputSearch.sendKeys(inputString);
        wb_inputSearch.sendKeys(Keys.RETURN);
    }

    public void enterSearchTextViaSearchButton(String inputString){
        wb_inputSearch.clear();
        wb_inputSearch.sendKeys(inputString);
        this.clickSearchButton();
    }

    public List<WebElement> getSearchResultTitleList(){
        return driver.findElements(byProductTitleList);
    }

    public String getSpanHintText(){
        return this.wb_spanSearchHintText.getText();
    }

    public String getProductTitleXpathViaIndex(String oldXpath, int index){
        //String oldXpath = "(//h2[contains(@class,'product-title')]//a)[1]";
        String str = "\""+ "[" + index + "]"+"\"";
        return oldXpath.toString().replace("[1]","[" + index + "]");
    }

    public List<WebElement> getProductTitlesWebElementList(int numberOfResults){
        List<WebElement> list = new ArrayList<>();
        for(int i=1;i<=numberOfResults;i++){
            String xpathStr = this.getProductTitleXpathViaIndex("(//h2[contains(@class,'product-title')]//a)[1]",i);
            WebElement wb = driver.findElement(By.xpath(xpathStr));
            list.add(wb);
        }
        return list;
    }
}
