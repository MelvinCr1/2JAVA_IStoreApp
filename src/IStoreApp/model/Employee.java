package IStoreApp.model;

public class Employee {
    private int id;
    private String email;
    private String pseudo;
    private String password;
    private String role;

    public Employee(int id, String email, String pseudo, String password, String role){
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getPseudo(){
        return pseudo;
    }

    public void setPseudo(String newPseudo){
        this.pseudo = newPseudo;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String newRole){
        this.role = newRole;
    }
}
