package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreationTests() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("FirstName", "MiddleName", "LastName",
            "nickName", "Title", "companyName", "addressName", "homePhone",
            "mobilePhone", "workName", "faxPhone", "email1", "test1","homePage"),
            true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }
}
