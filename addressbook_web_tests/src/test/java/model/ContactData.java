package model;

public record ContactData (String id, String firstName, String lastName, String address, String email, String homePhone ) {
public ContactData() {
    this ("", "", "", "", "", "");
}

public ContactData withId(String id) {
    return new ContactData (id, this.firstName, this.lastName, this.address, this.email, this.homePhone);
}

public ContactData withFirstName(String firstName) {
    return new ContactData (this.id, firstName, this.lastName, this.address, this.email, this.homePhone);
}

public ContactData withLastName(String lastName) {
    return new ContactData (this.id, this.firstName, lastName, this.address, this.email, this.homePhone);
}

public ContactData withAddress(String address) {
    return new ContactData (this.id, this.firstName, this.lastName, address, this.email, this.homePhone);
}

public ContactData withEmail(String email) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, email, this.homePhone);
}

public ContactData withHomePhone(String homePhone) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, homePhone);
}
}
