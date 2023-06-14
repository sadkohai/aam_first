 package ru.stqa.pft.addressbook.tests;

 import org.hamcrest.CoreMatchers;
 import org.testng.annotations.BeforeMethod;
 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.Groups;
 import ru.stqa.pft.addressbook.model.groupData;

 import static org.hamcrest.CoreMatchers.equalTo;
 import static org.hamcrest.MatcherAssert.assertThat;

 public class GroupDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions(){
     app.GoTo().GroupPage();
     if (app.group().all().size() == 0){
       app.group().create(new groupData().withName("test1"));
     }
   }

   @Test
   public void testGroupDeletionTests() throws Exception {
     Groups before =  app.group().all();
     groupData deletedGroup = before.iterator().next();
     app.group().delete(deletedGroup);
     assertThat(app.group().Count(), equalTo(before.size() - 1));
     Groups after =  app.group().all();
     assertThat(after, CoreMatchers.equalTo(before.without(deletedGroup)));
     }

 }
