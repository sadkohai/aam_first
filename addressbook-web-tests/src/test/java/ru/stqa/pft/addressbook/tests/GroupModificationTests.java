 package ru.stqa.pft.addressbook.tests;

 import org.testng.annotations.Test;
 import ru.stqa.pft.addressbook.model.groupData;

 public class GroupModificationTests extends TestBase {

   @Test
   public void testGroupModification() throws Exception {
     app.getNavigationHelper().gotoGroupPage();
     if (!app.getGroupHelper().isThereAGroup()){
       app.getGroupHelper().createGroup(new groupData("test1", null, null));
     }
     app.getGroupHelper().selectGroup();
     app.getGroupHelper().initGroupModification();
     app.getGroupHelper().fillGroupForm(new groupData("tested1", "tested2", "tested3"));
     app.getGroupHelper().submitGroupModification();
     app.getGroupHelper().returnToGroupPage();
   }
 }
