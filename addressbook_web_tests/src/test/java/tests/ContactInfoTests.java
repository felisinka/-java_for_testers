package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ContactInfoTests  extends TestBase {

@Test
    void testPhones(){
    if (app.hbm().getContactCount()==0){
        app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""));
    }
    var contacts = app.hbm().getContactList();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact->
        Stream.of(contact.homePhone(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))
    ));
    var phones = app.contacts().getPhonesFromGrid();
    Assertions.assertEquals(expected, phones);

    }

    @Test
    void testAddress(){
        if (app.hbm().getContactCount()==0){
            app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact->
                Stream.of(contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .map(s1->s1.replaceAll("\n|\r\n",""))
                        .collect(Collectors.joining("\n"))
        ));
        var address = app.contacts().getAddressFromGrid();
        Assertions.assertEquals(expected, address);

    }

    @Test
    void testEmail(){
        if (app.hbm().getContactCount()==0){
            app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contacts().getEmailsFromGrid();
        Assertions.assertEquals(expected, emails);

    }

    @Test
    void testContactsGrid(){
        if (app.hbm().getContactCount()==0){
            app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""));
        }

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contact = contacts.get(index);

        var expectedPhone = Stream.of(contact.homePhone(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));

        var expectedAddress = contact.address().replaceAll("\n|\r\n","");

        var expectedEmail = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));

       Assertions.assertEquals(expectedPhone, app.contacts().getContactPhoneFromGrid(contact));
       Assertions.assertEquals(expectedAddress, app.contacts().getContactAddressFromGrid(contact));
       Assertions.assertEquals(expectedEmail, app.contacts().getContactEmailFromGrid(contact));

    }

}
