package user_management;

import user_management.security.Password;

public class User {
    private String email;
    private int id;
    private String name;
    private Password password;

    public User( int id, String name,String email, Password password){
        this.email=email;
        this.id=id;
        this.name=name;
        this.password=password;
    }
    public User( int id, String name,String email, String password){
        this.email=email;
        this.id=id;
        this.name=name;
        this.password= new Password(password);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = new Password(password);
    }


    @Override
    public String toString() {

        return this.name + " - " + this.email;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(!(obj instanceof User)) return false;

        User other = (User) obj;

        return this.email.equals(other.getEmail()) && this.id == other.getId()
                && this.name.equals(other.getName());

    }
}
