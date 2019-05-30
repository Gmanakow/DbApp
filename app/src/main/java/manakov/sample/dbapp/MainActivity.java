package manakov.sample.dbapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import manakov.sample.dbapp.Course.CoursesActivity;
import manakov.sample.dbapp.Student.StudentsActivity;
import manakov.sample.dbapp.Teacher.TeachersActivity;

public class MainActivity extends AppCompatActivity {
    private DbApplication application;

    private TextView studentTextView;
    private TextView teacherTextView;
    private TextView  courseTextView;
    private TextView paymentsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = (DbApplication) getApplication();

        studentTextView = findViewById(R.id.studentTextView);
        teacherTextView = findViewById(R.id.teacherTextView);
         courseTextView = findViewById(R.id. courseTextView);
         paymentsTextView = findViewById(R.id.paymentsTextView);

        studentTextView.setVisibility(View.VISIBLE);
        teacherTextView.setVisibility(View.VISIBLE);
         courseTextView.setVisibility(View.VISIBLE);
         paymentsTextView.setVisibility(View.VISIBLE);

        studentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), StudentsActivity.class);
                startActivity(intent);
            }
        });

        teacherTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TeachersActivity.class);
                startActivity(intent);
            }
        });

        courseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CoursesActivity.class);
                startActivity(intent);
            }
        });

        paymentsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PaymentsActivity.class);
                startActivity(intent);
            }
        });


    }

    public void onClick(View view){
        application.fillDB();
    }

}
