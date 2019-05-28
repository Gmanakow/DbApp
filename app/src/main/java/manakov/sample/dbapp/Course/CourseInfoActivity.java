package manakov.sample.dbapp.Course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.Student.Student;
import manakov.sample.dbapp.Student.StudentInfoActivity;
import manakov.sample.dbapp.Student.StudentRecyclerViewAdapter;
import manakov.sample.dbapp.Teacher.Teacher;
import manakov.sample.dbapp.Teacher.TeacherInfoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseInfoActivity extends AppCompatActivity {
    private DbApplication application;

    private int courseId;

    private TextView courseInfoNameTextView;
    private TextView courseInfoLangLvlLangTextView;
    private TextView courseInfoLangLvlProfTextView;
    private TextView courseInfoTeacherFirstNameTextView;
    private TextView courseInfoTeacherLastNameTextView;

    private RecyclerView recyclerView;
    private StudentRecyclerViewAdapter adapter;
    private ArrayList<Student> list;

    private View.OnClickListener onClickListener;
    private View.OnClickListener onTeacherClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        application = (DbApplication) this.getApplication();

        Intent intent = getIntent();
        courseId = intent.getExtras().getInt("courseId");

        courseInfoNameTextView = findViewById(R.id.courseInfoNameTextView);
        courseInfoLangLvlLangTextView = findViewById(R.id.courseInfoLangLvlLangView);
        courseInfoLangLvlProfTextView = findViewById(R.id.courseInfoLangLvlProfView);

        courseInfoTeacherFirstNameTextView = findViewById(R.id.courseInfoTeacherFirstNameTextView);
        courseInfoTeacherLastNameTextView = findViewById(R.id.courseInfoTeacherLastNameTextView);

        courseInfoNameTextView.setVisibility(View.VISIBLE);
        courseInfoLangLvlLangTextView.setVisibility(View.VISIBLE);
        courseInfoLangLvlProfTextView.setVisibility(View.VISIBLE);

        courseInfoTeacherFirstNameTextView.setVisibility(View.VISIBLE);
        courseInfoTeacherLastNameTextView.setVisibility(View.VISIBLE);

        Course course = application.database.courseDao().getCourseById(courseId);

        courseInfoNameTextView.setText(course.getCourseName());
        CourseLanguageProficiency courseLanguageProficiency = application.database.courseLanguageProficiencyDao().getCourseLanguageProficiencyByCourseId(courseId);

        courseInfoLangLvlLangTextView.setText(
                application.database.languageDao().getLanguageById(courseLanguageProficiency.getLanguageId()).getLanguage()
        );
        courseInfoLangLvlProfTextView.setText(
                application.database.proficiencyDao().getProficiencyById(courseLanguageProficiency.getProficiencyId()).getProficiency()
        );

        try {
        final Teacher teacher = application.database.teacherDao().getTeacherById(
                application.database.courseTeacherDao().getCourseTeacherByCourseId(courseId).getTeacherId()
        );


            courseInfoTeacherFirstNameTextView.setText(teacher.getFirstName());
            courseInfoTeacherLastNameTextView.setText(teacher.getLastName());


        onTeacherClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TeacherInfoActivity.class);
                intent.putExtra("teacherId", teacher.getId());
                startActivityForResult(intent, DbApplication.teacherInfoTag);
            }
        };

        courseInfoTeacherFirstNameTextView.setOnClickListener(onTeacherClickListener);
        courseInfoTeacherLastNameTextView.setOnClickListener(onTeacherClickListener);

        } catch (Exception e){
            //
        }

        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        courseStudents.addAll(
                application.database.courseStudentDao().getCourseStudentsByCourseId(courseId)
        );

        list = new ArrayList<>();
        for (int i =0; i< courseStudents.size(); i++){
            list.add(
                    application.database.studentDao().getStudentById(
                            courseStudents.get(i).getStudentId()
                    )
            );
        }

        recyclerView = findViewById(R.id.courseInfoStudentsView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), StudentInfoActivity.class);
                intent.putExtra("studentId", list.get(position).getId());
                startActivityForResult(intent, DbApplication.studentInfoTag);
            }
        };

        adapter = new StudentRecyclerViewAdapter(list);
        adapter.setOnItemClickListener(onClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            ArrayList<CourseStudent> courseStudents = new ArrayList<>();
            courseStudents.addAll(
                    application.database.courseStudentDao().getCourseStudentsByCourseId(courseId)
            );

            list.clear();
            for (int i =0; i< courseStudents.size(); i++){
                list.add(
                        application.database.studentDao().getStudentById(
                                courseStudents.get(i).getStudentId()
                        )
                );
            }
            adapter.notifyDataSetChanged();
        }
    }
}
