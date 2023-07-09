package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
              .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("addressName")
              .withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone")
              .withEmail("email1").withNewGroup("test1").withHomePage("homePage"));
    }
  }

  @Test
  public void testContactDeletion() throws InterruptedException {
    Contacts before = app.contact().all();
    //System.out.println("before.size()0:" + before.size());
    ContactData deletedContact = before.iterator().next();
    //System.out.println("before.size()1:" + before.size());
    app.contact().delete(deletedContact);
    //System.out.println("before.size()2:" + before.size());
    app.goTo().homePage();
    Thread.sleep(500);
    Contacts after = app.contact().all();
    //System.out.println("after.size():" + after.size());
    //System.out.println("before.size()3:" + before.size());
    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
