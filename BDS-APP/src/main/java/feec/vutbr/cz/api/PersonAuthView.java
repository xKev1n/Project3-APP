package feec.vutbr.cz.api;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PersonAuthView {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
        // return only password, because there will be stored hashes; TODO: add all employees through the app.
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonAuthView{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}