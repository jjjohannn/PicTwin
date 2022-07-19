package cl.ucn.disc.dsm.pictwin.frontend.model;

import java.util.List;

public class User {

    private int id;
    private String email;
    private String password;
    private int strikes;
    private String state;
    private List<Twin> twins;

    public List<Twin> getTwins() {
        return twins;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getStrikes() {
        return strikes;
    }

    public String isState() {
        return state;
    }
}
