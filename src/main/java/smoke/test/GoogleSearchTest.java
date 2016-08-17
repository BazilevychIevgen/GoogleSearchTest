package smoke.test;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by barocko on 8/17/2016.
 */
public class GoogleSearchTest {

    @Test
    public void testSearch() {

        openPage();

        search("“Selenium automates browsers”");

        assertResultsListed(10);
    }

    ElementsCollection searchResults = $$("#lst-ib");

    @Step
    private void search(String... taskTexts) {
        for (String text : taskTexts) {
            $("#lst-ib").setValue(text).pressEnter();
        }
    }

    public void assertResultsListed(int count) {
        searchResults.shouldHave(size(count));
    }



    public void openPage() {
        open("https://google.com");
    }

}
