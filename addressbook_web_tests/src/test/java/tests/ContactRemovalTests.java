package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount()==0){//contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""));
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
    public void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount()==0) {
            app.hbm().createGroup(new GroupData("", "kate group", "kate group header", "kate group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        if (app.hbm().getContactsInGroup(group).isEmpty())
        {
            app.contacts().createContact(
                    new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""),
                    group);
        };
        var oldContacts = app.hbm().getContactsInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contact = oldContacts.get(index);
        app.contacts().removeContactFromGroup(contact, group);
        var newContacts = app.hbm().getContactsInGroup(group);//contacts().getList();
        Assertions.assertEquals(newContacts.size(), oldContacts.size()-1);

    }

    @Test
    public void canRemoveAllContactsAtOnce(){
        if (app.hbm().getContactCount()==0) {
            app.contacts().createContact(new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""));
        }

        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getCount());
    }
}
