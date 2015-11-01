package com.example.michaelhill.midterm;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Recipe
{
    String mTitle;
    String mIngredients;
    private int mPrepTime;
    private int mCookTime;
    private String mInstructions;
    int mServes;    // # of people
    private String mPath;    // path to image

    public Recipe()
    {
        mPrepTime = -1;
        mCookTime = -1;
        mInstructions = "VOID";
    }

    public void SetFields(int prepTime, int cookTime, String instructions)
    {
        mPrepTime = prepTime;
        mCookTime = cookTime;
        mInstructions = instructions;
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

    public void AddRecipeToRecipes(Context context, LinearLayout toUse)
    {
        TextView textView1 = new TextView(context);
        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textView1.setText(mTitle + "\nPrep time : " + mPrepTime + "\nCook Time: " + mCookTime + "\nTotal time: " + (mCookTime+mPrepTime));
        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
        toUse.addView(textView1);
    }

    public void PrintRecipe(Context context, LinearLayout toUse)
    {
        TextView textView1 = new TextView(context);
        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textView1.setText(mTitle +
                        "\nPrep time : " + mPrepTime +
                        "\nCook Time: " + mCookTime +
                        "\nTotal time: " + (mCookTime+mPrepTime) +
                        "\nINGREDIENTS: " + mIngredients +
                        "\nDIRECTIONS: " + mInstructions);
        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
        toUse.addView(textView1);
    }
}
