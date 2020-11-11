package com.unity.ui.page_object;

import com.unity.Const;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
}
