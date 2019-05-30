package manakov.sample.dbapp.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Course.CourseTeacher;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class AddTeacherCourseActivity extends AppCompatActivity {

    public Teacher teacher;
    private DbApplication application;
    private int courseId;

    private RecyclerView recyclerView;
    private TeacherRecyclerViewAdapter adapter;
    private ArrayList<Teacher> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_course);

        application = (DbApplication) getApplication();

        Intent intent = getIntent();
        courseId = intent.getExtras().getInt("courseId");

        ArrayList<Teacher> addList = new ArrayList<>();
        addList.addAll(
                application.database.teacherDao().getAll()
        );

        teacher = null;
        try {
            teacher =
                    application.database.teacherDao().getTeacherById(
                            application.database.courseTeacherDao().getCourseTeacherByCourseId(courseId).getTeacherId()
                    );
        } catch (Exception e) {}

        if (teacher != null) {
            list = substract(addList, teacher);
        } else list = addList;

        recyclerView = findViewById(R.id.teacherCourseRecyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();

                if (teacher != null){
                    application.database.courseTeacherDao().deleteCourseTeacherByCourseId(courseId);
                }

                Teacher teacher1 = list.get(position);
                application.database.courseTeacherDao().insertAll(
                        new CourseTeacher(
                                courseId,
                                teacher1.getId()
                        )
                );
                setResult(RESULT_OK);
                finish();
            }
        };

        adapter = new TeacherRecyclerViewAdapter(list);
        adapter.setOnClickListener(onClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }

    public ArrayList<Teacher> substract(ArrayList<Teacher> list1, Teacher teacher){
        ArrayList<Teacher> result = new ArrayList<>();
        for(int i = 0; i< list1.size(); i++){
            if (list1.get(i).getId() != teacher.getId()){
                result.add(list1.get(i));
            }
        }
        return result;
    }
}
