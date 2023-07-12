package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
              .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("Ptituchkogo 86, 30")
              .withHomePhone("315-38-12").withMobilePhone("+375295789988").withWorkPhone("22 333 444").withFaxPhone("faxPhone")
              .withEmail("email1").withEmail2("vera2@tut.by").withEmail3("vera3@tut.by").withNewGroup("test1")
              .withSecondAddress("Minsk").withSecondHomePhone("+375295789528").withHomePage("homePage"));
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(cleaned(contact.getAddressName()), equalTo(cleaned(contactInfoFromEditForm.getAddressName())));
  }

  public static String cleaned(String address) {
    return address.replaceAll("\\s", "").replaceAll("\n", "");
  }
}
