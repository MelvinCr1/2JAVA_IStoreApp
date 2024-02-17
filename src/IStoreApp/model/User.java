// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence

package IStoreApp.model;

public class User {
    private String email;
    private String pseudo;
    private String password;
    private String role;

    public User(String email, String pseudo, String password, String role) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = role;
    }

    public String getEmail(){
        return email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String newRole){this.role = newRole;}

    public void setEmail(String newEmail){this.email = newEmail;}

    public void setPseudo(String newPseudo) {
        this.pseudo = newPseudo;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}