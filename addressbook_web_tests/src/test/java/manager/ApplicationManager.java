package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;

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

    public void init() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-debugging-port=9222");
            //options.addArguments("start-maximized"); // open Browser in maximized mode
            //options.addArguments("disable-infobars"); // disabling infobars
            // options.addArguments("--disable-extensions"); // disabling extensions
            //options.addArguments("--disable-gpu"); // applicable to windows os only
            // options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            //options.addArguments("--no-sandbox"); // Bypass OS security model
            driver = new ChromeDriver(options);
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
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
