package javaapplication7;
     


import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.h2.command.CommandInterface;
 
public class Demo {
 
    public static void main(String[] args) throws Exception {
  //      JAXBContext jc = JAXBContext.newInstance(Wrapper.class, Site.class, Address.class);
 
        // Unmarshal
//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        ObservableList<Address> addresses = unmarshal(unmarshaller, Address.class, "addresses.xml");
        ObservableList<Site> persons = FXCollections.<Site>observableArrayList();
    //    ObservableList<Site> persons = unmarshal(unmarshaller, Site.class, "persons.xml");
        ObservableList<Page> pages = FXCollections.<Page>observableArrayList();
        pages.add(new Page("page1"));
        pages.add(new Page("page2"));
        Site l = new Site("site1", false);
        l.setPages(pages);
        
        Site l2 = new Site("site2", true);
        ObservableList<Page> pages2 = FXCollections.<Page>observableArrayList();
        pages2.add(new Page("lol1"));
        pages2.add(new Page("lol3"));
        l2.setPages(pages2);
        persons.addAll(l,l2);
        savePersonDataToFile(new File("test.xml"), persons);
        ObservableList<Site> sites = FXCollections.<Site>observableArrayList();
        loadPersonDataFromFile(new File("test.xml"), sites);
        // Marshal
//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshal(marshaller, addresses, "addresses1");
//        marshal(marshaller, persons, "PERSONS1");
     }
 
    
public static void loadPersonDataFromFile(File file, ObservableList<Site> personData) {
  XStream xstream = new XStream();
  xstream.alias("person", Site.class);

  try {
    String xml = FileUtil.readFile(file);

    ArrayList<Site> personList = (ArrayList<Site>) xstream.fromXML(xml);

    personData.clear();
    personData.addAll(personList);

    personData.forEach(file1-> {
        System.out.println(file1.getName()+" "+file1.getChange()+" ");
        file1.getPages().forEach(fil->System.out.println(fil.getName()));
    });
    
  } catch (Exception e) { // catches ANY exception

  }
}
    
  

public static void savePersonDataToFile(File file, ObservableList<Site> personData) {
  XStream xstream = new XStream();
  xstream.alias("person", Site.class);

  // Convert ObservableList to a normal ArrayList
  ArrayList<Site> personList = new ArrayList<>(personData);

  String xml = xstream.toXML(personList);
  try {
    FileUtil.saveFile(xml, file);


  } catch (Exception e) { // catches ANY exception

  }
}
    
    
    
    
    
    
    
    
    /**
//     * Unmarshal XML to Wrapper and return List value.
//     */
//    private static <T> ObservableList<T> unmarshal(Unmarshaller unmarshaller,
//            Class<T> clazz, String xmlLocation) throws JAXBException {
//        StreamSource xml = new StreamSource(xmlLocation);
//        Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(xml,
//                Wrapper.class).getValue();
//        return wrapper.getItems();
//    }
// 
//    /**
//     * Wrap List in Wrapper, then leverage JAXBElement to supply root element 
//     * information.
//     */
//    private static void marshal(Marshaller marshaller, ObservableList<?> list, String name)
//            throws JAXBException {
//        QName qName = new QName(name);
//        Wrapper wrapper = new Wrapper(list);
//        JAXBElement<Wrapper> jaxbElement = new JAXBElement<Wrapper>(qName,
//                Wrapper.class, wrapper);
//        marshaller.marshal(jaxbElement, System.out);
//    }
 
}