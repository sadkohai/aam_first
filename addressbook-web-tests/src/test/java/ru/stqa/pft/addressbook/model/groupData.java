package ru.stqa.pft.addressbook.model; //ready

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Objects;

@XStreamAlias("group")
public final class groupData {
  //@XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String name;

  @Expose
  private String header;

  @Expose
  private String footer;

  public int getId() {
    return id;
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
    return id == groupData.id && Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}