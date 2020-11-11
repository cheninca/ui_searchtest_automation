package com.unity.ui.test_cases;

import com.unity.Const;
import com.unity.ui.page_object.HomePage;
import com.unity.ui.util.uitesttool;
import junit.framework.TestCase;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class BasicSearchTest extends BaseTest {

    HomePage homePage = new HomePage(driver);

    @BeforeTest
    public void setUp() {
        super.setUp();
    }

    @BeforeMethod
    public void logCurrentTestName(Method method)
    {
        Reporter.log("Currently running " +method.getName()+ "test.");

    }

    @Test
    public void TC001_BasicSearchElementsTest() throws Exception {

        assertTrue(driver.getTitle().contains("demo store"));
        assertTrue(homePage.wb_BtnSearch.isDisplayed());
        assertTrue(homePage.wb_inputSearch.isDisplayed());
        assertTrue(homePage.wb_BtnSearch.getAttribute("value").toUpperCase().contains("SEARCH"));
    }

    @Test
    public void TC002_BasicSearchPositiveTest() throws Exception {

        homePage.enterSearchText("HTC");
        List<WebElement> listWB = homePage.getSearchResultTitleList();
        assertEquals(2,listWB.size());
    }


    @Test
    public void TC003_SimilarMatchSearchPositiveTest() throws Exception {

        homePage.enterSearchText("%HT%");
        assertEquals(homePage.getSearchResultTitleList().size(),3);

        homePage.enterSearchText("%ONE%");
        assertEquals(homePage.getSearchResultTitleList().size(),4);
    }

    @Test
    public void TC004_PartlySearchTest() throws Exception {

        homePage.enterSearchText("Min");
        List<WebElement> listWB = homePage.getSearchResultTitleList();
        assertEquals(2,listWB.size());
    }

    @Test
    public void TC005_LogicOperatorSearchTest() throws Exception {

        homePage.enterSearchText("HTC ONE");
        int expectResult = homePage.getSearchResultTitleList().size();

        homePage.enterSearchText("HTC+ONE");
        assertEquals(homePage.getSearchResultTitleList().size(),expectResult);

        homePage.enterSearchText("HTC + ONE");
        assertEquals(homePage.getSearchResultTitleList().size(),expectResult);

        homePage.enterSearchText("HTC && ONE");
        assertEquals(homePage.getSearchResultTitleList().size(),expectResult);
    }

    @Test
    public void TC006_InvalidCharacterSearchNegativeTest() throws Exception {
        homePage.enterSearchText("*&^%%$&*^&*^*(^*(^");
        assertEquals(0,homePage.getSearchResultTitleList().size());
        assertTrue(homePage.wb_divNoResultText.isDisplayed());


        homePage.enterSearchText("-=1.,/`';[]d)(*&^%$$\\||//>>M<<M~~~~~~~~~~");
        assertEquals(0,homePage.getSearchResultTitleList().size());
        assertTrue(homePage.wb_divNoResultText.isDisplayed());

    }

    @Test
    public void TC007_SearchEnterKeyTest() throws Exception {

        homePage.enterSearchTextViaEnter("Nokia");
        assertEquals(1,homePage.getSearchResultTitleList().size());
    }

    @Test
    public void TC008_SearchButtonTest() throws Exception {

        homePage.enterSearchTextViaSearchButton("Nokia");
        assertTrue(homePage.getSearchResultTitleList().size()>=1);
    }

    @Test
    public void TC009_EmptyInputSearchTest() throws Exception {

        homePage.enterSearchTextViaSearchButton("");
        assertTrue(uitesttool.isAlertPresent(driver));
    }

    //Enter space or invalid character to test if the search function work fine, supposed to return no result warning message
    @Test
    public void TC010_InvalidCharacterInputSearchDestroyTest() throws Exception {

        for(int i=0; i< Const.InvalidCharacterInputString.length; i++){
            System.out.println("Enter "+Const.InvalidCharacterInputString[i]);
            homePage.enterSearchText(Const.InvalidCharacterInputString[i]);
            Thread.sleep(500);
            int res = homePage.getSearchResultTitleList().size();
            if(res==0){
                assertTrue(true);
            }else{
                Reporter.log(" Character "+ Const.InvalidCharacterInputString[i] +" result is not correct ");
                fail();
            }
        }
    }



    @Test
    public void TC011_SolidSearchTest() throws Exception {


        String str = uitesttool.generateRandomString(200);
        homePage.enterSearchTextViaSearchButton(str);
        assertEquals(0, homePage.getSearchResultTitleList().size());
        assertTrue(homePage.wb_divNoResultText.isDisplayed());

        homePage.enterSearchTextViaSearchButton("Nokia---------------------------------------------------");
        assertEquals(0, homePage.getSearchResultTitleList().size());
        assertTrue(homePage.wb_divNoResultText.isDisplayed());

        homePage.enterSearchTextViaSearchButton(Integer.toString(Integer.MAX_VALUE));
        assertEquals(0, homePage.getSearchResultTitleList().size());
        assertTrue(homePage.wb_divNoResultText.isDisplayed());

    }


    @Test
    public void TC012_OneCharacterShowWarningMessage() throws Exception {

        homePage.enterSearchTextViaSearchButton("1");
        TestCase.assertEquals(0, homePage.getSearchResultTitleList().size());
        assertTrue(homePage.wb_divWarningMessage.isDisplayed());
    }

    @Test
    public void TC013_SearchHintTextTest() throws Exception {

        homePage.wb_inputSearch.sendKeys("Niko");
        assertTrue(homePage.getSpanHintText().contains("5500"));
    }

    @Test
    public void TC014_SearchNotMatchErrorValidate() throws Exception {

        homePage.enterSearchText("No");
        assertTrue(homePage.wb_divWarningMessage.getText().contains("minimum length"));
    }

    @Test
    public void TC015_NotCaseSensitiveSearchTest() throws Exception {

        homePage.enterSearchText("laptop");
        assertTrue(homePage.getSearchResultTitleList().size()>=2);

        homePage.enterSearchText("LAPTOP");
        assertTrue(homePage.getSearchResultTitleList().size()>=2);

        homePage.enterSearchText("LapTop");
        assertTrue(homePage.getSearchResultTitleList().size()>=2);

    }

    @Test
    public void TC016_WildcardCharacterAndCaseSensitiveTest() throws Exception {

        homePage.enterSearchText("%pc%");
        assertTrue(homePage.getSearchResultTitleList().size()>=2);

        homePage.enterSearchText("%PC%");
        assertTrue(homePage.getSearchResultTitleList().size()>=2);

        homePage.enterSearchText("%PC");
        assertTrue(homePage.getSearchResultTitleList().size()>=2);
    }

    //compatibility
    @Test
    public void TC017_SpaceCompatibilityTest() throws Exception {

        homePage.enterSearchText("HTC ONE");
        int expectResult = homePage.getSearchResultTitleList().size();

        homePage.enterSearchText(" HTC  ONE ");
        assertEquals(homePage.getSearchResultTitleList().size(),expectResult);

        homePage.enterSearchText("HTC   ONE");
        assertEquals(homePage.getSearchResultTitleList().size(),expectResult);

        homePage.enterSearchText("HTC%ONE");
        assertEquals(homePage.getSearchResultTitleList().size(),expectResult);
    }

    @Test
    public void TC018_BasicSearchCoverAllCategoryTest() throws Exception {

        //Search function could get all Category items
        Iterator hmIterator = Const.ExpectCategoryHashMap.entrySet().iterator();

        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            //int marks = ((int)mapElement.getValue() + 10);
            System.out.println(mapElement.getKey() + " : " + mapElement.getValue());
            String inputString =  mapElement.getValue().toString();
            homePage.enterSearchText(inputString);
            int res = homePage.getSearchResultTitleList().size();
            if(res>=1){
                assertTrue(true);
            }else{
                Reporter.log(mapElement.getKey().toString()+ " Category search result is not correct");
                fail();
            }

        }
    }

    //Validate after search action, could go back to homepage and Search Box is be cleared
    @Test
    public void TC019_AfterSearchBeAbleToGoBackToHomePageTest() throws Exception {

        homePage.enterSearchText("HTC");
        assertTrue(homePage.getSearchResultTitleList().size()>=2);
        Thread.sleep(500);
        driver.navigate().back();
        Thread.sleep(500);
        driver.switchTo().frame(0);

        assertTrue(homePage.wb_h2HomePageTitle.isDisplayed());
    }

    @Test
    public void TC020_MinimumLengthWarningSearchTest() throws Exception {

        for(int i=0; i< Const.LessThanThreeCharInputString.length; i++){
            System.out.println("Enter "+Const.LessThanThreeCharInputString[i]);
            homePage.enterSearchText(Const.LessThanThreeCharInputString[i]);
            if(homePage.wb_divMinimumLengthWarningMessage.isDisplayed()){
                assertTrue(true);
            }else{
                Reporter.log(" Character "+ Const.LessThanThreeCharInputString[i] +" warning message is not display, test failed ");
                fail();
            }
        }
    }

    //Enter String more than 2000 characters will throw ResourceUnavailableError, should confirm with PO considered as a defects.
    @Test
    public void TC021_DestroySearchTest() throws Exception {

        String str = uitesttool.generateRandomString(5000);
        homePage.enterSearchTextViaSearchButton(str);
        assertFalse(homePage.wb_divResourceUnavailableError.isDisplayed());
    }




    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
        Reporter.log("Tests done");
    }

}
