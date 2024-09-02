package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class UserManagementHelper extends HelperBase{

    public UserManagementHelper(ApplicationManager manager) {
        super(manager);
    }


    public void startRegistration(String userName, String email) {
        click(By.linkText("Signup for a new account"));
        type(By.id("username"),userName);
        type(By.id("email-field"),email);
        click(By.xpath("//input[@value=\'Signup\']"));
    }

    public void confirmRegistration(String url, String password) {
        manager.driver().get(url);
        type(By.id("password"),password);
        type(By.id("password-confirm"),password);
        click(By.xpath("//form[@id=\'account-update-form\']/fieldset/span/button/span"));

    }
}
