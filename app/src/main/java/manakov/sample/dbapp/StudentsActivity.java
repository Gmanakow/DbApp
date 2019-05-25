package manakov.sample.dbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Student.Student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity {
    private DbApplication application;

    private RecyclerView recyclerView;
    private StudentRecyclerViewAdapter adapter;
    private ArrayList<Student> list;

    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        application =(DbApplication) getApplication();

        list = new ArrayList<>();
        list = application.getStudents();

        recyclerView = findViewById(R.id.studentRecyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), StudentInfoActivity.class);
                intent.putExtra("studentId", list.get(position).getId());
                startActivity(intent);
            }
        };


        adapter = new StudentRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);
        adapter.notifyDataSetChanged();


    }
}
