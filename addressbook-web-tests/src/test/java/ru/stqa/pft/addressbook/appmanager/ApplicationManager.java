  package ru.stqa.pft.addressbook.appmanager;

  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.firefox.FirefoxDriver;
  import org.openqa.selenium.chrome.ChromeDriver;
  import org.openqa.selenium.edge.EdgeDriver;
  import java.util.concurrent.TimeUnit;

  import org.openqa.selenium.opera.OperaDriver;
  import org.openqa.selenium.remote.BrowserType;
  import org.openqa.selenium.remote.Browser;


  public class ApplicationManager {
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private groupHelper groupHelper;
    private ContactHelper contactHelper;
    private final String browser;

    public ApplicationManager(String browser) {
      this.browser = browser;
    }

    public void init() {
      if (browser == BrowserType.FIREFOX) {
        wd = new FirefoxDriver();
      } else if (browser == BrowserType.CHROME) {
        wd = new ChromeDriver();
      } else if (browser == BrowserType.EDGE) {
        wd = new EdgeDriver();
      }
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
