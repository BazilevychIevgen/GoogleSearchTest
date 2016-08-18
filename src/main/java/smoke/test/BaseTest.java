package smoke.test;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import com.sun.xml.internal.ws.dump.LoggingDumpTube;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;


/**
 * Created by barocko on 8/17/2016.
 */
public class BaseTest {

    @After
    public void tearDown() throws IOException {
        File lastSelenideScreenshot = Screenshots.getLastScreenshot();
        if (lastSelenideScreenshot != null) {
            screenshot(Files.toByteArray(lastSelenideScreenshot));
        }
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot(byte[] dataForScreenshot) {
        return dataForScreenshot;
    }

}


