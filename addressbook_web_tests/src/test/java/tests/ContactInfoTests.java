package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests  extends TestBase {

@Test
    void testPhones(){
    if (app.hbm().getContactCount()==0){
        app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "+79161307546", "", "", "", ""));
    }
    var contacts = app.hbm().getContactList();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact->
        Stream.of(contact.homePhone(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))
    ));
    var phones = app.contacts().getPhones();
    Assertions.assertEquals(expected, phones);

    }

}
