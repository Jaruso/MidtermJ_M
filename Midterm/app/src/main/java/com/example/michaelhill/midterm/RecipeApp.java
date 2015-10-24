package com.example.michaelhill.midterm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Attributes;

public class RecipeApp extends AppCompatActivity
{
    User temp;
    String mFirstName;
    String mLastName;
    boolean mPasswordCheck;
    String mZip;
    String mEmail;
    String mPassword;
    boolean mUsernameCheck;

    List<User> mUsers;
    List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitPage0();

        mEmail = "";
        mUsernameCheck = false;
        mFirstName = "";
        mLastName = "";
        mZip = "";
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
        setContentView(R.layout.welcome_page);



        /*
        final EditText usernameField = (EditText) findViewById(R.id.UsernameText);
        usernameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    if(!mEmail.trim().equals("") && mEmail.trim().equals(usernameField.getText().toString()))
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
                    if (!mPassword.trim().equals("") && mPassword.trim().equals(passwordField.getText().toString()))
                    {
                        mPasswordCheck = true;
                    } else
                        mPasswordCheck = false;

                    handled = true;
                }

                return handled;
            }
        });
        */

    }

    public void SignIn(View view)
    {
        EditText usernameField = (EditText) findViewById(R.id.UsernameText);
        EditText passwordField = (EditText) findViewById(R.id.PasswordText);

        temp = new User(usernameField.getText().toString(), passwordField.getText().toString());

        if(temp.checkUser(mUsers))
        {
            InitPage2();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Username or Password wasn't recognized", Toast.LENGTH_SHORT).show();
        }
    }

    public void SignUp(View view)
    {
        InitPage3();

    }

    private void GetText(EditText toUse, int fieldType)
    {
        switch (fieldType) {
            case 0:
                mEmail = toUse.getText().toString();
                break;
            case 1:
                mPassword = toUse.getText().toString();
                break;
            case 2:
                mFirstName = toUse.getText().toString();
                break;
            case 3:
                mLastName = toUse.getText().toString();
                break;
            case 4:
                mZip = toUse.getText().toString();
                break;
        }
    }

    //SIGN-UP PAGE------------------------------------------------------------------------------------------------------------------
    private void InitPage3()
    {
        setContentView(R.layout.sign_up_page);

        /*
        final EditText firstNameField = (EditText) findViewById(R.id.firstName);
        firstNameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    GetText(firstNameField, 2);

                    //hide keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(firstNameField.getWindowToken(), 0);

                    handled = true;
                }

                return handled;
            }
        });
        final EditText lastNameField = (EditText) findViewById(R.id.lastName);
        lastNameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    GetText(lastNameField, 3);

                    //hide keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(lastNameField.getWindowToken(), 0);

                    handled = true;
                }

                return handled;
            }
        });

        final EditText usernameField = (EditText) findViewById(R.id.email);
        usernameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    GetText(usernameField, 0);
                    mUsernameCheck =  true;

                    //hide keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(usernameField.getWindowToken(), 0);

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
                    if(!mPassword.trim().equals(""))
                    {
                        if(mPassword.trim().equals(passwordField.getText().toString()))
                        {
                            mPasswordCheck = true;
                        }
                    }
                    else
                        GetText(passwordField, 1);

                    //hide keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(passwordField.getWindowToken(), 0);

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
                    if (!mPassword.trim().equals(""))
                    {
                        if (mPassword.trim().equals(confirmField.getText().toString()))
                            mPasswordCheck = true;
                    } else
                        GetText(confirmField, 1);

                    //hide keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(confirmField.getWindowToken(), 0);

                    handled = true;
                }

                return handled;
            }
        });

        final EditText zipField = (EditText) findViewById(R.id.zipCode);
        zipField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    GetText(zipField, 4);

                    //hide keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(zipField.getWindowToken(), 0);

                    handled = true;
                }

                return handled;
            }
        });
        */
    }

    public void ConfirmAndSignIn(View view) {
        EditText firstNameField = (EditText) findViewById(R.id.firstName);
        EditText lastNameField = (EditText) findViewById(R.id.lastName);
        EditText usernameField = (EditText) findViewById(R.id.email);
        EditText passwordField = (EditText) findViewById(R.id.password);
        EditText confirmField = (EditText) findViewById(R.id.confirmPass);
        EditText zipField = (EditText) findViewById(R.id.zipCode);

        temp.mEmail = usernameField.getText().toString();

        if (!temp.isExist(mUsers)) {

            if(passwordField.getText().toString() == confirmField.getText().toString()) {
                User newUser = new User(firstNameField.getText().toString(),
                                        lastNameField.getText().toString(),
                                        passwordField.getText().toString(),
                                        zipField.getText().toString(),
                                        usernameField.getText().toString()  );
                mUsers.add(newUser);
                temp.setUser(newUser);
                InitPage2();
            }
            else{

                //password fields do not match
            }

        }
        else{
            //username already exists
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
