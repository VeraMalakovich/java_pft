package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    File avatar = new File("src/test/resources/avatar.jpg");
    if (app.db().contacts().size() == 0) {
      if (app.contact().all().size() == 0) {
        app.contact().create(new ContactData().withFirstName("Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
                .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("Ptituchkogo 86, 30")
                .withHomePhone("315-38-12").withMobilePhone("+375295789988").withWorkPhone("22 333 444").withFaxPhone("faxPhone")
                .withEmail("email1").withEmail2("vera2@tut.by").withEmail3("vera3@tut.by").withNewGroup("test1")
                .withSecondAddress("Minsk").withSecondHomePhone("+375295789528").withHomePage("homePage"));
      }
    }
  }

    @Test
    public void testContactInformation() {
      app.goTo().homePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      //проверка телефонов
      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

      //проверка email-ов
      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

      //проверка адресов
      assertThat(cleanedAddress(contact.getAddressName()), equalTo(cleanedAddress(contactInfoFromEditForm.getAddressName())));
    }

    private String mergePhones(ContactData contact) {
      return Stream.of(contact.getHomePhone(), contact.getMobilePhone(),
                      contact.getWorkPhone(), contact.getSecondHomePhone())
              .filter((s) -> !s.equals(""))
              .map(ContactInformationTests::cleanedPhones)
              .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
      return Stream.of(contact.getEmail(), contact.getSecondEmail(), contact.getThirdEmail())
              .filter((s) -> !s.equals(""))
              .map(ContactInformationTests::cleanedEmails)
              .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String cleanedAddress(String address) {
      return address.replaceAll("\n", "");
    }

    public static String cleanedEmails(String phone) {
      return phone.trim();
    }
}
