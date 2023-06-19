 package ru.stqa.pft.addressbook.tests;

 import com.google.common.reflect.TypeToken;
 import com.google.gson.Gson;
 import com.thoughtworks.xstream.XStream;
 import org.hamcrest.Matchers;
 import org.testng.annotations.DataProvider;
 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.Contacts;
 import ru.stqa.pft.addressbook.model.contactData;
 import ru.stqa.pft.addressbook.model.groupData;

 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.Iterator;
 import java.util.List;
 import java.util.stream.Collectors;

 import static org.hamcrest.CoreMatchers.equalTo;
 import static org.hamcrest.MatcherAssert.assertThat;

 public class ContactCreationTests extends TestBase {
   @DataProvider
   public Iterator<Object[]> validContactsFromXml() throws IOException {
     //File photo = new File("src/test/resources/stru.jpg"); //Добавляем аттач (относительный путь)
     try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
       String xml = "";
       String line = reader.readLine();
       while (line != null) {
         xml += line;
         line = reader.readLine();
       }
       XStream xstream = new XStream();
       xstream.processAnnotations(contactData.class);
       List<contactData> contacts = (List<contactData>) xstream.fromXML(xml);
       return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
     }
   }

   @DataProvider
   public Iterator<Object[]> validContactsFromJson() throws IOException {
     //File photo = new File("src/test/resources/stru.jpg"); //Добавляем аттач (относительный путь)
     try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
       String json = "";
       String line = reader.readLine();
       while (line != null) {
         json += line;
         line = reader.readLine();
       }
       Gson gson = new Gson();
       List<contactData> contacts = gson.fromJson(json, new TypeToken<List<contactData>>(){}.getType());
       return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
     }
   }

   @Test (dataProvider = "validContactsFromJson")
   public void testContactCreationTests(contactData contact) throws Exception {
     Contacts before = app.db().contacts();
     app.contact().gotoAddContact();
     if (! app.contact().chooseGroup()) {
       new GroupCreationTests().testGroupCreation(new groupData().withName("test1").withHeader("test2").withFooter("test3"));
       app.contact().gotoAddContact();
       app.contact().chooseGroup();
     }
     app.contact().createContact(contact);
     assertThat(app.contact().count(), Matchers.equalTo(before.size() + 1)); //сравнение значения из бд и кол-ва записей на странице портала
     Contacts after = app.db().contacts();
     assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
   }
 }
