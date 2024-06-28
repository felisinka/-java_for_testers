package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;


public class ContactCreationTests extends TestBase{


  @Test
  public void canCreateContact() {
    app.contacts().createContact(new ContactData("First Name","Last Name","Test Address","email@email.com","+79161307546"));
  }

  @Test
  public void canCreateContactWithEmptyName() {
    app.contacts().createContact(new ContactData());

  }

  @Test
  public void canCreateContactWithFirstNameOnly() {
    app.contacts().createContact(new ContactData().withFirstName("Some name"));

  }
}
