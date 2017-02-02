package by.wink.firebasesampleapplication.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import by.wink.firebasesampleapplication.R;

/**
 * Created by amine on 02/02/17.
 */

public class StudentViewHolder extends RecyclerView.ViewHolder {
    public TextView studentNameTv;
    public TextView studentCourseTv;

    public StudentViewHolder(final View v) {
        super(v);
        studentNameTv = (TextView) v.findViewById(R.id.student_name);
        studentCourseTv = (TextView) v.findViewById(R.id.student_course);


    }
}
