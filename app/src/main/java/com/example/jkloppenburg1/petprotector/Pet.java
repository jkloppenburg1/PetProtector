package com.example.jkloppenburg1.petprotector;

import android.net.Uri;


/**
 * Created by Nick on 11/7/2016.
 */

public class Pet
{
    private int mId;
    private String mName;
    private String mDetails;
    private int mPhone;
    private Uri mImageUri;

    public Pet(int mId, String mName, String mDetails, int mPhone, Uri mImageUri) {
        this.mId = mId;
        this.mName = mName;
        this.mDetails = mDetails;
        this.mPhone = mPhone;
        this.mImageUri = mImageUri;
    }

    public Pet(String mName, String mDetails, int mPhone, Uri mImageUri) {
        this.mName = mName;
        this.mDetails = mDetails;
        this.mPhone = mPhone;
        this.mImageUri = mImageUri;
    }

    public String toString()
    {
        return "";
    }


    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public int getPhone() {
        return mPhone;
    }

    public void setPhone(int mPhone) {
        this.mPhone = mPhone;
    }

    public Uri getImageUri() {
        return mImageUri;
    }

    public void setImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }
}
