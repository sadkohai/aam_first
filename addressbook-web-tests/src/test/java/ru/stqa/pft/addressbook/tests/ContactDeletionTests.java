package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.GoTo().HomePage();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new contactData().withFirstName("Anthonio").withLastName("Huan"));
    }
  }
  @Test(enabled = true)
  public void testContactDeletionTests() throws Exception {
    Contacts before = app.contact().all();
    contactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.GoTo().HomePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
