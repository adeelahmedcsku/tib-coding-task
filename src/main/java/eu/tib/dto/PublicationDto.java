package eu.tib.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicationDto {

    // Adeel:  The ppnNo is list in the jsonfile and also the datatype is different and we will miss the data.
    private List<String> ppnNo;

    private List<AuthorDto> authorList;

    // this is used because i think that the field name is not according to the standard of json in the file.
    @JsonProperty("publication date")
    private String publicationDate;

    private String place;

    public PublicationDto() {}

    public List<String> getPpnNo() {
        return ppnNo;
    }

    public void setPpnNo(List<String> ppnNo) {
        this.ppnNo = ppnNo;
    }

    public List<AuthorDto> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorDto> authorList) {
        this.authorList = authorList;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
