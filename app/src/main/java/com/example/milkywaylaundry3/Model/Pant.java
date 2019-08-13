package com.example.milkywaylaundry3.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pant implements Parcelable {
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

    protected Pant(Parcel in) {
        Name = in.readString();
        Image = in.readString();
        Price = in.readString();
        MenuId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Image);
        dest.writeString(Price);
        dest.writeString(MenuId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pant> CREATOR = new Creator<Pant>() {
        @Override
        public Pant createFromParcel(Parcel in) {
            return new Pant(in);
        }

        @Override
        public Pant[] newArray(int size) {
            return new Pant[size];
        }
    };

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
