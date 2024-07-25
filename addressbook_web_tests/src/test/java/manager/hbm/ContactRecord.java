package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "address")
    public String address;
    @Column(name = "email")
    public String email;
    @Column(name = "email2")
    public String email2;
    @Column(name = "email3")
    public String email3;
    @Column(name = "home")
    public String homePhone;
    @Column(name = "mobile")
    public String mobile;
    @Column(name = "work")
    public String work;
    @Column(name = "phone2")
    public String phone2;


    public ContactRecord(){

    }

    public ContactRecord(int id, String firstname, String lastname, String address, String email, String homePhone, String photo){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.homePhone = homePhone;

    }

}
