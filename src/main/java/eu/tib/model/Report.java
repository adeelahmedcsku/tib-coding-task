package eu.tib.model;

import java.util.List;

// Do not modify the class

public class Report implements Publication {

    private List<Author> authors;
    private int publicationYear;
    private long ppn;
    private String pubLocation;

    //setters
    public void setAuthors(List<Author> authors) { this.authors = authors; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    public void setPpn(long ppn) { this.ppn = ppn; }
    public void setPubLocation(String pubLocation){ this.pubLocation=pubLocation; }

    //getters
    public long getPpn(){ return ppn; }
    public List<Author> getAuthor(){
        return  this.authors;
    }
    public  int getPublicationYear(){
        return  this.publicationYear;
    }
    public String getPubLocation() { return this.pubLocation; }
}
