package easv.BE;

import javafx.scene.control.CheckBox;

public class User {
    private String username;
    private String email;
    private String userType;
    private int id;
    private CheckBox select;

    public User(int id, String username, String email, String userType) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.userType = userType;
        this.select = new CheckBox();
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public String getUserType() {
        return userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
