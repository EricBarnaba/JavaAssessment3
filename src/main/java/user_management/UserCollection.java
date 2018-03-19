package user_management;

import java.util.ArrayList;

public class UserCollection extends ArrayList<User> {

    public User findById(int id) {
        return null;
    }

    public User findByEmail(String email) {
        return null;
    }

    public User attemptLogin(String email, String password) {
        return null;
    }

    public int createUser(String name, String email, String password) {
        return 0;
    }
}
