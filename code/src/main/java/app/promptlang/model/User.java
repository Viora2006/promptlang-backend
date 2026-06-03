package app.promptlang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // user is a key word in postgres, cant use
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String passwordHash;

    public User() {}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
    this.username = username;
}

public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
}

public String getPasswordHash() {
    return passwordHash;
}
}