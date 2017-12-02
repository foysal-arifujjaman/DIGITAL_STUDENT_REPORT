package com.example.ma.digital_student_report;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ma on 01-Dec-17.
 */



public class GetFirebaseInstance {
    private static FirebaseDatabase firebaseDatabase;

    public static FirebaseDatabase GetInstance() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
            return firebaseDatabase;
        }
        return firebaseDatabase;
    }
}