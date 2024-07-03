package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static tests.TestBase.app;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("First Name","Last Name","Test Address","email@email.com","+79161307546"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount-1,newContactCount);
    }

    @Test
    public void canRemoveAllContactsAtOnce(){
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("First Name","Last Name","Test Address","email@email.com","+79161307546"));
        }

        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getCount());
    }
}
