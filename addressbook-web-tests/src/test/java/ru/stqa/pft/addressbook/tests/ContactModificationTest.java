package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModificationTests() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new contactData("Antonijo", "Huan", null, null, null));
    }
    List<contactData> before =  app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size() - 1);
    contactData contact = new contactData(before.get(before.size() - 1).getId(),"Antonijo", "Huan", null, null, null);
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<contactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);


  }
}
