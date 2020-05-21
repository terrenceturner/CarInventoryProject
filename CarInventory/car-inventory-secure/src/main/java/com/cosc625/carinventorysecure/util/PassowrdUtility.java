package com.cosc625.carinventorysecure.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassowrdUtility {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //kBryant Sales Manager
        String password = "PaSsWorD";
        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);

        PasswordEncoder encoder1 = new BCryptPasswordEncoder();
        //kIrving Sales Person
        String password1 = "!2@3#^^5";
        String encodedPassword1 = encoder1.encode(password1);
        System.out.println(encodedPassword1);

        PasswordEncoder encoder2 = new BCryptPasswordEncoder();
        //dBooker Staff
        String password2 = "IsThisPasswordOK?";
        String encodedPassword2 = encoder1.encode(password2);
        System.out.println(encodedPassword2);
    }
}
