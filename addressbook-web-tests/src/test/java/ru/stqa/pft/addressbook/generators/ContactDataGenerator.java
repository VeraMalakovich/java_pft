package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator implements JsonSerializer<File> {

  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(File.class, new ContactDataGenerator())
            .excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    File avatar = new File("src/test/resources/avatar.jpg");

    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("firstName %s", i))
              .withMiddleName(String.format("middleName %s", i)).withLastName(String.format("lastName %s", i))
              .withNickName(String.format("nickName %s", i)).withPhoto(avatar).withTitle(String.format("title %s", i))
              .withCompany(String.format("company %s", i)).withAddress(String.format("address %s", i))
              .withMobilePhone(String.format("+%s%s", i, i)).withEmail(String.format("email%s@mail.com", i))
              .withEmail2(String.format("email%s@yandex.com", i)).withEmail3(String.format("email%s@gmail.com", i))
              .withHomePhone(String.format("+%s%s%s", i, i, i)).withWorkPhone(String.format("+%s%s%s%s", i, i, i, i))
              .withFaxPhone(String.format("+%s", i)).withHomePage(String.format("home_page/%s", i))
              .withNewGroup(String.format("test %s", i)).withSecondAddress(String.format("address_2 %s", i))
              .withSecondHomePhone(String.format("+%s%s", i, i)));
    }
    return contacts;
  }

  @Override
  public JsonElement serialize(File file, Type typeOfFile, JsonSerializationContext context) {
    return context.serialize(file.getPath());
  }
}
