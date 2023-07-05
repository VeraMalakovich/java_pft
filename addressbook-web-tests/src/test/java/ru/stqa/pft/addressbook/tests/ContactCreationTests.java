package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;
import org.testng.Assert;

public class ContactCreationTests extends TestBase {
  @Test(enabled = false)
  public void testContactCreationTests() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Vera", "Anatolevna", "Malakovich",
            "nickName", "Title", "companyName", "addressName", "homePhone",
            "mobilePhone", "workName", "faxPhone", "email1", "test1",
            "homePage");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max(app.getContactHelper().byId).get().getId());
    before.add(contact);
    before.sort(app.getContactHelper().byId);
    after.sort(app.getContactHelper().byId);
    Assert.assertEquals(before, after);
  }
}
