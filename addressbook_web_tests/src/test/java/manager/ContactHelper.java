package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager){
        super(manager);

    }

    public void createContact(ContactData contact) {

        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

 /*   public void removeContact() {
        selectContact();
        removeSelectedContact();
        returnToHomePage();
    }

   */

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("home"), contact.homePhone());

    }

    private void submitContactCreation() {
         click(By.cssSelector("input:nth-child(75)"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void removeSelectedContact() {
        click(By.name("delete"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

}
