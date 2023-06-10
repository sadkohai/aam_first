  package ru.stqa.pft.addressbook.model;

  import java.util.Objects;

  public final class contactData {
    private final String firstName;
    private final String lastName;
    private final String adress;
    private final String email;
    private final String mobile;
    private final String group;

    public contactData(String firstName, String lastName, String adress, String email, String mobile, String group) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.adress = adress;
      this.email = email;
      this.mobile = mobile;
      this.group = group;
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

    public String getGroup() {
      return group;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) return true;
      if (obj == null || obj.getClass() != this.getClass()) return false;
      var that = (contactData) obj;
      return Objects.equals(this.firstName, that.firstName) &&
              Objects.equals(this.lastName, that.lastName) &&
              Objects.equals(this.adress, that.adress) &&
              Objects.equals(this.email, that.email) &&
              Objects.equals(this.mobile, that.mobile) &&
              Objects.equals(this.group, that.group);
    }

    @Override
    public int hashCode() {
      return Objects.hash(firstName, lastName, adress, email, mobile, group);
    }

    @Override
    public String toString() {
      return "contactData[" +
              "firstName=" + firstName + ", " +
              "lastName=" + lastName + ", " +
              "adress=" + adress + ", " +
              "email=" + email + ", " +
              "mobile=" + mobile + ", " +
              "group=" + group + ']';
    }

    }