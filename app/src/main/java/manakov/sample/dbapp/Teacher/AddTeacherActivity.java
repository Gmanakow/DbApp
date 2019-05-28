package manakov.sample.dbapp.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.Language.SpinnerLanguageAdapter;
import manakov.sample.dbapp.PaymentForm.PaymentForm;
import manakov.sample.dbapp.PaymentForm.SpinnerPaymentFormAdapter;
import manakov.sample.dbapp.Proficiency.Proficiency;
import manakov.sample.dbapp.Proficiency.SpinnerProficiencyAdapter;
import manakov.sample.dbapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddTeacherActivity extends AppCompatActivity {
    private DbApplication application;

    private EditText addTeacherFirstNameInputTextView;
    private EditText addTeacherLastNameInputTextView;

    private Spinner addTeacherLanguageSpinner;
    private Spinner addTeacherProficiencySpinner;

    private ArrayList<Language> languages;
    private SpinnerLanguageAdapter spinnerLanguageAdapter;

    private ArrayList<Proficiency> proficiencies;
    private SpinnerProficiencyAdapter spinnerProficiencyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        application = (DbApplication) getApplication();

        addTeacherFirstNameInputTextView = findViewById(R.id.addTeacherFirstNameInputTextView);
        addTeacherLastNameInputTextView = findViewById(R.id.addTeacherLastNameInputTextView);

        addTeacherLanguageSpinner = findViewById(R.id.addTeacherLanguageSpinner);
        addTeacherProficiencySpinner = findViewById(R.id.addTeacherProficiencySpinner);

        languages = new ArrayList<>();
        languages.addAll(
                application.database.languageDao().getAll()
        );
        spinnerLanguageAdapter = new SpinnerLanguageAdapter(this, languages);
        addTeacherLanguageSpinner.setAdapter(spinnerLanguageAdapter);

        proficiencies = new ArrayList<>();
        proficiencies.addAll(
                application.database.proficiencyDao().getAll()
        );

        spinnerProficiencyAdapter = new SpinnerProficiencyAdapter(this, proficiencies);
        addTeacherProficiencySpinner.setAdapter(spinnerProficiencyAdapter);
    }

    public void onAddTeacherClick(View view){
        Teacher teacher = new Teacher(
                addTeacherFirstNameInputTextView.getText().toString(),
                addTeacherLastNameInputTextView.getText().toString()
        );


        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.addAll(
                application.database.teacherDao().getAll()
        );

        application.database.teacherDao().insertAll(teacher);

        ArrayList<Teacher> teachers1 = new ArrayList<>();
        teachers1.addAll(
                application.database.teacherDao().getAll()
        );

        for (int i = 0; i< teachers1.size(); i++){
            if (check(teachers1.get(i), teachers) != null){
                teacher = teachers1.get(i);
                break;
            }
        }

        TeacherLanguageProficiency languageProficiency = new TeacherLanguageProficiency(
                teacher.getId(),
                languages.get(
                        addTeacherLanguageSpinner.getSelectedItemPosition()
                ).getId(),
                proficiencies.get(
                        addTeacherProficiencySpinner.getSelectedItemPosition()
                ).getId()
        );

        application.database.teacherLanguageProficiencyDao().insertAll(languageProficiency);

        setResult(RESULT_OK);
        finish();
    }

    public Teacher check(Teacher teacher, ArrayList<Teacher> list){
        for (int i= 0; i< list.size(); i++){
            if (list.get(i).getId() == teacher.getId()){
                return null;
            }
        }
        return teacher;
    }
}
