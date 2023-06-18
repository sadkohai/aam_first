  package ru.stqa.pft.addressbook.model;

  import com.google.gson.annotations.Expose;
  import com.thoughtworks.xstream.annotations.XStreamAlias;
  import com.thoughtworks.xstream.annotations.XStreamOmitField;
  import org.hibernate.annotations.Type;

  import javax.persistence.*;
  import java.io.File;
  import java.util.Objects;


  @XStreamAlias("contact") //Наименование XML
  @Entity //Для SQL определяет класс ContactData привязанным к базе
  @Table(name="addressbook") //Для SQL привязка к таблице
  public final class contactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname") //Для SQL
    private String firstName;
    @Expose
    @Column(name = "lastname") //Для SQL
    private String lastName;
    @Expose
    @Column(name = "address") //Для SQL
    @Type(type = "text") ////Для SQL устанавливаем тип колонки/поля для (varchar(225))
    private String adress;
    @Expose
    @Column(name = "email") //Для SQL
    @Type(type = "text") ////Для SQL устанавливаем тип колонки/поля для (varchar(225))
    private String email;
    @Expose
    @Column(name = "email2") //Для SQL
    @Type(type = "text") ////Для SQL устанавливаем тип колонки/поля для (varchar(225))
    private String email2;
    @Expose
    @Column(name = "email3") //Для SQL
    @Type(type = "text") ////Для SQL устанавливаем тип колонки/поля для (varchar(225))
    private String email3;
    @Expose
    @Transient //Для SQL аннотация исключает колонку
    private String allEmails;
    @Expose
    @Column(name = "home") //Для SQL
    @Type(type = "text") ////Для SQL устанавливаем тип колонки/поля для (varchar(225))
    private String homePhone;

    @Override
    public String toString() {
      return "contactData{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              '}';
    }

    @Expose
    @Column(name = "mobile") //Для SQL
    @Type(type = "text") ////Для SQL устанавливаем тип колонки/поля для (varchar(225))
    private String mobilePhone;
    @Expose
    @Column(name = "work") //Для SQL
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Transient //Для SQL аннотация исключает колонку
    private String allPhones;

    @Column(name = "photo") //Для SQL
    @Type(type = "text") ////Для SQL устанавливаем тип колонки/поля для (varchar(225))
    @XStreamOmitField
    transient private File photo;

    public int getId() {
      return id;
    }

    public File getPhoto() {
      return new File(photo.toURI());
    }

    public String getEmail2() {
      return email2;
    }



    public String getEmail3() {
      return email3;
    }


    public String getAllEmails() {
      return allEmails;
    }


    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public String getAdress() {
      return adress;
    }

    public String getEmail() {
      return email;
    }

    public String getMobilePhone() {
      return mobilePhone;
    }
    public String getHomePhone() {
      return homePhone;
    }
    public String getWorkPhone() {
      return workPhone;
    }

    public String getAllPhones() {
      return allPhones;
    }

    public contactData withId(int id) {
      this.id = id;
      return this;
    }
    public contactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public contactData withLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public contactData withAdress(String adress) {
      this.adress = adress;
      return this;
    }

    public contactData withEmail(String email) {
      this.email = email;
      return this;
    }
    public contactData withEmail2(String email2) {
      this.email2 = email2;
      return this;
    }
    public contactData withEmail3(String email3) {
      this.email3 = email3;
      return this;
    }

    public contactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
    }
    public contactData withMobilePhone(String mobile) {
      this.mobilePhone = mobile;
      return this;
    }

    public contactData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
    }

    public contactData withWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
    }

    public contactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
    }

    public contactData withPhoto(File photo) {
      this.photo = new File(photo.getPath());
      return this;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      contactData that = (contactData) o;
      return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(adress, that.adress) && Objects.equals(email, that.email) && Objects.equals(mobilePhone, that.mobilePhone);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, firstName, lastName, adress, email, mobilePhone);
    }

  }