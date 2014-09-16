package javaapplication7;
     
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlAnyElement;
 
public class Wrapper<T> {
 
    private ObservableList<T> items;
 
    public Wrapper() {
        items = FXCollections.<T>observableArrayList();
    }
 
    public Wrapper(ObservableList<T> items) {
        this.items = items;
    }
 
    @XmlAnyElement(lax=true)
    public ObservableList<T> getItems() {
        return items;
    }
 
}