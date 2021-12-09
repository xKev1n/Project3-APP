package feec.vutbr.cz.services;


import at.favre.lib.crypto.bcrypt.BCrypt;
import feec.vutbr.cz.api.PersonBasicView;
import feec.vutbr.cz.api.PersonCreateView;
import feec.vutbr.cz.api.PersonDetailView;
import feec.vutbr.cz.api.PersonEditView;
import feec.vutbr.cz.datasource.PersonRepository;


import java.util.List;

/**
 * Class representing business logic on top of the Persons
 */
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDetailView getPersonDetailView(Integer id) {
        return personRepository.findPersonDetailedView(id);
    }

    public List<PersonBasicView> getPersonsBasicView() {
        return personRepository.getPersonsBasicView();
    }

    public void createPerson(PersonCreateView personCreateView) {
        // the following three lines can be written in one code line (only for more clear explanation it is written in three lines
        String originalPassword = personCreateView.getPwd();
        String hashedPassword = hashPassword(originalPassword);
        personCreateView.setPwd(hashedPassword);

        personRepository.createPerson(personCreateView);
    }

    public void editPerson(PersonEditView personEditView) {
        personRepository.editPerson(personEditView);
    }

    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

}
