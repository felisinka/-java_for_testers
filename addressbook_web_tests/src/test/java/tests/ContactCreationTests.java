package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase{

  public static List<ContactData> contactProvider() throws IOException {
    var result = new ArrayList<ContactData>();
  /*  for (var name: List.of("","contact name")) {
      for (var lastName : List.of("","contact last name")) {
        for (var address : List.of( "","contact address")) {
          for (var email : List.of( "","contact email")) {
            for (var homePhone : List.of( "","+79161307546")) {
              result.add(new ContactData("", name, lastName, address, email, homePhone, ""));
            }
          }
        }
      }
    }*/
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(new File("contacts.json"),  new TypeReference<List<ContactData>>() { } );
    result.addAll(value);
    return result;
  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContacts(ContactData contact) {
    var oldContacts = app.hbm().getContactList();//contacts().getList();
    app.contacts().createContact(contact);
    var newContacts = app.hbm().getContactList();//contacts().getList();

    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);

    var expectedList = new ArrayList<>(oldContacts);
    var id = newContacts.get(newContacts.size()-1).id();
    expectedList.add(contact.withId(id)
            .withPhoto(""));
    expectedList.sort(compareById);

    Assertions.assertEquals(expectedList,newContacts);

  }

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateContactInGroup(ContactData contact) {
    if (app.hbm().getGroupCount()==0) {
      app.hbm().createGroup(new GroupData("", "kate group", "kate group header", "kate group footer"));
    }
    var group = app.hbm().getGroupList().get(0);
    var oldRelated = app.hbm().getContactsInGroup(group);
    app.contacts().createContact(contact,group);
    var newRelated = app.hbm().getContactsInGroup(group);

    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newRelated.sort(compareById);
    var id = newRelated.get(newRelated.size()-1).id();

    var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withId(id).withPhoto(""));
    expectedList.sort(compareById);

    Assertions.assertEquals(expectedList,newRelated);

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
