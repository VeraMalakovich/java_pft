package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class BaseHelper {
  protected WebDriver wd;

  public BaseHelper(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String exitingText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(exitingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File photo) {
    if (photo != null) {
      wd.findElement(locator).sendKeys(photo.getAbsolutePath());
    }
  }

  protected void selectByText(By locator, String text) {
    click(locator);
    new Select(wd.findElement(locator)).selectByVisibleText(text);
  }

  protected void selectDefault(By locator) {
    click(locator);
    new Select(wd.findElement(locator)).selectByIndex(0);
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public boolean isElementPresent(By locator) {
    try{
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
