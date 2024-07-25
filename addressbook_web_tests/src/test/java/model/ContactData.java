package model;

public record ContactData (String id,
                           String firstName,
                           String lastName,
                           String address,
                           String email,
                           String email2,
                           String email3,
                           String homePhone,
                           String photo,
                           String mobile,
                           String work,
                           String secondary) {
public ContactData() {
    this ("", "", "", "", "", "", "", "", "", "", "", "");
}

public ContactData withId(String id) {
    return new ContactData (id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withFirstName(String firstName) {
    return new ContactData (this.id, firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withLastName(String lastName) {
    return new ContactData (this.id, this.firstName, lastName, this.address, this.email, this.email2, this.email3, this.homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withAddress(String address) {
    return new ContactData (this.id, this.firstName, this.lastName, address, this.email, this.email2, this.email3, this.homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withEmail(String email) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, email, this.email2, this.email3, this.homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withEmail2(String email2) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, email2, this.email3, this.homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withEmail3(String email3) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, this.email2, email3, this.homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withHomePhone(String homePhone) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, homePhone, this.photo, this.mobile, this.work, this.secondary);
}

public ContactData withPhoto(String photo) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.homePhone, photo, this.mobile, this.work, this.secondary);
}

public ContactData withMobile(String mobile) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.homePhone, this.photo, mobile, this.work, this.secondary);
}

public ContactData withWork(String work) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.homePhone, this.photo, this.mobile, work, this.secondary);
}

public ContactData withSecondary(String secondary) {
    return new ContactData (this.id, this.firstName, this.lastName, this.address, this.email, this.email2, this.email3, this.homePhone, this.photo, this.mobile, this.work, secondary);
}
}
