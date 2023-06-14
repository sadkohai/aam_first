 package ru.stqa.pft.addressbook.tests;

 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.Contacts;
 import ru.stqa.pft.addressbook.model.contactData;

 import static org.hamcrest.CoreMatchers.equalTo;
 import static org.hamcrest.MatcherAssert.assertThat;

 public class ContactCreationTests extends TestBase {

   @Test (enabled = true)
   public void testContactCreationTests() throws Exception {
     Contacts before = app.contact().all();
     contactData contact =  new contactData().withFirstName("Anthonio").withLastName("Huan");
     app.contact().gotoAddContact();
     if (! app.contact().chooseGroup()) {
       new GroupCreationTests().testGroupCreation();
       app.contact().gotoAddContact();
       app.contact().chooseGroup();
     }
     app.contact().createContact(contact);
     Contacts after = app.contact().all();
     assertThat(after.size(), equalTo(before.size() + 1));
     assertThat(after, equalTo(
             before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
   }

 }
