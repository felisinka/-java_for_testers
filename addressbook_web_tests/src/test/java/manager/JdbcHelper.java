package manager;

import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try ( var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
        var statement = conn.createStatement();
      var result = statement.executeQuery("select group_id, group_name, group_header, group_footer from group_list")
        ){
            while(result.next()){

              groups.add(new GroupData().withId(result.getString("group_id"))
                      .withName(result.getString("group_name"))
                      .withHeader(result.getString("group_header"))
                      .withFooter(result.getString("group_footer")));
            }
              } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try ( var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
              var statement = conn.createStatement();
              var result = statement.executeQuery("select * from address_in_groups ag LEFT JOIN addressbook ab ON ab.id=ag.id where ab.id is null")
        ){
            if (result.next()){
                throw  new IllegalStateException("DB is corrupted");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContactData> getContactsNotInGroup(GroupData group) {
        var contacts = new ArrayList<ContactData>();
        try ( var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
              var statement = conn.createStatement();
              var result = statement.executeQuery("select ab.id id, firstname, lastname from addressbook ab LEFT JOIN address_in_groups ag ON ab.id=ag.id where group_id is null or group_id not in ("+group.id()+")")
        ){
            while(result.next()){

                contacts.add(new ContactData().withId(result.getString("id"))
                        .withFirstName(result.getString("firstname"))
                                .withLastName(result.getString("lastname"))
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
