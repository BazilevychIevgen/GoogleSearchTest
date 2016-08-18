package smoke.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by barocko on 8/17/2016.
 */
public class GoogleSearchTest extends BaseTest {

    @Test
    public void testSearch() {

        openPage();

        search("“Selenium automates browsers”");

        assertResultsCount(10);

        followLink(0);

        pageIsDownloaded();
    }

    ElementsCollection results = $$(".srg>.g");

    @Step
    public void search(String text) {
        $(byName("q")).setValue(text).pressEnter();
    }

    public void assertResultsCount(int count) {
        results.shouldHave(size(count));
    }

    public void followLink(int index) {
        results.get(index).find("h3>a").click();
    }

    public void pageIsDownloaded() {
        $("#editPage>a").shouldBe(visible);
        Assert.assertEquals("http://www.seleniumhq.org/", url());
    }

    public static void openPage() {
        open("https://google.com");
    }


}
