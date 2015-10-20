package com.example.michaelhill.midterm;

import java.util.List;

/**
 * Created by joseph.caruso on 10/8/2015.
 */
public class User {

    public String mFirstName;
    public String mLastName;
    private String mPassword;
    public int mZip;
    public String mEmail;


    public User(){

        mFirstName = "John";
        mLastName = "Doe";
        mPassword = "pass";
        mZip = 00000;
        mEmail = "jd@gmail.com";

    }

    public void makeUser(String first, String last, String pass, int zip, String email){

        mFirstName = first;
        mLastName = last;
        mPassword = pass;
        mZip = zip;
        mEmail = email;

    }

    // return true if user credentials are correct
    public boolean checkUser(List<User> list, String name, String pass){

        // if list.size() != 0
        // check if name is a name in the list of users
        for(int i=0; i < list.size(); i++){



        }

        // if yes, call checkPass

            // if check pass returns true, return true

        // else
        return false;
    }

     // returns true if pass is correct
    private boolean checkPass(String pass){

        // if this.mPassword == pass return true
        if(this.mPassword == pass)
            return true;
        else
            return false;
    }



}
