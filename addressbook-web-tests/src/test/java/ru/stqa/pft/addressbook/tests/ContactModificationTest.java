package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModificationTests() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new contactData("Antonijo", "Huan", "Three city", "rlymile@mai.ru", "89261231122", "test1"));
    }
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new contactData("UpdateFirstName", "UpdateLastName", "UpdateAdress", "Update@mail.ru", "89997776655", null),false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
