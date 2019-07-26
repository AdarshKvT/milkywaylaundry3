package com.example.milkywaylaundry3.Model;

public class User {

    private String Name;
    private String Password;

//constructor none
    public User() {
    }

    //contructer
    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    //getter setter

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
