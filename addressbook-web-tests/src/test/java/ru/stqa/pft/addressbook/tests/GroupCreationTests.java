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
     assertThat(app.group().Count(), equalTo(before.size() + 1));
     Groups after =  app.group().all();
     assertThat(after, equalTo(
             before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
   }

   @Test
   public void testBadGroupCreation()   {
     app.GoTo().GroupPage();
     Groups before =  app.group().all();
     groupData group = new groupData().withName("test2'");
     app.group().create(group);
     assertThat(app.group().Count(), equalTo(before.size()));
     Groups after =  app.group().all();
     assertThat(after, equalTo(before));
   }
 }