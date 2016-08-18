package smoke.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

/**
 * Created by barocko on 8/17/2016.
 */
public class GoogleSearchTest extends BaseTest {

    @Test
    public void testSearchAndFollowLink() {

        openPage();

        search("“Selenium automates browsers”");

        assertFirstResultHave(0,"Selenium automates browsers");

        followLink(0);

        $("h2").shouldHave(text("What is Selenium?"));
        assertEquals("http://www.seleniumhq.org/", url());
    }

    ElementsCollection results = $$(".srg>.g");

    @Step
    public void search(String text) {
        $(byName("q")).setValue(text).pressEnter();
    }

    public void assertFirstResultHave(int index,String texts) {
        results.get(index).shouldHave(text(texts));
    }

    public void followLink(int index) {
        results.get(index).find("h3>a").click();
    }

    public static void openPage() {
        open("https://google.com");
    }


}
