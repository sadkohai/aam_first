package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillContactForm(contactData contactData/*,  boolean creation */) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAdress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    attach(By.name("photo"), contactData.getPhoto());

//   if (creation) {
//     new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//   } else {
//     Assert.assertFalse(isElementPresent(By.name("new_group")));
//   }
  }

  public void gotoAddContact() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id +"']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void assertDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void editContactById(int id) {
    wd.findElement(By.cssSelector("img[title=\"Edit\"]")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }
  public void createContact(contactData contact)  {
   gotoAddContact();
   fillContactForm(contact /*, true*/);
   submitContactCreation();
   returnToContactPage();
  }
  public boolean chooseGroup() {
    click(By.xpath("/html/body/div/div[4]/form/select[5]"));
    if (isElementPresent(By.xpath("/html/body/div/div[4]/form/select[5]/option[2]"))) {
      click(By.xpath("/html/body/div/div[4]/form/select[5]/option[2]"));
      return true;
    } else {
      return false;
    }
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void delete(contactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    assertDeleteContact();
  }

  public void modify(contactData contact) {
    editContactById(0);
    fillContactForm(contact);
    submitContactModification();
  }

  public contactData infoFromEditForm(contactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new contactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById (int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

//public Contacts all() {
//  Contacts contacts = new Contacts();
//  List<WebElement> elements = wd.findElements(By.xpath("//*[@name=\"entry\"]")); // 1
//  for (WebElement element : elements) {
//    String name = element.findElement(By.cssSelector("tbody > tr > td + td + td")).getText(); // 1
//    String lastname =  element.findElement(By.cssSelector("tbody > tr > td + td")).getText(); // 1
//    String allPhones = cells.get(5).getText();
//    int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//    contacts.add(new contactData().withId(id).withFirstName(name).withLastName(lastname));
//  }
//  return contacts;
//}

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(1).getText();
      String lastname =  cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      contacts.add(new contactData().withId(id).withFirstName(name).withLastName(lastname).withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return contacts;
  }
}
