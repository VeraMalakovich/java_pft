package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompanyName());
    type(By.name("address"), contactData.getAddressName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFaxPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getSecondEmail());
    type(By.name("email3"), contactData.getThirdEmail());
    type(By.name("homepage"), contactData.getHomePage());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      try {
        selectByText(By.name("new_group"), contactData.getNewGroup());
      } catch (Exception NoSuchElementException) {
        selectDefault(By.name("new_group"));
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

  public void initContactModification(int id) {
    //wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }

  public void create(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    returnToHomePage();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> contactRows = wd.findElements(By.cssSelector("tr"));
    contactRows.remove(0);

    for (WebElement element: contactRows) {
      List<WebElement> contactDataCells = element.findElements(By.cssSelector("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = contactDataCells.get(1).getText();
      String firstName = contactDataCells.get(2).getText();
      String address = contactDataCells.get(3).getText();
      String allEmails = contactDataCells.get(4).getText();
      String allPhones = contactDataCells.get(5).getText();

      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
        .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
    String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String secondHomePhone = wd.findElement(By.name("phone2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withAddress(address).withEmail(email).withEmail2(secondEmail).withEmail3(thirdEmail)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withSecondHomePhone(secondHomePhone);
  }
}
