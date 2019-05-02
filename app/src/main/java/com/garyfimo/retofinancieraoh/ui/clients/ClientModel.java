package com.garyfimo.retofinancieraoh.ui.clients;

public class ClientModel {

    private String fullname;
    private String fullAgeDescription;

    public ClientModel() {
    }

    public ClientModel(String fullname, String fullAgeDescription) {
        this.fullname = fullname;
        this.fullAgeDescription = fullAgeDescription;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullAgeDescription() {
        return fullAgeDescription;
    }

    public void setFullAgeDescription(String fullAgeDescription) {
        this.fullAgeDescription = fullAgeDescription;
    }
}
