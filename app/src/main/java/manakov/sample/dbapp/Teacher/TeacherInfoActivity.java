package manakov.sample.dbapp.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Course.Course;
import manakov.sample.dbapp.Course.CourseInfoActivity;
import manakov.sample.dbapp.Course.CourseRecyclerViewAdapter;
import manakov.sample.dbapp.Course.CourseTeacher;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.Language.LanguageProficiencyRecyclerViewAdapter;
import manakov.sample.dbapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class TeacherInfoActivity extends AppCompatActivity {
    private DbApplication application;

    private int teacherId;

    private TextView teacherInfoFirstNameTextView;
    private TextView teacherInfoLastNameTextView;

    private RecyclerView languageProficiencyRecyclerView;
    private LanguageProficiencyRecyclerViewAdapter languageProficiencyRecyclerViewAdapter;
    private ArrayList<TeacherLanguageProficiency> languageProficiencies;

    private RecyclerView courseRecyclerView;
    private CourseRecyclerViewAdapter courseRecyclerViewAdapter;
    private ArrayList<Course> courses;

    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        application = (DbApplication) this.getApplication();

        Intent intent = getIntent();
        teacherId = intent.getExtras().getInt("teacherId");

        teacherInfoFirstNameTextView = findViewById(R.id.teacherInfoFirstNameTextView);
        teacherInfoLastNameTextView = findViewById(R.id.teacherInfoLastNameTextView);

        Teacher teacher = application.database.teacherDao().getTeacherById(teacherId);

        teacherInfoFirstNameTextView.setText(teacher.getFirstName());
        teacherInfoLastNameTextView.setText(teacher.getLastName());

        //

        languageProficiencies = new ArrayList<>();
        languageProficiencies.addAll(
                application.database.teacherLanguageProficiencyDao().getTeacherLanguageProficienciesByTeacherId(teacherId)
        );

        languageProficiencyRecyclerView = findViewById(R.id.teacherInfoLangProfRecyclerView);
        languageProficiencyRecyclerView.setVisibility(View.VISIBLE);
        languageProficiencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        languageProficiencyRecyclerViewAdapter = new LanguageProficiencyRecyclerViewAdapter(languageProficiencies, application, teacher);
        languageProficiencyRecyclerView.setAdapter(languageProficiencyRecyclerViewAdapter);
        languageProficiencyRecyclerViewAdapter.notifyDataSetChanged();

        //

        courses = new ArrayList<>();
        final ArrayList<CourseTeacher> courseTeachers = new ArrayList<>();
        courseTeachers.addAll(
                application.database.courseTeacherDao().getCourseTeachersByTeacherId(teacherId)
        );
        for (int i = 0; i< courseTeachers.size(); i++){
            courses.add(
                    application.database.courseDao().getCourseById(
                            courseTeachers.get(i).getCourseId()
                    )
            );
        }

        courseRecyclerView = findViewById(R.id.teacherInfoCoursesRecyclerView);
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
    }

    public void onDeleteTeacherClick(View view){
        application.database.teacherDao().deleteTeacherByTeacherId(teacherId);
        setResult(RESULT_OK);
        finish();
    }
}
