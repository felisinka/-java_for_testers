package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTests extends TestBase{

    public static List<ContactData> contactModifyProvider() {
        var result = new ArrayList<ContactData>();
        result.add(new ContactData("", "first name for modification",
                "last name for modification", "address for modification", "email for modification",
                "", "", "homePhone for modification", "", "", "", ""));

     /*   for (int i=1;i<5;i++){
            result.add(new ContactData("", randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10)));
        }*/
        return result;
    }


    @ParameterizedTest
    @MethodSource("contactModifyProvider")
    void canModifyContact(ContactData contact) {
        if (app.hbm().getContactCount()==0){
               // contacts().getCount()==0) {
            app.contacts().createContact(contact);
        }
        var oldContacts = app.hbm().getContactList();
        //contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        //contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index,testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
    @Test
    public void canAddContactToGroup() {
        if (app.hbm().getGroupCount()==0) {
            app.hbm().createGroup(new GroupData("", "kate group", "kate group header", "kate group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var contacts = app.jdbc().getContactsNotInGroup(group);
        if (contacts.isEmpty())
        {
            app.contacts().createContact(
                    new ContactData("", "First Name", "Last Name", "Test Address", "email@email.com", "", "", "+79161307546", "", "", "", ""));
            contacts = app.jdbc().getContactsNotInGroup(group);
        };
        var oldRelated = app.hbm().getContactsInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contact = contacts.get(index);
        app.contacts().addContactToGroup(contact,group);
        var newRelated = app.hbm().getContactsInGroup(group);

        Assertions.assertEquals(oldRelated.size()+1,newRelated.size());

    }


}
