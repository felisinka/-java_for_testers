package manager;

import org.openqa.selenium.By;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void type(By locator, String group) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(group);
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }
}
