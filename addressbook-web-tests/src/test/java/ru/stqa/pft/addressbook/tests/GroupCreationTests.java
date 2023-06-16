 package ru.stqa.pft.addressbook.tests; //ready

 import com.google.common.reflect.TypeToken;
 import com.google.gson.Gson;
 import com.thoughtworks.xstream.XStream;
 import org.testng.annotations.DataProvider;
 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.Groups;
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

 public class GroupCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validGroupsFromXml() throws IOException {
     try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
       String xml = "";
       String line = reader.readLine();
       while (line != null) {
         xml += line;
         line = reader.readLine();
       }
       XStream xstream = new XStream();
       xstream.processAnnotations(groupData.class);
       List<groupData> groups = (List<groupData>) xstream.fromXML(xml);
       return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
     }
   }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<groupData> groups = gson.fromJson(json, new TypeToken<List<groupData>>(){}.getType());
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }


   @Test (dataProvider = "validGroupsFromJson")
   public void testGroupCreation(groupData group) {
     app.GoTo().GroupPage();
     Groups before =  app.group().all();
     app.group().create(group);
     assertThat(app.group().Count(), equalTo(before.size() + 1));
     Groups after =  app.group().all();
     assertThat(after, equalTo(
             before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

   @Test (enabled = false)
   public void testBadGroupCreation()    {
     app.GoTo().GroupPage();
     Groups before =  app.group().all();
     groupData group = new groupData().withName("test2'");
     app.group().create(group);
     assertThat(app.group().Count(), equalTo(before.size()));
     Groups after =  app.group().all();
     assertThat(after, equalTo(before));
   }
 }