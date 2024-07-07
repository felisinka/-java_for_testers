package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

   public void removeContact(ContactData contact) {
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    public void removeAllContacts() {
       // returnToHomePage();
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes){
            checkbox.click();
        }
    }

    public int getCount() {
        returnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }


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
        click(By.linkText("home"));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']",contact.id())));
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.xpath("//tr[@name=\"entry\"]"));
      for (int i=0;i< rows.size();i++){
         var lastName = manager.driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr["+(i+2)+"]/td[2]")).getText();
          var firstName = manager.driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr["+(i+2)+"]/td[3]")).getText();
          var checkbox = rows.get(i).findElement(By.name("selected[]"));
          var id = checkbox.getAttribute("value");

          contacts.add(new ContactData().withFirstName(firstName)
                        .withId(id)
                        .withLastName(lastName)
                );
       }
        return contacts;
    }

}
