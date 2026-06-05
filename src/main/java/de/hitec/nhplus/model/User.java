package de.hitec.nhplus.model;

import de.hitec.nhplus.utils.DateConverter;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;

/**
* User Class to use for the Login process.
*/

public class User {
    private SimpleLongProperty uid;
    private SimpleStringProperty username;
    private SimpleStringProperty password_hash;
    private SimpleStringProperty salt;
    public enum Role{
        ADMIN,
        USER
    };
    private Role role;
    private SimpleStringProperty created_at;
    private SimpleStringProperty last_login;

    /**
     * Constructor to initiate an object of class <code>User</code> with the given parameter. Use this constructor
     * to initiate objects, which are not persisted yet, because it will not have a user id (uid).
     *
     * @param username Username used for Login.
     * @param password_hash Hashed password of the user.
     * @param salt Randomized salt value.
     * @param role The role of the user to restrict access.
     */
    public User(String username, String password_hash, String salt, Role role){
        this.uid = new SimpleLongProperty(0);
        this.username = new SimpleStringProperty(username);
        this.password_hash = new SimpleStringProperty(password_hash);
        this.salt = new SimpleStringProperty(salt);
        this.role = role;
        this.created_at = new SimpleStringProperty(DateConverter.convertLocalDateToString(LocalDate.now()));
    }

    /**
     * Constructor to initiate an object of class <code>User</code> with the given parameter. Use this constructor
     * to initiate objects, which are already persisted and have a user id (uid) and a last_login.
     *
     * @param username Username used for Login.
     * @param password_hash Hashed password of the user.
     * @param salt Randomized salt value.
     * @param role The role of the user to restrict access.
     * @param created_at Date the user was created.
     * @param last_login The last login date of a user.
     */
    public User(long uid, String username, String password_hash, String salt, Role role, LocalDate created_at, LocalDate last_login){
        this.uid = new SimpleLongProperty(uid);
        this.username = new SimpleStringProperty(username);
        this.password_hash = new SimpleStringProperty(password_hash);
        this.salt = new SimpleStringProperty(salt);
        this.role = role;
        this.created_at = new SimpleStringProperty(DateConverter.convertLocalDateToString(created_at));
        this.last_login = new SimpleStringProperty(DateConverter.convertLocalDateToString(last_login));
    }

    public long getUid(){return uid.get();}

    public String getUsername(){return this.username.get();}

    public void setUsername(String username){this.username.set(username);}

    public String getPasswordHash(){return this.password_hash.get();}

    public void setPasswordHash(String passwordHash){this.password_hash.set(passwordHash);}

    public String getSalt(){return this.salt.get();}

    public void setSalt(String salt){this.salt.set(salt);}

    public Role getRole(){return this.role;}

    public void setRole(Role role){this.role = role;}

    public String getCreatedAt(){return this.created_at.get();}

    public String getLastLogin(){return this.last_login.get();}

    public void setLastLogin(String lastLogin){this.last_login.set(lastLogin);}
}
