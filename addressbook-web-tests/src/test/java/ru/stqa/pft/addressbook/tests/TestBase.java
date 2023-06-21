  package ru.stqa.pft.addressbook.tests;

  import org.openqa.selenium.remote.BrowserType;
  import org.testng.annotations.AfterMethod;
  import org.testng.annotations.AfterSuite;
  import org.testng.annotations.BeforeMethod;
  import org.testng.annotations.BeforeSuite;
  import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
  import ru.stqa.pft.addressbook.model.Contacts;
  import ru.stqa.pft.addressbook.model.Groups;
  import ru.stqa.pft.addressbook.model.contactData;
  import ru.stqa.pft.addressbook.model.groupData;

  import java.io.IOException;
  import java.lang.reflect.Method;
  import java.util.stream.Collectors;

  import static org.hamcrest.CoreMatchers.equalTo;
  import static org.hamcrest.MatcherAssert.assertThat;

  public class TestBase {

    //  Logger logger = LoggerFactory.getLogger(TestBase.class);

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
      // logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
    }

    @AfterMethod
    public void logTestStop(Method m) {
      //  logger.info("Stop test" + m.getName());
    }

    public void verifyGroupListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
        Groups dbGroups = app.db().groups();
        Groups uiGroups = app.group().all();
        assertThat(uiGroups, equalTo(dbGroups.stream()
                .map((g) -> new groupData().withId(g.getId()).withName(g.getName()))
                .collect(Collectors.toSet())));
      }
    }

    public void verifyContactListInUI() { //Проверка списков из UI (вкл/выкл)
      if (Boolean.getBoolean("verifyUI")) { //Включение - в Edit Configurations "VM Options = -ea -DverifyUI=true"
        Contacts dbContacts = app.db().contacts(); //данные из базы (.db().)
        Contacts uiContacts = app.contact().all(); //Данные из UI
        assertThat(uiContacts, equalTo(dbContacts.stream() //Сравниваем только ID, имена и адрес
                .map((c) -> new contactData().withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName())
                        .withAdress(c.getAdress()))
                .collect(Collectors.toSet())));
      }
    }
  }
