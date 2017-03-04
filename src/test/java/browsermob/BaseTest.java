package browsermob;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.io.IOException;

public class BaseTest {

    public WebDriver driver;
    public BrowserMobProxy proxy;
    String driverPath = "D:/";
    String webSite = "https://kyivstar.ua/ru/mm";

    public void initializeProxyAndDriver() {
        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);

        //get the Selenium proxy object - org.openqa.selenium.Proxy;
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        //set chromedriver system property
        System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
        driver = new ChromeDriver(capabilities);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);


    }

    public void createHar(String harName) {
        // create a new HAR
        proxy.newHar(harName);
    }

    public void enterWebsite() {
        driver.get(webSite);
    }


    public void saveHar (String sFileName) {
        // get the HAR data
        Har har = proxy.getHar();

        // Write HAR Data in a File
        File harFile = new File(sFileName);
        try {
            har.writeTo(harFile);
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("Could not find file " + sFileName);
        }
    }

    public void closeBrowserAndProxy() {
        if (driver != null) {
            proxy.stop();
            driver.quit();
        }

    }
}
