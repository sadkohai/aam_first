 package ru.stqa.pft.addressbook.tests; //ready

 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.groupData;

 public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation()   {
     app.getNavigationHelper().gotoGroupPage();
     app.getGroupHelper().createGroup(new groupData("test1", null, null));
   }

 }
