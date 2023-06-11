 package ru.stqa.pft.addressbook.tests;

 import org.testng.Assert;
 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.groupData;

 import java.util.List;

 public class GroupDeletionTests extends TestBase {

   @Test
   public void testGroupDeletionTests() throws Exception {
     app.getNavigationHelper().gotoGroupPage();
     if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new groupData("test1", null, null));
     }
     List<groupData> before =  app.getGroupHelper().getGroupList();
     app.getGroupHelper().selectGroup(before.size() - 1);
     app.getGroupHelper().deleteSelectedGroups();
     app.getGroupHelper().returnToGroupPage();
     List<groupData> after =  app.getGroupHelper().getGroupList();
     Assert.assertEquals(after.size(), before.size() - 1);

     before.remove(before.size() - 1);
       Assert.assertEquals(before, after);
     }
 }
