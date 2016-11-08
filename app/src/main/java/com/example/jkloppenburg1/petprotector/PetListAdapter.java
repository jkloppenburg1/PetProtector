package com.example.jkloppenburg1.petprotector;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.appcompat.R.styleable.View;

/**
 * Created by Nick on 11/7/2016.
 */

public class PetListAdapter extends ArrayAdapter<Pet> {

    private Context mContext;
    private List<Pet> mPetList = new ArrayList<>();
    private int mResourceId;

    private ImageView petListImageView;
    private TextView petListNameTextView;
    private TextView petListDetailsTextView;

    private LinearLayout petListLinearLayout;

    public PetListAdapter(Context c, int rId, List<Pet> pets)
    {
        super(c, rId, pets);
        mContext = c;
        mResourceId = rId;
        mPetList = pets;
    }

    @Override
    public View getView(int pos, android.view.View convertView, ViewGroup parent)
    {
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        final Pet selectedPet = mPetList.get(pos);

        petListImageView = (ImageView) view.findViewById(R.id.petListImageView);
        petListNameTextView = (TextView) view.findViewById(R.id.petListNameTextView);
        petListDetailsTextView = (TextView) view.findViewById(R.id.petListDetailsTextView);
        petListLinearLayout = (LinearLayout) view.findViewById(R.id.petListLinearLayout);

        petListLinearLayout.setTag(selectedPet);

        petListImageView.setImageURI(selectedPet.getImageUri());
        petListNameTextView.setText(selectedPet.getName());
        petListDetailsTextView.setText(selectedPet.getDetails());

        return view;
    }
}
