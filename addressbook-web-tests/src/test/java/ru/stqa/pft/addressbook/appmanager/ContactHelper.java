package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper{

  public ContactHelper(FirefoxDriver wd)  {
    super(wd);
  }

  public void goToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("middlename"),contactData.getMiddleName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("nickname"),contactData.getNickName());
    type(By.name("title"),contactData.getTitle());
    type(By.name("company"),contactData.getCompanyName());
    type(By.name("address"),contactData.getAddressName());
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkName());
    type(By.name("fax"),contactData.getFaxPhone());
    type(By.name("email"),contactData.getEmail_one());
    type(By.name("homepage"),contactData.getHomePage());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }
}
