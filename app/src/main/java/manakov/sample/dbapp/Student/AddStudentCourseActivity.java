package manakov.sample.dbapp.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Course.CourseStudent;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.Student.Student;
import manakov.sample.dbapp.Student.StudentInfoActivity;
import manakov.sample.dbapp.Student.StudentRecyclerViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class AddStudentCourseActivity extends AppCompatActivity {

    private DbApplication application;
    private int courseId;

    private RecyclerView recyclerView;
    private StudentRecyclerViewAdapter adapter;
    private ArrayList<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_course);

        application = (DbApplication) getApplication();

        Intent intent = getIntent();
        courseId = intent.getExtras().getInt("courseId");


        ArrayList<Student> addList = new ArrayList<>();
        addList.addAll(
                application.database.studentDao().getAll()
        );

        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        courseStudents.addAll(
                application.database.courseStudentDao().getCourseStudentsByCourseId(courseId)
        );

        ArrayList<Student> addList2 = new ArrayList<>();
        for (int i =0; i< courseStudents.size(); i++){
            addList2.add(
                    application.database.studentDao().getStudentById(
                            courseStudents.get(i).getStudentId()
                    )
            );
        }

        list = substractById(addList, addList2);

        recyclerView = findViewById(R.id.studentCourseRecyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();

                Student student = list.get(position);
                application.database.courseStudentDao().insertAll(
                        new CourseStudent(
                                courseId,
                                student.getId()
                        )
                );
                setResult(RESULT_OK);
                finish();
            }
        };

        adapter = new StudentRecyclerViewAdapter(list);
        adapter.setOnItemClickListener(onClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<Student> substractById(ArrayList<Student> list1, ArrayList<Student> list2){
        ArrayList<Student> result = new ArrayList<>();
        for(int i = 0; i< list1.size(); i++){
            boolean check = false;
            for (int j = 0; j< list2.size(); j++){
                if (list1.get(i).getId() == list2.get(j).getId())
                {
                    check = true;
                }
            }
            if (!check) {
                result.add(list1.get(i));
            }
        }
        return result;
    }
}
