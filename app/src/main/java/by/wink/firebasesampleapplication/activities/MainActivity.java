package by.wink.firebasesampleapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import by.wink.firebasesampleapplication.R;
import by.wink.firebasesampleapplication.adapters.StudentsAdapter;
import by.wink.firebasesampleapplication.adapters.viewholders.StudentViewHolder;
import by.wink.firebasesampleapplication.controllers.AuthController;
import by.wink.firebasesampleapplication.controllers.StudentController;
import by.wink.firebasesampleapplication.models.Student;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    StudentController studentController;
    RecyclerView studentsRv;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!AuthController.isLogginIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        studentController = new StudentController(this);

        studentsRv = (RecyclerView) findViewById(R.id.students_recycler);
        studentsRv.setLayoutManager(new LinearLayoutManager(this));


        addButton = (FloatingActionButton) findViewById(R.id.add_student);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddStudentDialog();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Query students = studentController.getStudents();
        final FirebaseRecyclerAdapter<Student, StudentViewHolder> adapter = new FirebaseRecyclerAdapter<Student,
                StudentViewHolder>(Student.class, R.layout.item_student, StudentViewHolder.class, students) {

            @Override
            protected void populateViewHolder(StudentViewHolder holder, Student student, int position) {
                holder.studentNameTv.setText(student.getName());
                holder.studentCourseTv.setText(student.getCourse());

            }
        };
        studentsRv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                AuthController.logout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            case R.id.profile:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAddStudentDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_student_add, null);
        dialogBuilder.setView(dialogView);

        final EditText userEt = (EditText) dialogView.findViewById(R.id.student_name_dialog);

        dialogBuilder.setTitle(R.string.student);
        dialogBuilder.setMessage(R.string.insert_student_name);
        dialogBuilder.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                studentController.addStudent(userEt.getText().toString(), new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

}
