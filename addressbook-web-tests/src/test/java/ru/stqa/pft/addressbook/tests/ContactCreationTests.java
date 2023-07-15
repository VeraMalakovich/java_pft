package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @Test //(enabled = false)
  public void testContactCreationTests() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/avatar.jpg");
    System.out.println(photo.getAbsolutePath());
    ContactData contact = new ContactData().withFirstName("Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
            .withNickName("nickName").withPhoto(photo).withTitle("Title").withCompany("companyName").withAddress("addressName")
            .withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone")
            .withEmail("email1").withNewGroup("test1").withHomePage("homePage");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBedContactCreationTests() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/avatar.jpg");
    ContactData contact = new ContactData().withFirstName("''Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
            .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("addressName")
            .withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone")
            .withEmail("email1").withNewGroup("test1").withHomePage("homePage").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/avatar.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
