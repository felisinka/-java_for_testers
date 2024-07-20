package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount()==0){//contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "+79161307546", ""));
        }
        var oldContacts = app.hbm().getContactList();//contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contact = oldContacts.get(index);
        app.contacts().removeContact(contact);
        var newContacts = app.hbm().getContactList();//contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);

    }

    @Test
    public void canRemoveAllContactsAtOnce(){
        if (app.hbm().getContactCount()==0) {
            app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "+79161307546", ""));
        }

        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getCount());
    }
}
