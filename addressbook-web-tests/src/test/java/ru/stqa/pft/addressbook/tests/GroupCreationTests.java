 package ru.stqa.pft.addressbook.tests; //ready

 import org.testng.annotations.*;
 import ru.stqa.pft.addressbook.model.groupData;

 public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation()   {
     app.getNavigationHelper().gotoGroupPage();
     app.getGroupHelper().selectGroup();
     app.getGroupHelper().initGroupCreation();
     app.getGroupHelper().fillGroupForm(new groupData("test1", null, null));
     app.getGroupHelper().submitGroupCreation();
     app.getGroupHelper().returnToGroupPage();
   }

 }
