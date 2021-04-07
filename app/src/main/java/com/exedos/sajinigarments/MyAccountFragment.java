package com.exedos.sajinigarments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.awt.font.TextAttribute;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {


    public MyAccountFragment() {
        // Required empty public constructor
    }
    private Button viewAllAddressBtn, signOutBtn;

    public static final int MANAGE_ADDRESS = 1;

    private CircleImageView profileImage;
    private TextView name,email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        profileImage = view.findViewById(R.id.profile_image);
        name = view.findViewById(R.id.profile_username);
        email = view.findViewById(R.id.profile_user_email);

        signOutBtn = view.findViewById(R.id.sign_out_btn);

        name.setText(DBqueries.fullname);
        email.setText(DBqueries.email);
        if(!DBqueries.profile.equals("")){
            Glide.with(getContext()).load(DBqueries.profile).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_person_24)).into(profileImage);
        }


        viewAllAddressBtn = view.findViewById(R.id.view_all_addresses_btn);
     /*   viewAllAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressItent = new Intent(getContext(), MyAddressActivity.class);
                myAddressItent.putExtra("MODE",MANAGE_ADDRESS);
                startActivity(myAddressItent);

            }
        });
*/

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent registerIntent = new Intent(getContext(),RegisterActivity.class);
                startActivity(registerIntent);
                getActivity().finish();
            }
        });


        return view;
    }

}