package manakov.sample.dbapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DbApplication application;

    private TextView studentTextView;
    private TextView teacherTextView;
    private TextView  courseTextView;

    private View.OnClickListener studentOnClickListener;
    private View.OnClickListener teacherOnClickListener;
    private View.OnClickListener  courseOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = (DbApplication) getApplication();

        studentTextView = findViewById(R.id.studentTextView);
        teacherTextView = findViewById(R.id.teacherTextView);
         courseTextView = findViewById(R.id. courseTextView);

        studentTextView.setVisibility(View.VISIBLE);
        teacherTextView.setVisibility(View.VISIBLE);
         courseTextView.setVisibility(View.VISIBLE);

//        studentTextView;

        studentOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tag", "click");
                Intent intent = new Intent(getBaseContext(), StudentsActivity.class);
                startActivity(intent);
            }
        };
        studentTextView.setOnClickListener(studentOnClickListener);

    }

    public void onClick(View view){
        application.fillStudents();
    }
}
