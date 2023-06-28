package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName",
              "nickName", "Title", "companyName", "addressName", "homePhone",
              "mobilePhone", "workName", "faxPhone", "email1", "test1",
              "homePage"));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("FirstName1", "MiddleName1", "LastName1",
            "nickName", "Title", "companyName", "addressName", "homePhone",
            "mobilePhone", "workName", "faxPhone", "email1", null,"homePage"),
            false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
