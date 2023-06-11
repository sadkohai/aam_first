 package ru.stqa.pft.addressbook.tests;

 import org.testng.Assert;
 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.groupData;

 import java.util.Comparator;
 import java.util.List;

 public class GroupModificationTests extends TestBase {

   @Test
   public void testGroupModification() throws Exception {
     app.getNavigationHelper().gotoGroupPage();
     if (!app.getGroupHelper().isThereAGroup()){
       app.getGroupHelper().createGroup(new groupData("test1", null, null));
     }
     List<groupData> before =  app.getGroupHelper().getGroupList();
     app.getGroupHelper().selectGroup(before.size() - 1);
     app.getGroupHelper().initGroupModification();
     groupData group = new groupData(before.get(before.size() - 1).getId(),"test1","test2","test3");
     app.getGroupHelper().fillGroupForm(group);
     app.getGroupHelper().submitGroupModification();
     app.getGroupHelper().returnToGroupPage();
     List<groupData> after =  app.getGroupHelper().getGroupList();
     Assert.assertEquals(before.size(), after.size());

     before.remove(before.size() - 1);
     before.add(group);
     Comparator<? super groupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
     before.sort(byId);
     after.sort(byId);
     Assert.assertEquals(before, after);
   }
 }
