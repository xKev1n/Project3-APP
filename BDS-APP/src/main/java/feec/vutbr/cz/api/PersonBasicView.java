package feec.vutbr.cz.api;

import javafx.beans.property.*;

public class PersonBasicView {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty givenName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();
    private StringProperty familyName = new SimpleStringProperty();
    private StringProperty sex = new SimpleStringProperty();


    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public Integer getId() {
        return idProperty().get();
    }

    public void setId(Integer id) {
        this.idProperty().setValue(id);
    }

    public String getCity() {
        return cityProperty().get();
    }

    public void setCity(String city) {
        this.cityProperty().setValue(city);
    }

    public String getUsername() {
        return usernameProperty().get();
    }

    public void setUsername(String username) {
        this.usernameProperty().setValue(username);
    }

    public String getGivenName() {
        return givenNameProperty().get();
    }

    public void setGivenName(String givenName) {
        this.givenNameProperty().setValue(givenName);
    }

    public String getFamilyName() {
        return familyNameProperty().get();
    }

    public void setFamilyName(String familyName) {
        this.familyNameProperty().setValue(familyName);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty givenNameProperty() {
        return givenName;
    }

    public StringProperty familyNameProperty() {
        return familyName;
    }

    public String getSex() {
        return sex.get();
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }
}