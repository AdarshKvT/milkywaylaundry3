package com.example.milkywaylaundry3.Model;

public class Pant {
    private String Name, Image, Price,  MenuId;

    //constructor for class
    public Pant() {
    }
    //constructor fro all variable/model

    public Pant(String name, String image, String price, String menuId) {
        Name = name;
        Image = image;
        Price = price;
        MenuId = menuId;
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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
