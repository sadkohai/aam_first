 package ru.stqa.pft.addressbook.tests;

 import org.testng.Assert;
 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.contactData;

 import java.util.Comparator;
 import java.util.List;

 public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreationTests() throws Exception {
     List<contactData> before = app.getContactHelper().getContactList();
     contactData contact = new contactData("Antonijo", "Huan", null, null, null);
     app.getContactHelper().gotoAddContact();
     if (! app.getContactHelper().chooseGroup()) {
       new GroupCreationTests().testGroupCreation();
       app.getContactHelper().chooseGroup();
     }
     app.getContactHelper().createContact(contact);
     List<contactData> after = app.getContactHelper().getContactList();
     Assert.assertEquals(after.size(), before.size() + 1);

     before.add(contact);
     Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
     before.sort(byId);
     after.sort(byId);
     Assert.assertEquals(after, before);
   }

 }
