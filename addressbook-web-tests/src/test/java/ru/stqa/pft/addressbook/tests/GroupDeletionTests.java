 package ru.stqa.pft.addressbook.tests;

 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.groupData;

 public class GroupDeletionTests extends TestBase {


   @Test
   public void testGroupDeletionTests() throws Exception {
     app.getNavigationHelper().gotoGroupPage();
     if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new groupData("test1", null, null));
     }
     app.getGroupHelper().selectGroup();
     app.getGroupHelper().deleteSelectedGroups();
     app.getGroupHelper().returnToGroupPage();
   }
 }
