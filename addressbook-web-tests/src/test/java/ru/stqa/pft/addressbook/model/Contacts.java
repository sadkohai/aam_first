package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<contactData> {
  private Set<contactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<contactData>(contacts.delegate);
  }
  public Contacts() {
    this.delegate = new HashSet<contactData>();
  }

  public Contacts(Collection<contactData> contacts) {
    this.delegate = new HashSet<contactData>(contacts);
  }

  @Override
  protected Set<contactData> delegate() {
    return delegate;
  }

  public Contacts withAdded (contactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts without (contactData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
