package com.example.rahmanm2.firebaseui.FirebaseUtil;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.rahmanm2.firebaseui.DataModel.DealModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseUi {

    private static final int RC_SIGN_IN = 123;
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static ChildEventListener mChildEventListener;
    private static FirebaseUi mFireBaseUtil;
    public static ArrayList<DealModel>mDeals;


    //firebase autonication
    public static FirebaseAuth mFirebaseAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    private static Activity caller;
    //fire base storoge connection
    public static FirebaseStorage mFirebaseStorage;
    public static StorageReference mStorageReference;

    private FirebaseUi(){

    }
    public static void openFirebase(String Url, Activity call){
        if(mFireBaseUtil==null){
            mFireBaseUtil = new FirebaseUi();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseAuth = FirebaseAuth.getInstance();
            caller = call;
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser()==null) {
                        FirebaseUi.SignIn();
                    }
                    Toast.makeText(caller.getBaseContext(),"Welcome Back",Toast.LENGTH_LONG).show();
                }
            };
            storageConnection("deals_picture");
        }
        mDeals = new ArrayList<DealModel>();
        mDatabaseReference = mFirebaseDatabase.getReference().child(Url);
    }

    private static void SignIn(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        // Create and launch sign-in intent
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }
    public static void storageConnection(String url){
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference().child(url);
    }
    public static void AttachListener(){
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }
    public static void DetechListener(){
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }
}
