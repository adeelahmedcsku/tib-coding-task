package eu.tib.model;

import java.util.List;

// Do not modify the interfaces

public interface Publication {
    //setters
    public void setAuthors(List<Author> authors);
    public void setPublicationYear(int publicationYear);
    public void setPpn(long ppn);
    public void setPubLocation(String place);

    //getters
    public  long getPpn();
    public List<Author> getAuthor();
    public  int getPublicationYear();
    public String getPubLocation();

}

