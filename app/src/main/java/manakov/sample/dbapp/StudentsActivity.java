package manakov.sample.dbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Student.Student;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity {
    private DbApplication application;

    private RecyclerView recyclerView;
    private StudentRecyclerViewAdapter adapter;
    private ArrayList<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        application =(DbApplication) getApplication();

        list = new ArrayList<>();
        list = application.getStudents();

        recyclerView = findViewById(R.id.studentRecyclerView);
        recyclerView.destroyDrawingCache();
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StudentRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
