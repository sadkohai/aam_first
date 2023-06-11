package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionTests() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new contactData("Antonijo", "Huan", null, null, null));
    }
    List<contactData> before = app.getContactHelper().getContactList();
    contactData contact = new contactData("Antonijo", "Huan", null, null, null);
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().assertDeleteContact();
    app.getNavigationHelper().gotoHomePage();
    List<contactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
    System.out.println("before" + before);
    System.out.println("after" + after);
  }
}
