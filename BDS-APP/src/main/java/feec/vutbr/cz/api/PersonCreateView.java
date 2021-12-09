package feec.vutbr.cz.api;


public class PersonCreateView {

    private int idRole;
    private int idSalary;
    private String birthdate;
    private String firstName;
    private String middleName;
    private String surname;
    private String pwd;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getIdSalary() {
        return idSalary;
    }

    public void setIdSalary(int idSalary) {
        this.idSalary = idSalary;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "PersonCreateView{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", EmployeeRole_idRole=" + String.valueOf(idRole)+
                ", Salary_idSalary="+ String.valueOf(idSalary)+
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}

