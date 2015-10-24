package com.example.michaelhill.midterm;

import java.util.List;

/**
 * Created by joseph.caruso on 10/8/2015.
 */
public class User
{
    public String mFirstName;
    public String mLastName;
    private String mPassword;
    public String mZip;
    public String mEmail;


    public User(){

        mFirstName = "John";
        mLastName = "Doe";
        mPassword = "pass";
        mZip = "0000";
        mEmail = "jd@gmail.com";

    }

    public User(String email, String pass){

        mFirstName = "";
        mLastName = "";
        mPassword = pass;
        mZip = "";
        mEmail = email;

    }

    public User(String first, String last, String pass, String zip, String email){

        mFirstName = first;
        mLastName = last;
        mPassword = pass;
        mZip = zip;
        mEmail = email;

    }

    public void setUser(User store){

        mFirstName = store.mFirstName;
        mLastName = store.mLastName;
        mZip = store.mLastName;

    }

    // return true if user credentials are correct
    public boolean checkUser(List<User> list){

        if( list.size() > 0 )
        {
            // check if name is a name in the list of users
            for(int i=0; i < list.size(); i++)
            {
                if (this.mEmail == list.get(i).mEmail && list.get(i).checkPass(this.mPassword))
                {
                    this.setUser(list.get(i));
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isExist(List<User> list){

        if( list.size() > 0 )
        {
            // check if name is a name in the list of users
            for(int i=0; i < list.size(); i++)
            {
                if (this.mEmail == list.get(i).mEmail )
                {
                    return true;
                }
            }
        }

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
