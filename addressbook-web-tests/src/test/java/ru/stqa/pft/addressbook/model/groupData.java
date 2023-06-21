package ru.stqa.pft.addressbook.model; //ready

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table (name = "group_list")
public final class groupData {
  //@XStreamOmitField
  @Id
  @Column (name = "group_id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column (name = "group_name")
  private String name;

  @Expose
  @Column (name = "group_header")
  @Type(type = "text")
  private String header;
  @Expose
  @Column (name = "group_footer")
  @Type(type = "text")
  private String footer;

  @ManyToMany(mappedBy = "groups")
  private Set<contactData> contacts = new HashSet<contactData>();

  public int getId() {
    return id;
  }

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public String getName() { return name; }

  public String getHeader() { return header; }

  public String getFooter() { return footer; }


  public groupData withId(int id) {
    this.id = id;
    return this;
  }

  public groupData withName(String name) {
    this.name = name;
    return this;
  }

  public groupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public groupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public groupData withContacts(Set<contactData> contacts) {
    this.contacts = contacts;
    return this;
  }

  @Override
  public String toString() {
    return "groupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    groupData groupData = (groupData) o;
    return id == groupData.id && Objects.equals(name, groupData.name) && Objects.equals(header, groupData.header) && Objects.equals(footer, groupData.footer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, header, footer);
  }
}