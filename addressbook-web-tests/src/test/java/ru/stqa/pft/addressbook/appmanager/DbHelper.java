package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.sql.*;
import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {  //Получаем данные из addressbook (groupData)
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<groupData> result = session.createQuery("from groupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() { //Получаем данные из addressbook (contactData)
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<contactData> result = session.createQuery("from contactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts (result);
  }

  public Contacts verifyContactInGroup() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select addressbook.id, firstname, lastname, address, home, mobile" +
              ", work, phone2, email, email2, email3 from addressbook " +
              "join address_in_groups ON addressbook.id=address_in_groups.id");
      Contacts contacts = new Contacts();
      while (rs.next()) {
        contacts.add(new contactData().withId(rs.getInt("id")).withFirstName(rs.getString("firstname")).withLastName(rs.getString("lastname"))
                .withAdress(rs.getString("address")).withHomePhone(rs.getString("home")).withMobilePhone(rs.getString("mobile"))
                .withWorkPhone(rs.getString("work")).withEmail(rs.getString("email")).withEmail2(rs.getString("email2"))
                .withEmail3(rs.getString("email3")));
      }
      rs.close();
      st.close();
      conn.close();

      //System.out.println(contacts);
      return new Contacts(contacts);

      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    } return new Contacts();
  }

  public Contacts verifyContactNotInGroup() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select addressbook.id, firstname, lastname, address, home, mobile" +
              ", work, phone2, email, email2, email3 from addressbook " +
              "join address_in_groups ON addressbook.id=address_in_groups.id where (select count(*) from addressbook) = (select count(*) from address_in_groups)");
      Contacts contacts = new Contacts();
      while (rs.next()) {
        contacts.add(new contactData().withId(rs.getInt("id")).withFirstName(rs.getString("firstname")).withLastName(rs.getString("lastname"))
                .withAdress(rs.getString("address")).withHomePhone(rs.getString("home")).withMobilePhone(rs.getString("mobile"))
                .withWorkPhone(rs.getString("work")).withEmail(rs.getString("email")).withEmail2(rs.getString("email2"))
                .withEmail3(rs.getString("email3")));
      }
      rs.close();
      st.close();
      conn.close();

      //System.out.println(contacts);
      return new Contacts(contacts);

      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    } return new Contacts();
  }

  public contactData contactById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<contactData> result = session.createQuery(String.format("from contactData where id = %s ", id)).list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public contactData contactWithoutGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<contactData> result = session.createQuery("from contactData where groups.size = 0 and deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public contactData contactWithGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<contactData> result = session.createQuery("from contactData where groups.size > 0 and deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }
}
