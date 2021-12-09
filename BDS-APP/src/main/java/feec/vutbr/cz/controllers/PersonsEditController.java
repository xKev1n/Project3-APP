package feec.vutbr.cz.controllers;


import feec.vutbr.cz.api.PersonBasicView;
import feec.vutbr.cz.api.PersonEditView;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class PersonsEditController {

    private static final Logger logger = LoggerFactory.getLogger(PersonsEditController.class);

    @FXML
    public Button editPersonButton;
    @FXML
    public TextField idTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField middleNameTextField;


    private PersonService personService;
    private PersonRepository personRepository;
    private ValidationSupport validation;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);

        validation = new ValidationSupport();
        validation.registerValidator(idTextField, Validator.createEmptyValidator("The id must not be empty."));
        idTextField.setEditable(false);
        validation.registerValidator(usernameTextField, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(firstNameTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(lastNameTextField, Validator.createEmptyValidator("The last name must not be empty."));

        editPersonButton.disableProperty().bind(validation.invalidProperty());

        loadPersonsData();

        logger.info("PersonsEditController initialized");
    }


    private void loadPersonsData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof PersonBasicView) {
            PersonBasicView personBasicView = (PersonBasicView) stage.getUserData();
            idTextField.setText(String.valueOf(personBasicView.getId()));
            usernameTextField.setText(personBasicView.getUsername());
            firstNameTextField.setText(personBasicView.getGivenName());
            lastNameTextField.setText(personBasicView.getFamilyName());
            middleNameTextField.setText(personBasicView.getMiddleName());
        }
    }

    @FXML
    public void handleEditPersonButton(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        Long id = Long.valueOf(idTextField.getText());
        String username = usernameTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String middleName = middleNameTextField.getText();

        PersonEditView personEditView = new PersonEditView();
        personEditView.setId(id);
        personEditView.setMiddleName(middleName);
        personEditView.setEmail(username);
        personEditView.setFirstName(firstName);
        personEditView.setSurname(lastName);

        personService.editPerson(personEditView);

        personEditedConfirmationDialog();
    }

    private void personEditedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Edited Confirmation");
        alert.setHeaderText("Your person was successfully edited.");

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
