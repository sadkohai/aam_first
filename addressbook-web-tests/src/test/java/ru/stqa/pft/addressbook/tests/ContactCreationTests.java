 package ru.stqa.pft.addressbook.tests;

 import org.testng.annotations.*;
 import ru.stqa.pft.addressbook.model.contactData;

 public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreationTests() throws Exception {
     app.getContactHelper().createContact(new contactData("Antonijo", "Huan", "Three city", "rlymile@mai.ru", "89261231122", "test1"));
   }

 }
