package org.example;

public class Admin {
    private String name;
    private String password;

    public Admin(String name, String password) {
        name = this.name;
        password = this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getPassWord() {
        return password;
    }

    public void showReports() {

    }
}
