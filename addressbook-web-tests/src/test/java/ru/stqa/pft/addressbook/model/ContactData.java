package ru.stqa.pft.addressbook.model;

public class ContactData {
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
}
