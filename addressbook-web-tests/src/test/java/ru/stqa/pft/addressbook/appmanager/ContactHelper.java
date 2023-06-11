package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.ArrayList;
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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void assertDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//*[@title=\"Edit\"]")).get(index).click();
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

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
  public List<contactData> getContactList() {
    List<contactData> contacts = new ArrayList<contactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@name=\"entry\"]")); // 1
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("tbody > tr > td + td + td")).getText(); // 1
    //  System.out.println("firstname" + name);
      String lastname = element.findElement(By.cssSelector("tbody > tr > td + td")).getText(); // 1
    //  System.out.println("lastname" + lastname);
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactData contact = new contactData(id, name, lastname, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
