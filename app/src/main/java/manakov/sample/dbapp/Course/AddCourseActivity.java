package manakov.sample.dbapp.Course;

import androidx.appcompat.app.AppCompatActivity;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.Language.SpinnerLanguageAdapter;
import manakov.sample.dbapp.Proficiency.Proficiency;
import manakov.sample.dbapp.Proficiency.SpinnerProficiencyAdapter;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.Student.Student;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddCourseActivity extends AppCompatActivity {

    private DbApplication application;

    private EditText addCourseNameInputTextView;

    private Spinner addCourseLanguageSpinner;
    private Spinner addCourseProficiencySpinner;

    private ArrayList<Language> languages;
    private SpinnerLanguageAdapter spinnerLanguageAdapter;

    private ArrayList<Proficiency> proficiencies;
    private SpinnerProficiencyAdapter spinnerProficiencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        application = (DbApplication) getApplication();

        addCourseNameInputTextView = findViewById(R.id.addCourseNameInputTextView);

        addCourseLanguageSpinner = findViewById(R.id.addCourseLanguageSpinner);
        addCourseProficiencySpinner = findViewById(R.id.addCourseProficiencySpinner);

        languages = new ArrayList<>();
        languages.addAll(
                application.database.languageDao().getAll()
        );
        spinnerLanguageAdapter = new SpinnerLanguageAdapter(this, languages);
        addCourseLanguageSpinner.setAdapter(spinnerLanguageAdapter);

        proficiencies = new ArrayList<>();
        proficiencies.addAll(
                application.database.proficiencyDao().getAll()
        );

        spinnerProficiencyAdapter = new SpinnerProficiencyAdapter(this, proficiencies);
        addCourseProficiencySpinner.setAdapter(spinnerProficiencyAdapter);
    }

    public void onAddCourseClick(View view){
        Course course = new Course(
                addCourseNameInputTextView.getText().toString()
        );

        ArrayList<Course> courses = new ArrayList<>();
        courses.addAll(
                application.database.courseDao().getAll()
        );

        application.database.courseDao().insertAll(course);

        ArrayList<Course> courses1 = new ArrayList<>();
        courses1.addAll(
                application.database.courseDao().getAll()
        );

        for (int i = 0; i< courses1.size(); i++){
            if (check(courses1.get(i), courses) != null){
                course = courses1.get(i);
                break;
            }
        }

        CourseLanguageProficiency courseLanguageProficiency = new CourseLanguageProficiency(
                course.getId(),
                languages.get(
                        addCourseLanguageSpinner.getSelectedItemPosition()
                ).getId(),
                proficiencies.get(
                        addCourseProficiencySpinner.getSelectedItemPosition()
                ).getId()
        );

        application.database.courseLanguageProficiencyDao().insertAll(courseLanguageProficiency);

        setResult(RESULT_OK);
        finish();
    }

    public Course check(Course course, ArrayList<Course> list){
        for (int i= 0; i< list.size(); i++){
            if (list.get(i).getId() == course.getId()){
                return null;
            }
        }
        return course;
    }
}
