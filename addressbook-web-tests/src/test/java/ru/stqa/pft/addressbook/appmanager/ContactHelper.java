package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper{

  public ContactHelper(WebDriver wd)  {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.withFirstName());//get
    type(By.name("middlename"), contactData.withMiddleName());
    type(By.name("lastname"), contactData.withLastName());
    type(By.name("nickname"), contactData.withNickName());
    type(By.name("title"), contactData.withTitle());
    type(By.name("company"), contactData.withCompany());
    type(By.name("address"), contactData.withAddress());
    type(By.name("home"), contactData.withHomePhone());
    type(By.name("mobile"), contactData.withMobilePhone());
    type(By.name("work"), contactData.withWorkPhone());
    type(By.name("fax"), contactData.withFaxPhone());
    type(By.name("email"), contactData.withEmail());
    type(By.name("homepage"), contactData.withHomePage());

    if (creation) {
      try {
        selectByText(By.name("new_group"), contactData.withNewGroup());
      } catch (Exception NoSuchElementException) {
        selectByIndex(By.name("new_group"), 0);
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    confirmContactDeletion();
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }

  public void create(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
    returnToHomePage();
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = HashSet<>();
    List<WebElement> contactElements = wd.findElements(By.cssSelector("tr"));
    contactElements.remove(0);

    for (WebElement element: contactElements) {
      List<WebElement> contactDataElements = element.findElements(By.cssSelector("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = contactDataElements.get(1).getText();
      String firstName = contactDataElements.get(2).getText();

      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contact);
    }
    return contacts;
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();//
    //returnToHomePage();??
  }
}
