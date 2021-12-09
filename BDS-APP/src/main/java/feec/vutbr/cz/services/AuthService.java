package feec.vutbr.cz.services;


import at.favre.lib.crypto.bcrypt.BCrypt;
import feec.vutbr.cz.api.PersonAuthView;
import feec.vutbr.cz.datasource.PersonRepository;
import feec.vutbr.cz.exceptions.ResourceNotFoundException;


public class AuthService {

    private PersonRepository personRepository;

    public AuthService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private PersonAuthView findPersonByUsername(String username) {
        return personRepository.findPersonByEmail(username);
    }

    public boolean authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        PersonAuthView personAuthView = findPersonByUsername(username);
        if (personAuthView == null) {
            throw new ResourceNotFoundException("Provided username is not found.");
        }

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), personAuthView.getPassword());
        return result.verified;
    }


}

