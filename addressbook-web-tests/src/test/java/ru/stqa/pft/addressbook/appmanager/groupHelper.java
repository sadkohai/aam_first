  package ru.stqa.pft.addressbook.appmanager;

  import org.openqa.selenium.By;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement;
  import ru.stqa.pft.addressbook.model.Groups;
  import ru.stqa.pft.addressbook.model.groupData;

  import java.util.List;

  public class groupHelper extends HelperBase{

    public groupHelper(WebDriver wd) {
      super(wd);
    }

    public void returnToGroupPage() {
      click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
      click(By.name("submit"));
    }

    public void fillGroupForm(groupData groupData) {
      type(By.name("group_name"), groupData.getName());
      type(By.name("group_header"), groupData.getHeader());
      type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
      click(By.name("new"));
    }

    public void deleteSelectedGroups() {
      click(By.name("delete"));
    }

    public void selectGroupById(int id) {
      wd.findElement(By.cssSelector("input[value='"+id +"']")).click();
    }

    public void initGroupModification() {
      click(By.name("edit"));
    }

    public void submitGroupModification() {
      click(By.name("update"));
    }

    public void create(groupData group) {
      initGroupCreation();
      fillGroupForm(group);
      submitGroupCreation();
      returnToGroupPage();
    }

    public void delete(groupData group) {
      selectGroupById(group.getId());
      deleteSelectedGroups();
      returnToGroupPage();
    }

    public void modify(groupData group) {
      selectGroupById(group.getId());
      initGroupModification();
      fillGroupForm(group);
      submitGroupModification();
      returnToGroupPage();
    }
    public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
      return wd.findElements(By.name("selected[]")).size();
    }

    public Groups all() {
      Groups groups = new Groups();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      for (WebElement element : elements){
        String name = element.getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        groups.add(new groupData().withId(id).withName(name));
      }
      return groups;
    }
  }
