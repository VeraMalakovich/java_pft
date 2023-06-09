package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.Browser;

public class TestBase {
  protected static final ApplicationManager app = new ApplicationManager(Browser.FIREFOX.browserName());

  @BeforeSuite(alwaysRun = true)
  public void setUp() {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }
}
