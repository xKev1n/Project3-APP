package feec.vutbr.cz.controllers;


import feec.vutbr.cz.api.PersonCreateView;
import feec.vutbr.cz.datasource.PersonRepository;
import feec.vutbr.cz.services.PersonService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class PersonCreateController {

    private static final Logger logger = LoggerFactory.getLogger(PersonCreateController.class);

    @FXML
    public Button newPersonCreatePerson;

    @FXML
    private TextField newPersonUsername;

    @FXML
    private TextField newPersonFirstName;

    @FXML
    private TextField newPersonLastName;

    @FXML
    private TextField newPersonMiddleName;

    @FXML
    private TextField newPersonPwd;

    @FXML
    private TextField newPersonIdSalary;

    @FXML
    private TextField newPersonIdRole;

    @FXML
    private TextField newPersonBirthDate;

    private PersonService personService;
    private PersonRepository personRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize() {
        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);

        validation = new ValidationSupport();
        validation.registerValidator(newPersonUsername, Validator.createEmptyValidator("The username must not be empty."));
        validation.registerValidator(newPersonFirstName, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(newPersonLastName, Validator.createEmptyValidator("The last name must not be empty."));
        validation.registerValidator(newPersonPwd, Validator.createEmptyValidator("The password must not be empty."));
        validation.registerValidator(newPersonIdRole, Validator.createEmptyValidator("The id_role must not be empty."));
        validation.registerValidator(newPersonIdSalary, Validator.createEmptyValidator("The id_salary must not be empty."));
        validation.registerValidator(newPersonBirthDate, Validator.createEmptyValidator("The birthdate must not be empty."));

        newPersonCreatePerson.disableProperty().bind(validation.invalidProperty());

        logger.info("PersonCreateController initialized");
    }

    @FXML
    void handleCreateNewPerson(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        String firstName = newPersonFirstName.getText();
        String lastName = newPersonLastName.getText();
        String password = newPersonPwd.getText();
        String middlename = newPersonMiddleName.getText();
        String username = newPersonUsername.getText();
        int idSalary = Integer.parseInt(newPersonIdSalary.getText());
        int idRole = Integer.parseInt(newPersonIdRole.getText());
        String birthdate = newPersonBirthDate.getText();


        PersonCreateView personCreateView = new PersonCreateView();
        personCreateView.setPwd(password);
        personCreateView.setFirstName(firstName);
        personCreateView.setSurname(lastName);
        personCreateView.setMiddleName(middlename);
        personCreateView.setUsername(username);

        personCreateView.setBirthdate(birthdate);
        personCreateView.setIdRole(idRole);
        personCreateView.setIdSalary(idSalary);

        personService.createPerson(personCreateView);

        personCreatedConfirmationDialog();
    }

    private void personCreatedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Created Confirmation");
        alert.setHeaderText("Your person was successfully created.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
    }

}
