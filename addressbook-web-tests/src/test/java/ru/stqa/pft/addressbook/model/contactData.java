  package ru.stqa.pft.addressbook.model;

  import java.util.Objects;

  public final class contactData {
    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String lastName;
    private String adress;
    private String email;
    private String mobile;

  //  private final String group;

    public int getId() {
      return id;
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

    public String getMobile() {
      return mobile;
    }

  /*  public String getGroup() {
      return group;
    } */

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

    public contactData withMobile(String mobile) {
      this.mobile = mobile;
      return this;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      contactData that = (contactData) o;
      return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(adress, that.adress) && Objects.equals(email, that.email) && Objects.equals(mobile, that.mobile);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, firstName, lastName, adress, email, mobile);
    }

    @Override
    public String toString() {
      return "contactData[" +
              "id=" + id +
              "firstName=" + firstName + '\'' +
              "lastName=" + lastName + '\'' +
              "adress=" + adress + '\'' +
              "email=" + email + '\'' +
              "mobile=" + mobile + '\'' +
        //      "group=" + group + + '\'' +
              '}';
    }

    }