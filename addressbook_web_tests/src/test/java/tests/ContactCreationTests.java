package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class ContactCreationTests extends TestBase{

  public static List<ContactData> contactProvider() {
    var result = new ArrayList<ContactData>();
    for (var name: List.of("","contact name")) {
      for (var lastName : List.of("", "contact last name")) {
        for (var address : List.of("", "contact address")) {
          for (var email : List.of("", "contact email")) {
            for (var homePhone : List.of("", "+79161307546")) {
              result.add(new ContactData(name, lastName, address, email, homePhone));
            }
          }
        }
      }
    }
    for (int i=0;i<5;i++){
      result.add(new ContactData(randomString(i*10),randomString(i*10),randomString(i*10),randomString(i*10),randomString(i*10)));
    }
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContacts(ContactData contact) {
    int contactCount = app.contacts().getCount();
    app.contacts().createContact(contact);
    int newContactCount = app.contacts().getCount();
    Assertions.assertEquals(contactCount+1,newContactCount);
  }


  public static List<ContactData> negativeContactProvider() {
    var result = new ArrayList<ContactData>(List.of(new ContactData().withFirstName("Some name'")));
    return result;
  }


  @ParameterizedTest
  @MethodSource("negativeContactProvider")
  public void cannotCreateContact(ContactData contact) {
    int contactCount = app.contacts().getCount();
    app.contacts().createContact(contact);
    int newContactCount = app.contacts().getCount();
    Assertions.assertEquals(contactCount,newContactCount);
  }
}
