package com.example.milkywaylaundry3.Model;

public class Category {
    private String Name;
    private String Image;

    //contructor for class
    public Category() {
    }

    //constructor fro all variable/model
    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    //Getter and Setter for all varibales

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
