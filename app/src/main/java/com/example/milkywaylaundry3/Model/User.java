package com.example.milkywaylaundry3.Model;

public class User {

    private String Name;
    private String Password;
    private String Phone;

//constructor none
    public User() {
    }

    //contructer

    public User(String name, String password /*String phone*/) {
        Name = name;
        Password = password;
  //      Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
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
