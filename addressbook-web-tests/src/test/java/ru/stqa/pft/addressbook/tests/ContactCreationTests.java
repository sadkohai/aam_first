 package ru.stqa.pft.addressbook.tests;

 import org.testng.annotations.*;
 import ru.stqa.pft.addressbook.model.contactData;

 public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreationTests() throws Exception {
     app.getContactHelper().gotoAddContact();
     app.getContactHelper().fillContactForm(new contactData("Mihalcov", "Vladimir", "Poland, 10", "testmailcontact@mail.ru", "89999757811"));
     app.getContactHelper().submitContactCreation();
     app.getNavigationHelper().gotoHomePage();
   }

 }
