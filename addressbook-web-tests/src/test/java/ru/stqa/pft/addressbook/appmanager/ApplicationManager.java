  package ru.stqa.pft.addressbook.appmanager;

  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.chrome.ChromeDriver;
  import org.openqa.selenium.edge.EdgeDriver;
  import org.openqa.selenium.firefox.FirefoxDriver;
  import org.openqa.selenium.remote.BrowserType;
  import org.openqa.selenium.remote.DesiredCapabilities;
  import org.openqa.selenium.remote.RemoteWebDriver;
  import ru.stqa.pft.addressbook.model.groupData;

  import java.io.File;
  import java.io.FileReader;
  import java.io.IOException;
  import java.net.URL;
  import java.util.Properties;


  public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private groupHelper groupHelper;
    private ContactHelper contactHelper;
    private final String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
      this.browser = browser;
      properties = new Properties();
    }

    public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

         dbHelper = new DbHelper();

      if ("".equals(properties.getProperty("selenium.server"))) {
        if (browser.equals(BrowserType.FIREFOX)) {
          wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
          wd = new ChromeDriver();
        } else if (browser.equals (BrowserType.EDGE)) {
          wd = new EdgeDriver();
        }
      } else {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
      }


  //    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
      groupHelper = new groupHelper(wd);
      contactHelper = new ContactHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.Login(properties.getProperty("web.baseUrl"), properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void createGroupIfNot() {
      GoTo().GroupPage();
      if (db().groups().size() == 0) { //данные из базы (.db().)
        group().create(new groupData().withName("test1").withHeader("test2").withFooter("test3"));
      }
    }

    public void stop() {
      wd.quit();
    }

    public groupHelper group() {
      return groupHelper;
    }

    public NavigationHelper GoTo() {
      return navigationHelper;
    }

    public ContactHelper contact() {
      return contactHelper;
    }

    public DbHelper db() {
      return dbHelper;
    }
  }
