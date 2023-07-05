package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickName;
  private final String title;
  private final String companyName;
  private final String addressName;
  private final String homePhone;
  private final String mobilePhone;
  private final String workName;
  private final String faxPhone;
  private final String email_one;
  private final String group;
  private final String homePage;

  public ContactData(String firstName, String middleName, String lastName, String nickName, String title, String companyName,
                     String addressName, String homePhone, String mobilePhone, String workName, String faxPhone, String email_one,
                     String group, String homePage) {
    this.id = 0; //Integer.MAX_VALUE
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.title = title;
    this.companyName = companyName;
    this.addressName = addressName;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.workName = workName;
    this.faxPhone = faxPhone;
    this.email_one = email_one;
    this.group = group;
    this.homePage = homePage;
  }

  public ContactData(int id, String firstname, String lastname) {
    this.id = id;
    this.firstName = firstname;
    this.lastName = lastname;
    this.middleName = null;
    this.nickName = null;
    this.title = null;
    this.companyName = null;
    this.addressName = null;
    this.homePhone = null;
    this.mobilePhone = null;
    this.workName = null;
    this.faxPhone = null;
    this.email_one = null;
    this.group = null;
    this.homePage = null;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getTitle() {
    return title;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getAddressName() {
    return addressName;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkName() {
    return workName;
  }

  public String getFaxPhone() {
    return faxPhone;
  }

  public String getEmail_one() {
    return email_one;
  }

  public String getHomePage() {
    return homePage;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstName + '\'' +
            ", lastname='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!Objects.equals(firstName, that.firstName)) return false;
    return Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
