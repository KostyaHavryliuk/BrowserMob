package browsermob;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class BrowserMobTest extends BaseTest {
    BaseTest baseTest = new BaseTest();

    @BeforeTest
    public void setUp() {
        baseTest.initializeProxyAndDriver();
    }

    @Test
    public void testCaseOne() {
        baseTest.createHar("kyivstar0");
        baseTest.enterWebsite();
        baseTest.saveHar("D:/Games/kyivstar0.har");
    }

    @Test
    public void testCaseTwo() {
        baseTest.createHar("kyivstar1");
        baseTest.enterWebsite();
        baseTest.driver.findElement(By.linkText("Тарифы"));
        baseTest.saveHar("D:/Games/kyivstar1.har");
    }

    @Test
    public void testCaseThree() {
        baseTest.createHar("kyivstar2");
        baseTest.enterWebsite();
        baseTest.driver.findElement(By.linkText("Услуги"));
        baseTest.saveHar("D:/Games/kyivstar2.har");
    }

    @AfterTest
    public void tearDown() {
        baseTest.closeBrowserAndProxy();

    }
}