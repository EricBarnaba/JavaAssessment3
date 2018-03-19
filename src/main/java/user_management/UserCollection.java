package user_management;

import user_management.security.UserAuthenticationFailedException;
import user_management.validation.EmailNotAvailableException;
import user_management.validation.InvalidEmailException;
import user_management.validation.PasswordTooSimpleException;

import java.util.ArrayList;

public class UserCollection extends ArrayList<User> {

    public User findById(int id) {
        for(User u : this){
            if(u.getId()==id) return u;
        }
        return null;
    }

    public User findByEmail(String email) {
        for(User u : this){
            if(u.getEmail().equals(email)) return u;
        }
        return null;
    }

    public User attemptLogin(String email, String password) throws UserAuthenticationFailedException {
        return null;
    }

    public int createUser(String name, String email, String password) throws PasswordTooSimpleException,EmailNotAvailableException, InvalidEmailException {
        return 0;
    }
}
