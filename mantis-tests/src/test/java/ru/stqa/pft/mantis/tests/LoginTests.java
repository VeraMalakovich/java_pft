package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

  @Test
  public void testLogin() throws IOException {
    //Logger.getLogger(LoginTests.class).debug("test");
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
