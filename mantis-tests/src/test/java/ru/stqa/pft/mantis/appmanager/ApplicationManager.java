package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
  private final String browser;
  private final Properties properties;
  private RegistrationHelper registrationHelper;
  private WebDriver wd;

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
  }

  public void stop() {
    if (wd != null){
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public WebDriver getDriver() {
    if (wd == null) {
      if (browser.equals(Browser.CHROME.browserName())) {
        wd = new ChromeDriver();
      } else if (browser.equals(Browser.FIREFOX.browserName())) {
        wd = new FirefoxDriver();
      } else if (browser.equals(Browser.IE.browserName())) {
        wd = new InternetExplorerDriver();
      }

      wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
}
