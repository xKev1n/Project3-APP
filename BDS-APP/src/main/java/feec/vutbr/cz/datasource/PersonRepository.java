package feec.vutbr.cz.datasource;


import feec.vutbr.cz.api.*;
import feec.vutbr.cz.config.DataSourceConfig;
import feec.vutbr.cz.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    public PersonAuthView findPersonByEmail(String username) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT username, pwd" +
                             " FROM lib_db.employee e" +
                             " WHERE e.username = ?")
        ) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonAuth(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }

    public PersonDetailView findPersonDetailedView(Integer employeeId) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT idemployee, username, pwd, title, firstname, middlename, surname, sex, birthdate, city, h_number, street, zip_code, country, salary, role" +
                             " FROM lib_db.employee e" +
                             " JOIN lib_db.employee_has_address eha ON e.idemployee = eha.employee_idemployee" +
                             " JOIN lib_db.address a ON a.idaddress = eha.address_idaddress"+
                             " JOIN lib_db.salary s on s.idsalary = e.salary_idsalary"+
                             " JOIN lib_db.employeerole er on er.idrole = e.employeerole_idrole"+
                             " WHERE e.idemployee = ?")
        ) {
            preparedStatement.setInt(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find person by ID with addresses failed.", e);
        }
        return null;
    }


    public List<PersonBasicView> getPersonsBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT idemployee, username, title, firstname, middlename, surname, city" +
                             " FROM lib_db.employee e" +
                             " JOIN lib_db.employee_has_address eha ON e.idemployee = eha.employee_idemployee" +
                             " JOIN lib_db.address a ON a.idaddress = eha.address_idaddress");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<PersonBasicView> personBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                personBasicViews.add(mapToPersonBasicView(resultSet));
            }
            return personBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Persons basic view could not be loaded.", e);
        }
    }

    public void createPerson(PersonCreateView personCreateView) {
        String insertPersonSQL = "INSERT INTO lib_db.employee (firstname, middlename, surname, username, pwd, birthdate, Salary_idSalary, EmployeeRole_idRole) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            /*if (personCreateView.getMiddleName() == ""){
                personCreateView.setMiddleName(null);
            }*/
            preparedStatement.setString(1, personCreateView.getFirstName());
            preparedStatement.setString(2, personCreateView.getMiddleName());
            preparedStatement.setString(3, personCreateView.getSurname());
            preparedStatement.setString(4, personCreateView.getUsername());
            preparedStatement.setString(5, personCreateView.getPwd());

            preparedStatement.setDate(6, java.sql.Date.valueOf(personCreateView.getBirthdate()));
            preparedStatement.setInt(7, personCreateView.getIdSalary());
            preparedStatement.setInt(8, personCreateView.getIdRole());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataAccessException("Creating person failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }

    public void editPerson(PersonEditView personEditView) {
        String insertPersonSQL = "UPDATE lib_db.employee e SET username = ?, firstname = ?, middlename = ?, surname = ? WHERE e.idemployee = ?";
        String checkIfExists = "SELECT username FROM lib_db.employee e WHERE e.idemployee = ?";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setString(1, personEditView.getUsername());
            preparedStatement.setString(2, personEditView.getFirstName());
            preparedStatement.setString(3, personEditView.getMiddleName());
            preparedStatement.setString(4, personEditView.getSurname());
            preparedStatement.setLong(5, personEditView.getId());

            try {
                connection.setAutoCommit(false);
                try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, personEditView.getId());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("This person for edit does not exist.");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating person failed, no rows affected.");
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }


    private PersonAuthView mapToPersonAuth(ResultSet rs) throws SQLException {
        PersonAuthView person = new PersonAuthView();
        person.setUsername(rs.getString("username"));
        person.setPassword(rs.getString("pwd"));
        return person;
    }

    private PersonBasicView mapToPersonBasicView(ResultSet rs) throws SQLException {
        PersonBasicView personBasicView = new PersonBasicView();
        personBasicView.setId(rs.getInt("idemployee"));
        personBasicView.setUsername(rs.getString("username"));
        personBasicView.setGivenName(rs.getString("firstname"));
        personBasicView.setFamilyName(rs.getString("surname"));
        personBasicView.setMiddleName(rs.getString("middlename"));
        personBasicView.setCity(rs.getString("city"));
        return personBasicView;
    }

    private PersonDetailView mapToPersonDetailView(ResultSet rs) throws SQLException {
        PersonDetailView personDetailView = new PersonDetailView();
        personDetailView.setId(rs.getInt("idemployee"));
        personDetailView.setUsername(rs.getString("username"));
        personDetailView.setTitle(rs.getString("title"));
        personDetailView.setGivenName(rs.getString("firstname"));
        personDetailView.setMiddleName(rs.getString("middlename"));
        personDetailView.setFamilyName(rs.getString("surname"));
        personDetailView.setBirthdate(rs.getString("birthdate"));
        personDetailView.setRole(rs.getString("role"));
        personDetailView.setSex(rs.getString("sex"));
        personDetailView.setCity(rs.getString("city"));
        personDetailView.sethouseNumber(rs.getString("h_number"));
        personDetailView.setZipCode(rs.getString("zip_code"));
        personDetailView.setStreet(rs.getString("street"));
        personDetailView.setCountry(rs.getString("country"));
        personDetailView.setSalary(rs.getDouble("salary"));
        return personDetailView;
    }

}

