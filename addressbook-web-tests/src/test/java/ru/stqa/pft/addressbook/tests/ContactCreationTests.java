package ru.stqa.pft.addressbook.tests;

import com.google.gson.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase implements JsonDeserializer<File> {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new GsonBuilder()
              .registerTypeAdapter(File.class, new ContactCreationTests())
              .create();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreationTests(ContactData contact) {
    GroupData group = contact.getGroups().iterator().next();
    if (!checkGroup(group)){
      app.goTo().groupPage();
      app.group().create(group);
    }
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().create(contact);
    //assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

  public boolean checkGroup(GroupData contactGroup){
    Groups groups = app.db().groups();
    for (GroupData group : groups) {
      if (group.getName().equals(contactGroup.getName())){
        return true;
      }
    }
    return false;
  }

  @Test (enabled = false)
  public void testBedContactCreationTests() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/avatar.jpg");
    ContactData contact = new ContactData().withFirstName("''Vera").withMiddleName("Anatolevna").withLastName("Malakovich")
            .withNickName("nickName").withTitle("Title").withCompany("companyName").withAddress("addressName")
            .withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withFaxPhone("faxPhone")
            .withEmail("email1").withHomePage("homePage").withPhoto(photo);//.withNewGroup("test1")
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

  @Override
  public File deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return new File(json.getAsString());
  }
}
