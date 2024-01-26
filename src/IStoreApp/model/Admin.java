package IStoreApp.model;

public class Admin {
    private String email;
    private String pseudo;
    private String password;

    public Admin(String email, String pseudo, String password) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String newPseudo) {
        this.pseudo = newPseudo;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
