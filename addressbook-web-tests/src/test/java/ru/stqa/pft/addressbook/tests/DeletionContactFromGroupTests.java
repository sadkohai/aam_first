package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.io.File;

import static org.testng.AssertJUnit.assertTrue;

public class DeletionContactFromGroupTests extends TestBase {

  File photo = new File("src/test/resources/stru.jpg");

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().verifyContactInGroup().size() == 0) { //данные из базы (.db().)
      app.createGroupIfNot();
      app.contact().createContact(new contactData().withFirstName("000").withLastName("000").withAdress("000")
              .withEmail("000").withEmail2("000").withEmail3("000")
              .withHomePhone("000").withMobilePhone("000").withWorkPhone("000"));
      app.contact().gotoHomePage();
    }
  }

  @Test
  public void testDeletionContactFromGroup() throws Exception {
    contactData before = app.db().contactWithGroup();
    groupData group = before.getGroups().iterator().next();
    app.contact().gotoHomePage();
    app.contact().getGroupData(group);
    app.contact().selectContact(before);
    app.contact().removeContactFromGroup();
    contactData after = app.db().contactById(before.getId());
    System.out.println("11111 " + after);
    System.out.println("222222 " + group);
    assertTrue(after.getGroups().contains(group));
    verifyContactListInUI();
  }
}
