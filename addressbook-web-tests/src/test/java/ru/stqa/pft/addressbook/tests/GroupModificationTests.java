 package ru.stqa.pft.addressbook.tests;

 import org.hamcrest.CoreMatchers;
 import org.testng.annotations.BeforeMethod;
 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.Groups;
 import ru.stqa.pft.addressbook.model.groupData;

 import static org.hamcrest.CoreMatchers.equalTo;
 import static org.hamcrest.MatcherAssert.assertThat;

 public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions(){
     app.GoTo().GroupPage();
     if (app.group().all().size() == 0){
       app.group().create(new groupData().withName("test1"));
     }
   }
   @Test
   public void testGroupModification() throws Exception {
     Groups before =  app.db().groups();
     groupData modifiedGroup = before.iterator().next();
     groupData group = new groupData().
             withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
     app.GoTo().GroupPage();
     app.group().modify(group);
     assertThat(app.group().Count(), equalTo(before.size()));
     Groups after =  app.db().groups();
     assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));
     verifyGroupListInUI();
   }

 }
