package feec.vutbr.cz.controllers;


import feec.vutbr.cz.api.PersonDetailView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonsDetailViewController {

    private static final Logger logger = LoggerFactory.getLogger(PersonsDetailViewController.class);

    @FXML
    private TextField idTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField roleTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField birthdateTextField;

    @FXML
    private TextField sexTextField;

    @FXML
    private TextField houseNumberTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField middlenameTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField zipCodeTextField;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        idTextField.setEditable(false);
        roleTextField.setEditable(false);
        birthdateTextField.setEditable(false);
        middlenameTextField.setEditable(false);
        usernameTextField.setEditable(false);
        sexTextField.setEditable(false);
        countryTextField.setEditable(false);
        salaryTextField.setEditable(false);
        firstNameTextField.setEditable(false);
        lastNameTextField.setEditable(false);
        titleTextField.setEditable(false);
        cityTextField.setEditable(false);
        houseNumberTextField.setEditable(false);
        streetTextField.setEditable(false);
        zipCodeTextField.setEditable(false);

        loadPersonsData();

        logger.info("PersonsDetailViewController initialized");
    }

    private void loadPersonsData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof PersonDetailView) {
            PersonDetailView personBasicView = (PersonDetailView) stage.getUserData();
            idTextField.setText(String.valueOf(personBasicView.getId()));
            usernameTextField.setText(personBasicView.getUsername());
            firstNameTextField.setText(personBasicView.getGivenName());
            lastNameTextField.setText(personBasicView.getFamilyName());
            titleTextField.setText(personBasicView.getTitle());
            salaryTextField.setText(String.valueOf(personBasicView.getSalary()));
            roleTextField.setText(personBasicView.getRole());
            sexTextField.setText(personBasicView.getSex());
            countryTextField.setText(personBasicView.getCountry());
            birthdateTextField.setText(personBasicView.getBirthdate());
            middlenameTextField.setText(personBasicView.getMiddleName());
            cityTextField.setText(personBasicView.getCity());
            houseNumberTextField.setText(personBasicView.gethouseNumber());
            streetTextField.setText(personBasicView.getStreet());
            zipCodeTextField.setText(personBasicView.getZipCode());
        }
    }

}

