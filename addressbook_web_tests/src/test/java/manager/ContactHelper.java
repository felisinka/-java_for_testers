package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void selectGroup(GroupData group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }


    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
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
       // attach(By.name("photo"), contact.photo());

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

    public void modifyContact(ContactData initialContact, ContactData modifiedContact) {
        initContactModification(initialContact);
        fillContactForm(modifiedContact);
        submitContactModification();
        //returnToHomePage();
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//input[@value='%s']/../following-sibling::td[./a][3]" ,contact.id())));

    }

    private void submitContactModification() {
        click(By.cssSelector("input:nth-child(74)"));
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        var dropdown = manager.driver.findElement(By.name("group"));
        dropdown.findElement(By.xpath("option[. = '"+group.name()+"']")).click();
        selectContact(contact);
        click(By.name("remove"));
    }

    public void addContactToGroup(ContactData contact, GroupData group) {

        selectContact(contact);
        var dropdown = manager.driver.findElement(By.name("to_group"));
        dropdown.findElement(By.xpath("option[. = '"+group.name()+"']")).click();
        click(By.name("add"));

    }

    public String getPhones(ContactData contact) {
       return  manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]",contact.id()))).getText();
    }

    public Map<String,String> getPhones() {
        var result = new HashMap<String,String>();
      List<WebElement> rows =  manager.driver.findElements(By.name("entry"));
      for (WebElement row:rows){
          var id = row.findElement(By.name("selected[]")).getAttribute("id");
          var phones = row.findElements(By.tagName("td")).get(5).getText();
          result.put(id,phones);
      }
      return result;
    }

    public Map<String,String> getAddress() {
        var result = new HashMap<String,String>();
        List<WebElement> rows =  manager.driver.findElements(By.name("entry"));
        for (WebElement row:rows){
            var id = row.findElement(By.name("selected[]")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            if (address!= null && !"".equals(address)){
                address = address.replaceAll("\n","");
            }

            result.put(id,address);
        }
        return result;
    }

    public Map<String,String> getEmails() {
        var result = new HashMap<String,String>();
        List<WebElement> rows =  manager.driver.findElements(By.name("entry"));
        for (WebElement row:rows){
            var id = row.findElement(By.name("selected[]")).getAttribute("id");
            var email = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id,email);
        }
        return result;
    }
}
