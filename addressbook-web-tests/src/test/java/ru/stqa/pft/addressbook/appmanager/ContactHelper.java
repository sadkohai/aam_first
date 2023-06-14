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
    type(By.name("mobile"), contactData.getMobile());

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
    click(By.linkText("home page"));
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

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@name=\"entry\"]")); // 1
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("tbody > tr > td + td + td")).getText(); // 1
      String lastname = element.findElement(By.cssSelector("tbody > tr > td + td")).getText(); // 1
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new contactData().withId(id).withFirstName(name).withLastName(lastname));
    }
    return contacts;
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
}
