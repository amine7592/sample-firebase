package by.wink.firebasesampleapplication.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import by.wink.firebasesampleapplication.activities.MainActivity;
import by.wink.firebasesampleapplication.models.Student;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by amine on 01/02/17.
 */

public class StudentController {
    Context context;
    private DatabaseReference mDatabase;

    public StudentController(Context context) {
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("students");
    }

    public Query getStudents() {
        return mDatabase;

    }

    public void addStudent(final String studentName, final ValueEventListener callback) {

        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Student student = new Student(studentName,"VM Sviluppo");
                        Random generator = new Random();
                        int i = generator.nextInt(1000) + 1;
                        mDatabase.child("student" + i).setValue(student.toMap());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

}
