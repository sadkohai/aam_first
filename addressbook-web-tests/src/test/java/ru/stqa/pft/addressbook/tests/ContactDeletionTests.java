package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionTests extends TestBase {
  File photo = new File("src/test/resources/stru.jpg");

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) { //данные из базы (.db().)
        app.createGroupIfNot();
        app.contact().createContact(new contactData().withFirstName("000").withLastName("000").withAdress("000")
                .withEmail("000").withEmail2("000").withEmail3("000")
                .withHomePhone("000").withMobilePhone("000").withWorkPhone("000").withPhoto(photo));
        app.GoTo().HomePage();
      }
    }

  @Test(enabled = true)
  public void testContactDeletionTests() throws Exception {
    Contacts before = app.db().contacts();
    contactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.GoTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1)); //сравнение значения из бд и кол-ва записей на странице портала
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
