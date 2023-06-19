package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
  File photo = new File("src/test/resources/stru.jpg");

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0) { //данные из базы (.db().)
      app.createGroupIfNot();
      app.contact().createContact(new contactData().withFirstName("000").withLastName("000").withAdress("000")
              .withEmail("000").withEmail2("000").withEmail3("000")
              .withHomePhone("000").withMobilePhone("000").withWorkPhone("000").withPhoto(photo));
      app.GoTo().HomePage();
    }
  }

  @Test(enabled = true)
  public void testContactModificationTests() throws Exception {
    Contacts before =  app.db().contacts();
    contactData modifiedContact = before.iterator().next();
    contactData contact = new contactData()
            .withId(modifiedContact.getId()).withFirstName("000").withLastName("000").withAdress("000")
            .withEmail("000").withEmail2("000").withEmail3("000")
            .withHomePhone("000").withMobilePhone("000").withWorkPhone("000").withPhoto(photo);
    app.contact().modify(contact);
    app.GoTo().HomePage();
    assertThat(app.contact().count(), Matchers.equalTo(before.size())); //сравнение значения из бд и кол-ва записей на странице портала
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
