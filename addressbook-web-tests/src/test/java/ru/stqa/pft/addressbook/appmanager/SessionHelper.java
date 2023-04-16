package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
  public SessionHelper(WebDriver wd) {
    super(wd);
  }
  public void Login(String username, String password) {
    type(By.name("user"), username);
    type(By.name("password"), username);
    type(By.name("user"), username);
    click(By.xpath("//input[@value='Login']"));
  }
}
