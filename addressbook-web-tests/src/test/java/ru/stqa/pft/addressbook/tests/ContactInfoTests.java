package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().gotoAddContact();
      if (! app.contact().chooseGroup()) {
        new GroupCreationTests().testGroupCreation(new groupData().withName("test1").withHeader("test1").withFooter("test1"));
        app.contact().gotoAddContact();
        app.contact().chooseGroup();
      }
      app.contact().createContact(new contactData().withFirstName("111").withLastName("111").withAdress("Ddd ddd")
              .withEmail("111.222@").withEmail2("11-11.222@").withEmail3("22-22@af")
              .withHomePhone("+7 (222)").withMobilePhone("22-22").withWorkPhone("33 33 33"));
    }
  }
  @Test
  public void testContactPhones() {
    app.GoTo().HomePage();
    contactData contact = app.contact().all().iterator().next();
    contactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAdress(), equalTo((contactInfoFromEditForm.getAdress())));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(contactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInfoTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
  private String mergeEmails(contactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }
  public static String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]", "");
  }
}
