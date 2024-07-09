package tests;

import model.ContactData;
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
                "homePhone for modification"));

     /*   for (int i=1;i<5;i++){
            result.add(new ContactData("", randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10)));
        }*/
        return result;
    }


    @ParameterizedTest
    @MethodSource("contactModifyProvider")
    void canModifyContact(ContactData contact) {
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(contact);
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index,testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
