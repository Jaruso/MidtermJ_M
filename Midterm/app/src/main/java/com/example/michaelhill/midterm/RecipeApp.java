package com.example.michaelhill.midterm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Attributes;

public class RecipeApp extends AppCompatActivity
{
    String mFirstName;
    String mLastName;
    String mPassword;
    boolean mPasswordCheck;
    int mZip;
    String mEmail;
    boolean mUsernameCheck;

    List<User> mUsers;
    List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        InitPage0();

        mEmail = "";
        mUsernameCheck = false;
        mFirstName = "";
        mLastName = "";
        mZip = 0;
        mPassword = "";
        mPasswordCheck = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //WELCOME PAGE------------------------------------------------------------------------------------------------------------------
    private void InitPage0()
    {
        final EditText usernameField = (EditText) findViewById(R.id.UsernameText);
        usernameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND)
                {
                    if(mEmail != "" && mEmail == usernameField.getText().toString())
                    {
                        mUsernameCheck = true;
                    }
                    else
                        mUsernameCheck = false;

                    handled = true;
                }

                return handled;
            }
        });

        final EditText passwordField = (EditText) findViewById(R.id.PasswordText);
        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (mPassword != "" && mPassword == passwordField.getText().toString())
                    {
                        mPasswordCheck = true;
                    } else
                        mPasswordCheck = false;

                    handled = true;
                }

                return handled;
            }
        });
    }

    public void SignIn(View view)
    {
        if(mUsernameCheck && mPasswordCheck)
        {
            User toCheck = new User();

            if(toCheck.checkUser(mUsers, mEmail, mPassword))
            {
                setContentView(R.layout.recepies_page);
                InitPage3();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Username or Password wasn't recognized", Toast.LENGTH_SHORT).show();
        }
    }

    public void SignUp(View view) {
        setContentView(R.layout.sign_up_page);
        InitPage3();
    }

    private void GetText(EditText toUse, int fieldType) {
        switch (fieldType) {
            case 0:
                mEmail = toUse.getText().toString();
                break;
            case 1:
                mPassword = toUse.getText().toString();
                break;
        }
    }

    //SIGN-UP PAGE------------------------------------------------------------------------------------------------------------------
    private void InitPage3()
    {
        final EditText usernameField = (EditText) findViewById(R.id.email);
        usernameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    GetText(usernameField, 0);
                    mUsernameCheck =  true;

                    handled = true;
                }

                return handled;
            }
        });

        final EditText passwordField = (EditText) findViewById(R.id.password);
        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    if(mPassword != "")
                    {
                        if(mPassword == passwordField.getText().toString())
                            mPasswordCheck = true;
                    }
                    else
                        GetText(passwordField, 1);

                    handled = true;
                }

                return handled;
            }
        });

        final EditText confirmField = (EditText) findViewById(R.id.confirmPass);
        confirmField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    if (mPassword != "") {
                        if (mPassword == confirmField.getText().toString())
                            mPasswordCheck = true;
                    } else
                        GetText(confirmField, 2);

                    handled = true;
                }

                return handled;
            }
        });
    }

    public void ConfirmAndSignIn(View view)
    {
        if(mPasswordCheck && mUsernameCheck)
        {
            User toAdd = new User();
            toAdd.makeUser(mFirstName, mLastName, mPassword, mZip, mEmail);

            SignIn(view);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    //RECIPES PAGE------------------------------------------------------------------------------------------------------------------
    private void InitPage2()
    {
        final int N = mRecipes.size(); // total number of textviews to add
        final TextView[] myTextViews;

        if(N > 0)
        {
            myTextViews = new TextView[N]; // create an empty array;

            for (int i = 0; i < N; i++)
            {
                // create a new textview
                final TextView rowTextView = new TextView(this);

                // set some properties of rowTextView or something
                rowTextView.setText("This is row #" + i);

                // add the textview to the linearlayout
                //myLinearLayout.addView(rowTextView);

                // save a reference to the textview for later
                myTextViews[i] = rowTextView;
            }
        }
        else {
            myTextViews = new TextView[1]; // create an empty array;

            for (int i = 0; i < N; i++)
            {
                // create a new textview
                final TextView rowTextView = new TextView(this);

                // set some properties of rowTextView or something
                rowTextView.setText("This is row #" + i);

                // add the textview to the linearlayou

                myTextViews[i] = rowTextView;
            }
        }
    }
}
