  package ru.stqa.pft.mantis.tests;

  import biz.futureware.mantis.rpc.soap.client.IssueData;
  import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
  import com.google.protobuf.ServiceException;
  import org.openqa.selenium.remote.BrowserType;
  import org.testng.SkipException;
  import org.testng.annotations.AfterSuite;
  import org.testng.annotations.BeforeSuite;
  import ru.stqa.pft.mantis.appmanager.ApplicationManager;
  import ru.stqa.pft.mantis.model.Issue;

  import java.io.File;
  import java.io.IOException;
  import java.math.BigInteger;
  import java.net.MalformedURLException;
  import java.rmi.RemoteException;

  public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); //-ea -Dbrowser=chrome

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
      app.init();
      app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
      app.ftp().restore("config_inc.php.bak", "config_inc.php");
      app.stop();
    }

    boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
      String status = app.soap().getIssueResolutionName(BigInteger.valueOf(issueId));
      return status.equals("open");
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException, javax.xml.rpc.ServiceException {
      if (isIssueOpen(issueId)) {
        throw new SkipException("Ignored because of issue " + issueId);
      }
    }

    public String issueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
      MantisConnectPortType mc = app.soap().getMantisConnect();
      IssueData issueData = mc.mc_issue_get
              (app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
      Issue issue = new Issue().withId(issueData.getId().intValue()).withStatus(issueData.getStatus().getName());
      return issue.getStatus();
    }
  }