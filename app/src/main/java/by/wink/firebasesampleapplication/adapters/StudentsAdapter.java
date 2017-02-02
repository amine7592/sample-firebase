package by.wink.firebasesampleapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import by.wink.firebasesampleapplication.R;
import by.wink.firebasesampleapplication.models.Student;

/**
 * Created by amine on 02/02/17.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {
    private ArrayList<Student> dataSet = new ArrayList<>();

    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentViewHolder holder = new StudentViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(StudentsAdapter.StudentViewHolder holder, int position) {
        Student currentStudent = dataSet.get(position);
        holder.studentNameTv.setText(currentStudent.getName());

    }


    public void setDataSet(ArrayList<Student> students) {
        dataSet = students;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView studentNameTv;
        public TextView studentCourseTv;

        public StudentViewHolder(final View v) {
            super(v);
            studentNameTv = (TextView) v.findViewById(R.id.student_name);
            studentCourseTv = (TextView) v.findViewById(R.id.student_course);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(v.getContext(), "Nome studente : " + dataSet.get(getAdapterPosition())
                            .getName(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
