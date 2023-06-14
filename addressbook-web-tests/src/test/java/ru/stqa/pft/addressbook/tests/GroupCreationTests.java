 package ru.stqa.pft.addressbook.tests; //ready

 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.Groups;
 import ru.stqa.pft.addressbook.model.groupData;

 import static org.hamcrest.CoreMatchers.equalTo;
 import static org.hamcrest.MatcherAssert.assertThat;

 public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation()   {
     app.GoTo().GroupPage();
     Groups before =  app.group().all();
     groupData group = new groupData().withName("test1");
     app.group().create(group);
     Groups after =  app.group().all();
     assertThat(after.size(), equalTo(before.size() + 1));
     assertThat(after, equalTo(
             before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

 }