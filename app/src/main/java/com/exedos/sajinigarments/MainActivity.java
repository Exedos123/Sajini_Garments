 package com.exedos.sajinigarments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.exedos.sajinigarments.RegisterActivity.setSignUpFragment;

 public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static DrawerLayout drawer1;
    private NavigationView navigationView;


    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;
    private static final int MYACCOUNT_FRAGMENT = 4;
    private static final int CONTACT_FRAGMENT = 5;

    public static Boolean showCart = false;
    public static Boolean showContact = false;
    private Dialog signInDialog;
    private FirebaseUser currentUser;

    private FrameLayout frameLayout;
    private int currentFragment = -1;
    private TextView actionBarTitle;

    private CircleImageView profileImage;
    private TextView fullName,email;
    private ImageView addProfileIcon;

    @SuppressLint({"WrongConstant", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        actionBarTitle = findViewById(R.id.actionbar_logoTitle);
        setSupportActionBar(toolbar);

        drawer1 = findViewById(R.id.drawer_layout);

       navigationView = findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(this);
       navigationView.getMenu().getItem(0).setChecked(true);

       frameLayout = findViewById(R.id.main_frameLayout);

       profileImage = navigationView.getHeaderView(0).findViewById(R.id.navheader_Profile_image);
       fullName = navigationView.getHeaderView(0).findViewById(R.id.navheader_user_fullname);
       email = navigationView.getHeaderView(0).findViewById(R.id.navheader_user_email);
       addProfileIcon = navigationView.getHeaderView(0).findViewById(R.id.navheader_plus_icon);


       fullName.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(currentUser == null) {

                   Intent mainIntent = new Intent(MainActivity.this, RegisterActivity.class);
                   startActivity(mainIntent);
               }
               else{
                   return;
               }
           }
       });



        if (showCart) {
            drawer1.setDrawerLockMode(1);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart", new MyCartFragment(), -2);
        }

        else if (showContact) {
            drawer1.setDrawerLockMode(1);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("Contact US", new ContactFragment(), -3);
        }

        else  {

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer1, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer1.addDrawerListener(toggle);
            toggle.syncState();
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }

        if (currentUser == null) {
        navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        }
        else {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(true);
        }

        signInDialog = new Dialog(MainActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialog);
        signInDialog.setCancelable(true);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogSignInBtn = signInDialog.findViewById(R.id.signIn_btn);
        Button dialogSignUpBtn = signInDialog.findViewById(R.id.signUp_btn);
        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);


        dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUpFragment.disableCloseBtn = true;
                SignInFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(registerIntent);
            }
        });


        dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment.disableCloseBtn = true;
                SignInFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(registerIntent);
            }
        });


    }

     @Override
     protected void onStart() {
         super.onStart();

         currentUser = FirebaseAuth.getInstance().getCurrentUser();

         if (currentUser == null) {
             navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
         }
         else {
             FirebaseFirestore.getInstance().collection("USERS").document(currentUser.getUid())
                     .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                     if (task.isSuccessful()) {
                         DBqueries.fullname = task.getResult().getString("fullname");
                         DBqueries.email = task.getResult().getString("email");
                         DBqueries.profile = task.getResult().getString("profile");

                         fullName.setText(DBqueries.fullname);
                         email.setText(DBqueries.email);

                         //// code for profile image
                        /* if(DBqueries.profile.equals("")){
                             addProfileIcon.setVisibility(View.VISIBLE);
                         }else {
                             addProfileIcon.setVisibility(View.INVISIBLE);
                             Glide.with(MainActivity.this).load(DBqueries.profile).apply(new RequestOptions().placeholder(R.drawable.ic_baseline_person_24)).into(profileImage);
                         }
                        */
                     }
                     else{
                     String error = task.getException().getMessage();
                         Toast.makeText(MainActivity.this,error, Toast.LENGTH_SHORT).show();
                 }
             }
             });
             navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(true);
         }

     }

     @Override
    public void onBackPressed() {
        DrawerLayout drawer1 = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer1.isDrawerOpen(GravityCompat.START)){
            drawer1.closeDrawer(GravityCompat.START);
        }
        else {
            if (currentFragment == HOME_FRAGMENT) {
                currentFragment = -1;
                super.onBackPressed();
            }
            else{
                if(showCart){
                    showCart = false;
                    finish();

                }
                else if(showContact){
                    showContact = false;
                    finish();
                }
                else {
                    actionBarTitle.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(), HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setChecked(true);
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(currentFragment == HOME_FRAGMENT ) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_search_icon){
            return true;
        }
        else if(id == R.id.action_cart){

            if(currentUser == null) {
                signInDialog.show();
            }else {
                // gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                Toast.makeText(this, "Feature will roll out soon", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        else if(id == R.id.action_profile){
            if(currentUser == null) {
                signInDialog.show();
            }else {
                gotoFragment("My Account", new MyAccountFragment(), MYACCOUNT_FRAGMENT);
            }
            return true;
        } else if(id == android.R.id.home){
            if(showCart){
                showCart = false;
                finish();
                return true;
            }
            else if(showContact){
                showContact = false;
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNO) {
        actionBarTitle.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNO);
        if(fragmentNO == HOME_FRAGMENT){
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        else if(fragmentNO == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }
        else if(fragmentNO == MYACCOUNT_FRAGMENT) {
            navigationView.getMenu().getItem(2).setChecked(true);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer1 = (DrawerLayout) findViewById(R.id.drawer_layout);

        int id = item.getItemId();

       ///    Nav menu for version 1
        if (id == R.id.nav_home) {
            actionBarTitle.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }

        if (id == R.id.nav_shop) {
            Intent shopIntent = new Intent(MainActivity.this, ProductsVone.class);
            startActivity(shopIntent);
            drawer1.closeDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.nav_Contact) {
            gotoFragment("Contact Us", new ContactFragment(), CONTACT_FRAGMENT);

        } else if (id == R.id.nav_account) {

            if (currentUser != null) {
                gotoFragment("My Account", new MyAccountFragment(), MYACCOUNT_FRAGMENT);
            } else {
                drawer1.closeDrawer(GravityCompat.START);
                signInDialog.show();
                return false;
            }
        } else if (id == R.id.nav_Signout) {
            FirebaseAuth.getInstance().signOut();
            Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
            finish();
        }

        drawer1.closeDrawer(GravityCompat.START);
        return true;
        ///    Nav menu for version 1


/*  // original code for full navigation menu

       if(currentUser != null) {

            if (id == R.id.nav_home) {
                actionBarTitle.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
            }


            else if (id == R.id.nav_info) {

            } else if (id == R.id.nav_orders) {
                gotoFragment("My Orders", new MyOrdersFragment(), ORDERS_FRAGMENT);

            } else if (id == R.id.nav_cart) {
               // gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                Toast.makeText(this, "Feature will roll out soon", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_wishlist) {
                gotoFragment("My WishList", new MyWishlistFragment(), WISHLIST_FRAGMENT);
            }
              if (id == R.id.nav_shop) {
                 Intent shopIntent = new Intent(MainActivity.this, ProductsVone.class);
                 startActivity(shopIntent);
                 drawer1.closeDrawer(GravityCompat.START);
                 return true;
             } else if (id == R.id.nav_Contact) {
                 gotoFragment("Contact Us", new ContactFragment(), CONTACT_FRAGMENT);

             }   else if (id == R.id.nav_account) {
                gotoFragment("My Account", new MyAccountFragment(), MYACCOUNT_FRAGMENT);
            } else if (id == R.id.nav_Signout) {
                FirebaseAuth.getInstance().signOut();
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }

            drawer1.closeDrawer(GravityCompat.START);
            return true;
       }

        else{

            drawer1.closeDrawer(GravityCompat.START);
            signInDialog.show();
            return false;
        }
                     //  original code for full navigation menu    */


    }



    private void setFragment(Fragment fragment, int fragmentNo){
        if(fragmentNo != currentFragment) {
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }

    }



}

