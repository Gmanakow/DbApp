package manakov.sample.dbapp.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Course.Course;
import manakov.sample.dbapp.Course.CourseInfoActivity;
import manakov.sample.dbapp.Course.CourseStudent;
import manakov.sample.dbapp.Course.CourseRecyclerViewAdapter;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.Language.LanguageProficiencyRecyclerViewAdapter;
import manakov.sample.dbapp.PaymentForm.PaymentForm;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.PaymentForm.Payment;

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
    private ArrayList<StudentLanguageProficiency> languageProficiencies;

    private RecyclerView courseRecyclerView;
    private CourseRecyclerViewAdapter courseRecyclerViewAdapter;
    private ArrayList<Course> courses;

    private RecyclerView paymentRecycleView;
    private StudentPaymentRecyclerViewAdapter paymentRecycleViewAdapter;
    private ArrayList<Payment> payments;

    private View.OnClickListener onClickListener;


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

        //

        languageProficiencies = new ArrayList<>();
        languageProficiencies.addAll(
                        application.database.studentLanguageProficiencyDao().getStudentLanguageProficienciesByStudentId(studentId)
        );

        languageProficiencyRecyclerView = findViewById(R.id.studentInfoLangProfRecyclerView);
        languageProficiencyRecyclerView.setVisibility(View.VISIBLE);
        languageProficiencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        languageProficiencyRecyclerViewAdapter = new LanguageProficiencyRecyclerViewAdapter(languageProficiencies, application, student);
        languageProficiencyRecyclerView.setAdapter(languageProficiencyRecyclerViewAdapter);
        languageProficiencyRecyclerViewAdapter.notifyDataSetChanged();

        //

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

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), CourseInfoActivity.class);
                intent.putExtra("courseId", courses.get(position).getId());
                startActivity(intent);
            }
        };

        courseRecyclerViewAdapter = new CourseRecyclerViewAdapter(courses);
        courseRecyclerViewAdapter.setOnClickListener(onClickListener);
        courseRecyclerView.setAdapter(courseRecyclerViewAdapter);
        courseRecyclerViewAdapter.notifyDataSetChanged();

        //

        payments =
                (ArrayList<Payment>)
                application.database.paymentDao().getPaymentsByStudentId(
                        studentId
                );

        paymentRecycleView = findViewById(R.id.studentInfoPaymentRecyclerView);
        paymentRecycleView.setVisibility(View.VISIBLE);
        paymentRecycleView.setLayoutManager(new LinearLayoutManager(this));

        paymentRecycleViewAdapter = new StudentPaymentRecyclerViewAdapter(payments, application);
        paymentRecycleView.setAdapter(paymentRecycleViewAdapter);
        paymentRecycleViewAdapter.notifyDataSetChanged();

    }

    public void onDeleteStudentClick(View view){
        application.database.studentDao().deleteStudentByStudentId(studentId);
        setResult(RESULT_OK);
        finish();
    }

}
