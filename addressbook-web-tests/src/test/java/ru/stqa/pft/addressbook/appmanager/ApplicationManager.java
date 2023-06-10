  package ru.stqa.pft.addressbook.appmanager;

  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.chrome.ChromeDriver;
  import org.openqa.selenium.edge.EdgeDriver;
  import org.openqa.selenium.firefox.FirefoxDriver;
  import org.openqa.selenium.remote.Browser;

  import java.util.concurrent.TimeUnit;


  public class ApplicationManager {
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private groupHelper groupHelper;
    private ContactHelper contactHelper;
    private final Browser browser;

    public ApplicationManager(Browser browser) {
      this.browser = browser;
    }

    public void init() {
      if (browser.equals(Browser.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(Browser.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals (Browser.EDGE)) {
        wd = new EdgeDriver();
      }
      wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      groupHelper = new groupHelper(wd);
      contactHelper = new ContactHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.Login("admin", "secret");
    }

    public void stop() {
      wd.quit();
    }

    public groupHelper getGroupHelper() {
      return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
      return navigationHelper;
    }

    public ContactHelper getContactHelper() {
      return contactHelper;
    }
  }
