  package ru.stqa.pft.addressbook.appmanager;

  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.firefox.FirefoxDriver;

  import java.util.concurrent.TimeUnit;

  public class ApplicationManager {
    WebDriver wd;

    private SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private  groupHelper groupHelper;

    public void init() {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/group.php");
      groupHelper = new groupHelper(wd);
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
  }
