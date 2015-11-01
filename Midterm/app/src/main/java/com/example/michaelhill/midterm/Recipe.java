package com.example.michaelhill.midterm;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class Recipe
{
    Activity mActivity;

    String mTitle;
    String mIngredients;
    private int mPrepTime;
    private int mCookTime;
    private String mInstructions;
    int mServes;    // # of people
    private String mPath;    // path to image

    public Recipe(Activity activity)
    {
        mActivity = activity;

        mPrepTime = -1;
        mCookTime = -1;
        mInstructions = "VOID";
        mIngredients = "VOID";

        mServes = -1;
    }

        public void SetFields(int prepTime, int cookTime, String instructions, String ingredients, int serves)
    {
        mPrepTime = prepTime;
        mCookTime = cookTime;
        mInstructions = instructions;
        mIngredients = ingredients;
        mServes = serves;
    }

    public void SetFields(int prepTime, int cookTime, String instructions, String ingredients, int serves, String title, String path)
    {
        mPrepTime = prepTime;
        mCookTime = cookTime;
        mInstructions = instructions;
        mIngredients = ingredients;
        mServes = serves;
        mTitle = title;
        mPath = path;
    }

    public int GetPrepTime() { return mPrepTime; }
    public int GetCookTime()
    {
        return mCookTime;
    }
    public String GetInstructions()
    {
        return mInstructions;
    }

    public void AddRecipeToRecipes(LinearLayout toUse)
    {
        Button textView1 = new Button(mActivity.getApplicationContext());
        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textView1.setText(mTitle +
                "\nPrep time : " + mPrepTime +
                "\nCook Time: " + mCookTime +
                "\nTotal time: " + (mCookTime + mPrepTime));
        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)

        textView1.setOnClickListener(getOnClickDoSomething(textView1));

        //adding to the scroll view
        toUse.addView(textView1);
    }

    //on click listener for the buttons
    View.OnClickListener getOnClickDoSomething(final Button button)
    {
        return new View.OnClickListener()
        {
            public void onClick(View v)
            {
                mActivity.setContentView(R.layout.recipe);

                TextView toWrite = (TextView) mActivity.findViewById(R.id.printer);
                toWrite.setText(mTitle +
                        "\nPrep time : " + mPrepTime +
                        "\nCook Time: " + mCookTime +
                        "\nTotal time: " + (mCookTime+mPrepTime) +
                        "\nINGREDIENTS: " + mIngredients +
                        "\nDIRECTIONS: " + mInstructions);
            }
        };
    }
}
