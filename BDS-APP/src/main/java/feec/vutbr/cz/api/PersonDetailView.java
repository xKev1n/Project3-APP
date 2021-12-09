package feec.vutbr.cz.api;

import javafx.beans.property.*;

public class PersonDetailView {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty givenName = new SimpleStringProperty();
    private StringProperty middleName = new SimpleStringProperty();
    private StringProperty familyName = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty houseNumber = new SimpleStringProperty();
    private StringProperty country = new SimpleStringProperty();
    private StringProperty zipCode = new SimpleStringProperty();
    private StringProperty birthdate = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty sex = new SimpleStringProperty();
    private DoubleProperty salary = new SimpleDoubleProperty();
    private StringProperty role = new SimpleStringProperty();

    public String getSex() {
        return sex.get();
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(String birthdate) {
        this.birthdate.set(birthdate);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public Integer getId() {
        return idProperty().get();
    }

    public void setId(Integer id) {
        this.idProperty().setValue(id);
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

    public String getCity() {
        return cityProperty().get();
    }

    public void setCity(String city) {
        this.cityProperty().setValue(city);
    }

    public String gethouseNumber() {
        return houseNumberProperty().get();
    }

    public void sethouseNumber(String houseNumber) {
        this.houseNumberProperty().setValue(houseNumber);
    }

    public String getStreet() {
        return streetProperty().get();
    }

    public void setStreet(String street) {
        this.streetProperty().setValue(street);
    }

    public IntegerProperty idProperty() {
        return id;
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

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty houseNumberProperty() {
        return houseNumber;
    }

    public StringProperty streetProperty() {
        return street;
    }


}
