package user_management;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import user_management.security.Authenticator;
import user_management.security.UserAuthenticationFailedException;
import user_management.validation.EmailNotAvailableException;
import user_management.validation.InvalidEmailException;
import user_management.validation.PasswordTooSimpleException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        User user = this.findByEmail(email);
        if(user == null) throw new UserAuthenticationFailedException();
        else if(!Authenticator.authenticate(user,password)) throw new UserAuthenticationFailedException();
        else return user;
    }

    public int createUser(String name, String userEmail, String password) throws PasswordTooSimpleException,EmailNotAvailableException, InvalidEmailException {
        int newId;
        int lastIdSeen=0;
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        ArrayList<String> userList = new ArrayList<>();
        for(User u : this){
            userList.add(gson.toJson(u));
        }
        for(String s : userList) {
            JsonObject user = parser.parse(s).getAsJsonObject();
            String actualEmail = user.get("email").getAsString();
            lastIdSeen = user.get("id").getAsInt();
            if (actualEmail.equals(userEmail)) throw new EmailNotAvailableException();
        }
        newId = lastIdSeen+1;
        Pattern email = Pattern.compile("([\\w]+@)([\\w]+[.])(com|org|co|net)");
        Matcher emailMatcher = email.matcher(userEmail);
        if(!emailMatcher.find()) throw new InvalidEmailException();
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern number = Pattern.compile("[\\d]");
        Pattern specialCharacters = Pattern.compile("[!@#$%^&*()<>?]");
        Pattern length = Pattern.compile(".{8,20}");
        Pattern[] patterns = {lowercase,uppercase,number,specialCharacters,length};
        for(Pattern p : patterns){
            Matcher m = p.matcher(password);
            if(!m.find()) throw new PasswordTooSimpleException();
        }


        this.add(new User(newId,name,userEmail,password));
        return newId;
    }

}
