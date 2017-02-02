package by.wink.firebasesampleapplication.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amine on 01/02/17.
 */

@IgnoreExtraProperties
public class Student {

    private String name;
    private String course = "VM Sviluppo";

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Student.class)
    }

    public Student(String name,String c) {
        this.name = name;
        course = c;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("course",course);
        return result;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }
}