  package ru.stqa.pft.addressbook.tests;

  import org.junit.platform.commons.logging.Logger;
  import org.junit.platform.commons.logging.LoggerFactory;
  import org.openqa.selenium.remote.BrowserType;
  import org.testng.annotations.AfterMethod;
  import org.testng.annotations.AfterSuite;
  import org.testng.annotations.BeforeMethod;
  import org.testng.annotations.BeforeSuite;
  import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

  import java.io.IOException;
  import java.lang.reflect.Array;
  import java.lang.reflect.Method;
  import java.util.Arrays;

  public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); //-ea -Dbrowser=chrome

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
      app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
      app.stop();
    }
    public ApplicationManager getApp() {
      return app;
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
      logger.info("Start test" + m.getName() + "with parameters " + Arrays.asList(p));
    }

    @AfterMethod
    public void logTestStop(Method m) {
      logger.info("Stop test" + m.getName());
    }

  }
