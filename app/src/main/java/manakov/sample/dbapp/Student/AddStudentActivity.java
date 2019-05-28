package manakov.sample.dbapp.Student;

import androidx.appcompat.app.AppCompatActivity;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.PaymentForm.PaymentForm;
import manakov.sample.dbapp.Proficiency.Proficiency;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.Language.SpinnerLanguageAdapter;
import manakov.sample.dbapp.PaymentForm.SpinnerPaymentFormAdapter;
import manakov.sample.dbapp.Proficiency.SpinnerProficiencyAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    private DbApplication application;

    private EditText addStudentFirstNameInputTextView;
    private EditText addStudentLastNameInputTextView;
    private Spinner addStudentLanguageSpinner;
    private Spinner addStudentProficiencySpinner;
    private Spinner addStudentPaymentFormSpinner;

    private ArrayList<Language> languages;
    private SpinnerLanguageAdapter spinnerLanguageAdapter;

    private ArrayList<Proficiency> proficiencies;
    private SpinnerProficiencyAdapter spinnerProficiencyAdapter;

    private ArrayList<PaymentForm> paymentForms;
    private SpinnerPaymentFormAdapter spinnerPaymentFormAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        application = (DbApplication) getApplication();

        addStudentFirstNameInputTextView = findViewById(R.id.addStudentFirstNameInputTextView);
        addStudentLastNameInputTextView = findViewById(R.id.addStudentLastNameInputTextView);

        addStudentLanguageSpinner = findViewById(R.id.addStudentLanguageSpinner);
        addStudentProficiencySpinner = findViewById(R.id.addStudentProficiencySpinner);
        addStudentPaymentFormSpinner = findViewById(R.id.addStudentPaymentFormSpinner);

        languages = new ArrayList<>();
        languages.addAll(
                application.database.languageDao().getAll()
        );
        spinnerLanguageAdapter = new SpinnerLanguageAdapter(this, languages);
        addStudentLanguageSpinner.setAdapter(spinnerLanguageAdapter);

        proficiencies = new ArrayList<>();
        proficiencies.addAll(
                application.database.proficiencyDao().getAll()
        );

        spinnerProficiencyAdapter = new SpinnerProficiencyAdapter(this, proficiencies);
        addStudentProficiencySpinner.setAdapter(spinnerProficiencyAdapter);

        paymentForms = new ArrayList<>();
        paymentForms.addAll(
                application.database.paymentFormDao().getAll()
        );

        spinnerPaymentFormAdapter = new SpinnerPaymentFormAdapter(this, paymentForms);
        addStudentPaymentFormSpinner.setAdapter(spinnerPaymentFormAdapter);
    }

    public void onAddStudentClick(View view){
        Student student = new Student(
                addStudentFirstNameInputTextView.getText().toString(),
                addStudentLastNameInputTextView.getText().toString()
        );


        ArrayList<Student> students = new ArrayList<>();
        students.addAll(
                application.database.studentDao().getAll()
        );

        application.database.studentDao().insertAll(student);

        ArrayList<Student> students1 = new ArrayList<>();
        students1.addAll(
                application.database.studentDao().getAll()
        );

        for (int i = 0; i< students1.size(); i++){
            if (check(students1.get(i), students) != null){
                student = students1.get(i);
                break;
            }
        }

        StudentLanguageProficiency languageProficiency = new StudentLanguageProficiency(
                student.getId(),
                languages.get(
                        addStudentLanguageSpinner.getSelectedItemPosition()
                ).getId(),
                proficiencies.get(
                        addStudentProficiencySpinner.getSelectedItemPosition()
                ).getId()
        );

        StudentPaymentForm studentPaymentForm =  new StudentPaymentForm(
                student.getId(),
                paymentForms.get(
                        addStudentPaymentFormSpinner.getSelectedItemPosition()
                ).getId()
        );


        application.database.studentLanguageProficiencyDao().insertAll(languageProficiency);
        application.database.studentPaymentFormDao().insertAll(studentPaymentForm);

        setResult(RESULT_OK);
        finish();
    }

    public Student check(Student student, ArrayList<Student> list){
        for (int i= 0; i< list.size(); i++){
            if (list.get(i).getId() == student.getId()){
                return null;
            }
        }
        return student;
    }
}
