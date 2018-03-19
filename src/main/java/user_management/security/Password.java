package user_management.security;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
    private final static int workload = 12;
    private String hash;

    public Password(String password) {
        this.hash = hashPassword(password);
    }

    public static String hashPassword(String password_plaintext) {
         return BCrypt.hashpw(password_plaintext, BCrypt.gensalt(workload));
    }

    public boolean matches(String password_plaintext) {
        return BCrypt.checkpw(password_plaintext,this.hash);
    }

    public String getHash() {
        return this.hash;
    }
}
