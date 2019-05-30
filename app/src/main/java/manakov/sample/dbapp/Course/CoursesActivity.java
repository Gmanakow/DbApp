package manakov.sample.dbapp.Course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Course.Course;
import manakov.sample.dbapp.Course.CoursesRecyclerViewAdapter;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.Teacher.AddTeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    private DbApplication application;

    private RecyclerView recyclerView;
    private CoursesRecyclerViewAdapter adapter;
    private ArrayList<Course> list;

    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        application =(DbApplication) getApplication();

        list = new ArrayList<>();
        list.addAll(
                application.database.courseDao().getAll()
        );

        recyclerView = findViewById(R.id.coursesRecyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), CourseInfoActivity.class);
                intent.putExtra("courseId", list.get(position).getId());
                startActivityForResult(intent, DbApplication.courseInfoTag);
            }
        };

        adapter = new CoursesRecyclerViewAdapter(list);
        adapter.setOnClickListener(onClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onAddCourseClick(View view){
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivityForResult(intent, DbApplication.addCourseTag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            list.clear();
            list.addAll(
                    application
                            .database
                            .courseDao()
                            .getAll()
            );
            adapter.notifyDataSetChanged();
        }
    }


}
