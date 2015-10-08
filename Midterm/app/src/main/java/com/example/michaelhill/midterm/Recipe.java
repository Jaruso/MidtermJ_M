package com.example.michaelhill.midterm;

public class Recipe
{
    private int mPrepTime;
    private int mCookTime;
    private String mInstructions;

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

    public int GetPrepTime()
    {
        return mPrepTime;
    }
    public int GetCookTime()
    {
        return mCookTime;
    }
    public String GetInstructions()
    {
        return mInstructions;
    }
}
