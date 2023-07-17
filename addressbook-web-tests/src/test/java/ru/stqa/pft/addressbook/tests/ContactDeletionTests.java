package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    File photo = new File("src/test/resources/avatar.jpg");
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
              .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("addressName")
              .withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone")
              .withEmail("email1").withNewGroup("test1").withHomePage("homePage").withPhoto(photo));
    }
  }

  @Test
  public void testContactDeletion() throws InterruptedException {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
