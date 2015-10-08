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
    String mUsername;
    String mPassword;
    boolean mPasswordCheck;

    List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        mUsername = "";
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
    public void SignIn(View view) {
        setContentView(R.layout.activity_recipe_app);
        InitPage1();
    }

    public void SignUp(View view) {
        setContentView(R.layout.sing_up_page);
        InitPage3();
    }

    //SIGN-IN PAGE-------------------------------------------------------------------------------------------------------------------
    private void InitPage1() {
        final EditText usernameField = (EditText) findViewById(R.id.UsernameText);
        usernameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    GetText(usernameField, 0);

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
    }

    private void GetText(EditText toUse, int fieldType) {
        switch (fieldType) {
            case 0:
                mUsername = toUse.getText().toString();
                break;
            case 1:
                mPassword = toUse.getText().toString();
                break;
        }
    }

    public void ConfirmSignIn(View view) {
        setContentView(R.layout.recepies_page);
    }

    //SIGN-UP PAGE------------------------------------------------------------------------------------------------------------------
    private void InitPage3()
    {
        final EditText usernameField = (EditText) findViewById(R.id.UsernameText);
        usernameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    GetText(usernameField, 0);

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

        final EditText confirmField = (EditText) findViewById(R.id.ConfirmPassword);
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
        if(mPasswordCheck)
        {
            setContentView(R.layout.activity_recipe_app);
            InitPage1();
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
