package manakov.sample.dbapp.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.Student.AddStudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class TeachersActivity extends AppCompatActivity {
    private DbApplication application;

    private RecyclerView recyclerView;
    private TeacherRecyclerViewAdapter adapter;
    private ArrayList<Teacher> list;

    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        application =(DbApplication) getApplication();

        list = new ArrayList<>();
        list.addAll(
                application.database.teacherDao().getAll()
        );

        recyclerView = findViewById(R.id.teacherRecyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), TeacherInfoActivity.class);
                intent.putExtra("teacherId", list.get(position).getId());
                startActivityForResult(intent, DbApplication.teacherInfoTag);
            }
        };

        adapter = new TeacherRecyclerViewAdapter(list);
        adapter.setOnClickListener(onClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onAddTeacherClick(View view){
        Intent intent = new Intent(this, AddTeacherActivity.class);
        startActivityForResult(intent, DbApplication.addTeacherTag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            list.clear();
            list.addAll(
                    application
                            .database
                            .teacherDao()
                            .getAll()
            );
            adapter.notifyDataSetChanged();
        }
    }


}
