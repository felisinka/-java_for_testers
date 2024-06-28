package manager;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;

public LoginHelper session(){
    if (session == null){
        session = new LoginHelper(this);
    }
    return session;
}

public GroupHelper groups(){
    if (groups == null){
        groups = new GroupHelper(this);
    }
    return groups;
}

public ContactHelper contacts(){
    if (contacts == null){
        contacts = new ContactHelper(this);
    }
    return contacts;
}

    public void init(String browser) {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-debugging-port=9222");
                //options.addArguments("start-maximized"); // open Browser in maximized mode
                //options.addArguments("disable-infobars"); // disabling infobars
                // options.addArguments("--disable-extensions"); // disabling extensions
                //options.addArguments("--disable-gpu"); // applicable to windows os only
                // options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                //options.addArguments("--no-sandbox"); // Bypass OS security model
                driver = new ChromeDriver(options);

            } else if ("firefox".equals(browser))
            {
                // put gecko driver to the path. it starts selenium, not firefox!
                System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            //firefox driver works only with url to index.php. otherwise failed with Unable to locate element: *[name='user']
            driver.get("http://localhost/addressbook/index.php");
            session().login("admin", "secret");
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
