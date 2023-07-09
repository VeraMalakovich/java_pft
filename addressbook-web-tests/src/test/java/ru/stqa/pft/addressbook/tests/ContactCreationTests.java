package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;
import org.testng.Assert;
import java.util.Set;

public class ContactCreationTests extends TestBase {
  @Test(enabled = false)
  public void testContactCreationTests() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
            .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("addressName")
            .withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone")
            .withEmail("email1").withNewGroup("test1").withHomePage("homePage");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
