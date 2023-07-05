package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test(enabled = false)
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName",
              "nickName", "Title", "companyName", "addressName", "homePhone",
              "mobilePhone", "workName", "faxPhone", "email1", "test1",
              "homePage"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Vera", "Malakovich");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    before.sort(app.getContactHelper().byId);
    after.sort(app.getContactHelper().byId);
    Assert.assertEquals(before, after);
  }
}
