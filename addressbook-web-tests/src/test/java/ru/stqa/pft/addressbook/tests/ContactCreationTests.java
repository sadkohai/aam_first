 package ru.stqa.pft.addressbook.tests;

 import java.util.concurrent.TimeUnit;
 import org.testng.annotations.*;
 import org.openqa.selenium.*;
 import org.openqa.selenium.firefox.FirefoxDriver;
 import ru.stqa.pft.addressbook.model.contactData;

 public class ContactCreationTests {
   private WebDriver wd;

   @BeforeClass(alwaysRun = true)
   public void setUp() throws Exception {
     wd = new FirefoxDriver();
     wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     wd.get("http://localhost/addressbook/group.php");
     Login("admin", "secret");
   }

   private void Login(String username, String password) {
     wd.findElement(By.name("user")).click();
     wd.findElement(By.name("user")).clear();
     wd.findElement(By.name("user")).sendKeys(username);
     wd.findElement(By.name("pass")).click();
     wd.findElement(By.name("pass")).clear();
     wd.findElement(By.name("pass")).sendKeys(password);
     wd.findElement(By.xpath("//input[@value='Login']")).click();
   }

   @Test
   public void testContactCreationTests() throws Exception {
     gotoAddContact();
     fillContactForm(new contactData("Mihalcov", "Vladimir", "Poland, 10", "testmailcontact@mail.ru", "89999757811"));
     submitContactCreation();
     returnToPage();
   }

   private void returnToPage() {
     wd.findElement(By.linkText("home")).click();
   }

   private void submitContactCreation() {
     wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
   }

   private void fillContactForm(contactData contactData) {
     wd.findElement(By.name("firstname")).click();
     wd.findElement(By.name("firstname")).clear();
     wd.findElement(By.name("firstname")).sendKeys(contactData.firstName());
     wd.findElement(By.name("lastname")).click();
     wd.findElement(By.name("lastname")).clear();
     wd.findElement(By.name("lastname")).sendKeys(contactData.lastName());
     wd.findElement(By.name("address")).click();
     wd.findElement(By.name("address")).clear();
     wd.findElement(By.name("address")).sendKeys(contactData.adress());
     wd.findElement(By.name("email")).click();
     wd.findElement(By.name("email")).clear();
     wd.findElement(By.name("email")).sendKeys(contactData.email());
     wd.findElement(By.name("mobile")).click();
     wd.findElement(By.name("mobile")).clear();
     wd.findElement(By.name("mobile")).sendKeys(contactData.mobile());
   }

   private void gotoAddContact() {
     wd.findElement(By.linkText("add new")).click();
   }

   @AfterClass(alwaysRun = true)
   public void tearDown() throws Exception {
     wd.quit();
   }


 }
