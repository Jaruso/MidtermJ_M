package com.example.michaelhill.midterm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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

    ArrayList<User> mUsers = new ArrayList<>();
    ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitPage0();

        temp = new User();
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

        String user = usernameField.getText().toString();
        temp.mEmail = user;

        String pass = passwordField.getText().toString();


        if(temp.checkUser(mUsers, pass))
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

    public void returnHome(View view){
        temp.clear();
        InitPage0();
    }

    public void signOut(View view){
        returnHome(view);
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
       final EditText firstNameField = (EditText) findViewById(R.id.firstName);
       final EditText lastNameField = (EditText) findViewById(R.id.lastName);
       final EditText usernameField = (EditText) findViewById(R.id.email);
       final EditText passwordField = (EditText) findViewById(R.id.password);
       final EditText confirmField = (EditText) findViewById(R.id.confirmPass);
       final EditText zipField = (EditText) findViewById(R.id.zipCode);

        temp.mEmail = usernameField.getText().toString();

        String pass = passwordField.getText().toString();
        String passconfirm = confirmField.getText().toString();

        if(mUsers.size() == 0)
        {
            if (pass.equals(passconfirm)) {
                User newUser = new User(firstNameField.getText().toString(),
                        lastNameField.getText().toString(),
                        passwordField.getText().toString(),
                        zipField.getText().toString(),
                        usernameField.getText().toString());
                mUsers.add(newUser);
                temp.setUser(newUser);
                InitPage2();
            } else {

                Toast.makeText(getApplicationContext(), pass + " does not match " + passconfirm, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (temp.isExist(mUsers)) {
                Toast.makeText(getApplicationContext(), "Username already exists.", Toast.LENGTH_SHORT).show();
            } else {

                if (pass.equals(passconfirm)) {
                    User newUser = new User(firstNameField.getText().toString(),
                            lastNameField.getText().toString(),
                            passwordField.getText().toString(),
                            zipField.getText().toString(),
                            usernameField.getText().toString());
                    mUsers.add(newUser);
                    temp.setUser(newUser);
                    InitPage2();
                } else {

                    Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }

            }
        }


    }
    //RECIPES PAGE------------------------------------------------------------------------------------------------------------------
    private void InitPage2()
    {
        setContentView(R.layout.recepies_page);

        final int N = mRecipes.size(); // total number of textviews to add

        for(int i = 0; i < N; i++)
        {
            mRecipes.get(i).AddRecipeToRecipes(getApplicationContext(), (LinearLayout) findViewById(R.layout.recepies_page) );
        }
    }
}
