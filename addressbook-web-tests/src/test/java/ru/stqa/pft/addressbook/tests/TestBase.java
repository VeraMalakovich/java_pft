package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.Browser;

import java.io.IOException;

public class TestBase {
  protected static final ApplicationManager app;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", Browser.FIREFOX.browserName()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.stop();
  }
}
