package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.contactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
    }
    generator.run();
  }

  private void run() throws IOException {
    List<contactData> contacts = generateContacts(count);
    if (format.equals("xml")){
      saveAsXml(contacts, new File(file)); //Сохраняем в XML формат
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file)); //Сохраняем в JSon формат (-f src/test/resources/contacts.json -c 3 -d json)
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<contactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<contactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(contactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private  List<contactData> generateContacts(int count) {
    List<contactData> contacts = new ArrayList<contactData>();
 //   File photo = new File("src/test/resources/stru.jpg");
    for (int i = 0; i < count; i++) {
      contacts.add(new contactData().withFirstName(String.format("name %s", i))
              .withLastName(String.format("lastname %s", i)).withAdress(String.format("address %s", i))
              .withEmail(String.format("email %s", i)).withEmail2(String.format("email2 %s", i)).withEmail3(String.format("email3 %s", i))
              .withHomePhone(String.format("111%s", "")).withMobilePhone(String.format("+7 (222)%s", ""))
              .withWorkPhone(String.format("+7 (333)%s", "")));
    }
    return contacts;
  }
}
