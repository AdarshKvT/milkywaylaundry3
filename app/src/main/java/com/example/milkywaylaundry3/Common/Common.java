package com.example.milkywaylaundry3.Common;

import com.example.milkywaylaundry3.Model.User;

public class Common {

    public static User currentUser;


    public static String convertCodeToStatus(String status) {
        if ((status.equals("0")))
            return "Placed";
        else if (status.equals("1"))
            return "On my way";
        else
            return String.valueOf(Integer.parseInt("Arriving"));
    }
}
