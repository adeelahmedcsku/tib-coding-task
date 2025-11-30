package eu.tib.model;

// Do not modify the class

public class Author {

    String lastName;
    String FirstName;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String  getFullName(){
        return  getLastName() + ", " + getFirstName();
    }

}
