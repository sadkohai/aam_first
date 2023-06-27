package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

  //@BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws Exception, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);
//    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); //первое значение это то сколько сообщений ожидается к получению
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 180000);
    String confirmationLink = app.session().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  //@AfterMethod (alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
