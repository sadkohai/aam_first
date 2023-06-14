package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.GoTo().HomePage();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new contactData().withFirstName("Anthonio").withLastName("Huan"));
    }
  }

  @Test(enabled = true)
  public void testContactModificationTests() throws Exception {
    Contacts before =  app.contact().all();
    contactData modifiedContact = before.iterator().next();
    contactData contact = new contactData()
            .withId(modifiedContact.getId()).withFirstName("Anthonio2").withLastName("Huan2");
    app.contact().modify(contact);
    app.GoTo().HomePage();
    Contacts after = app.contact().all();
    assertEquals(before.size(), after.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
