package manakov.sample.dbapp.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;

import android.content.Intent;
import android.os.Bundle;
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
        list.addAll(
            application.database.studentDao().getAll()
        );

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
                startActivityForResult(intent, DbApplication.studentInfoTag);
            }
        };

        adapter = new StudentRecyclerViewAdapter(list);
        adapter.setOnItemClickListener(onClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onAddStudentClick(View view){
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivityForResult(intent, DbApplication.addStudentTag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            list.clear();
            list.addAll(
                    application
                            .database
                            .studentDao()
                            .getAll()
            );
            adapter.notifyDataSetChanged();
        }
    }

}
