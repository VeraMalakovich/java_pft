package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    File photo = new File("src/test/resources/avatar_to_change.jpg");
    //if (app.contact().all().size() == 0) {
    if (app.db().contacts().size() == 0) {
      if (app.contact().all().size() == 0) {
        app.contact().create(new ContactData().withFirstName("Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
                .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("addressName")
                .withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone")
                .withEmail("email1").withHomePage("homePage").withPhoto(photo));//.withNewGroup("test1")
      }
    }
  }

  @Test
  public void testContactModification() {
    //Contacts before = app.contact().all();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Vera").withLastName("Malakovich");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    //Contacts after = app.contact().all();
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(modifiedContact, contact)));
  }
}
