package manakov.sample.dbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Course.Course;
import manakov.sample.dbapp.Course.CourseStudent;
import manakov.sample.dbapp.PaymentForm.PaymentForm;
import manakov.sample.dbapp.Student.Student;
import manakov.sample.dbapp.Student.StudentLanguageProficiency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentInfoActivity extends AppCompatActivity {
    private DbApplication application;

    private int studentId;

    private TextView studentInfoFirstNameTextView;
    private TextView studentInfoLastNameTextView;
    private TextView studentInfoPaymentFormTextView;

    private RecyclerView languageProficiencyRecyclerView;
    private LanguageProficiencyRecyclerViewAdapter languageProficiencyRecyclerViewAdapter;
    private ArrayList<StudentLanguageProficiency> studentLanguageProficiencies;

    private RecyclerView courseRecyclerView;
    private CourseRecyclerViewAdapter courseRecyclerViewAdapter;
    private ArrayList<Course> courses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        application = (DbApplication) this.getApplication();

        Intent intent = getIntent();
        studentId = intent.getExtras().getInt("studentId");

        studentInfoFirstNameTextView = findViewById(R.id.studentInfoFirstNameTextView);
        studentInfoLastNameTextView = findViewById(R.id.studentInfoLastNameTextView);
        studentInfoPaymentFormTextView = findViewById(R.id.studentInfoPaymentFormTextView);

        studentInfoFirstNameTextView.setVisibility(View.VISIBLE);
        studentInfoLastNameTextView.setVisibility(View.VISIBLE);
        studentInfoPaymentFormTextView.setVisibility(View.VISIBLE);

        Student student = application.database.studentDao().getStudentById(studentId);

        studentInfoFirstNameTextView.setText(student.getFirstName());
        studentInfoLastNameTextView.setText(student.getLastName());

        PaymentForm paymentForm = application.database.paymentFormDao().getPaymentFormById(
                application.database.studentPaymentFormDao().getStudentPaymentFormByStudentId(studentId).getPaymentFormId()
        );

        studentInfoPaymentFormTextView.setText(paymentForm.getPaymentForm());

        studentLanguageProficiencies = new ArrayList<>();
        studentLanguageProficiencies.addAll(
                        application.database.studentLanguageProficiencyDao().getStudentLanguageProficienciesByStudentId(studentId)
        );

        languageProficiencyRecyclerView = findViewById(R.id.studentInfoLangProfRecyclerView);
        languageProficiencyRecyclerView.setVisibility(View.VISIBLE);
        languageProficiencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //onClick

        languageProficiencyRecyclerViewAdapter = new LanguageProficiencyRecyclerViewAdapter(studentLanguageProficiencies, application);
        languageProficiencyRecyclerView.setAdapter(languageProficiencyRecyclerViewAdapter);
        languageProficiencyRecyclerViewAdapter.notifyDataSetChanged();


        courses = new ArrayList<>();
        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        courseStudents.addAll(
                application.database.courseStudentDao().getCourseStudentsByStudentId(studentId)
        );
        for (int i = 0; i< courseStudents.size(); i++){
            courses.add(
                    application.database.courseDao().getCourseById(
                            courseStudents.get(i).getCourseId()
                    )
            );
        }

        courseRecyclerView = findViewById(R.id.studentInfoCoursesRecyclerView);
        courseRecyclerView.setVisibility(View.VISIBLE);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseRecyclerViewAdapter = new CourseRecyclerViewAdapter(courses);
        courseRecyclerView.setAdapter(courseRecyclerViewAdapter);
        courseRecyclerViewAdapter.notifyDataSetChanged();





    }
}
