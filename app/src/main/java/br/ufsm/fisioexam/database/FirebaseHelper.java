package br.ufsm.fisioexam.database;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseHelper instance;
    private final DatabaseReference databaseReference;

    private FirebaseUser fbUser;


    private FirebaseHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


    }

    public static synchronized FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
        }
        return instance;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void setFbUser(FirebaseUser fbUser) {
        this.fbUser = fbUser;
    }

    public FirebaseUser getFbUser() {
        return fbUser;
    }
}
