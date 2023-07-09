package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;
import org.testng.Assert;

public class ContactCreationTests extends TestBase {
  @Test(enabled = false)
  public void testContactCreationTests() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Vera", "Anatolevna", "Malakovich",
            "nickName", "Title", "companyName", "addressName", "homePhone",
            "mobilePhone", "workName", "faxPhone", "email1", "test1",
            "homePage");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max(app.contact().byId).get().getId());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
