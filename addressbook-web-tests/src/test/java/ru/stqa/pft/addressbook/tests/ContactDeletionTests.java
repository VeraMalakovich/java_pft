package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test(enabled = false)
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName",
              "nickName", "Title", "companyName", "addressName", "homePhone",
              "mobilePhone", "workName", "faxPhone", "email1", "test1",
              "homePage"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
