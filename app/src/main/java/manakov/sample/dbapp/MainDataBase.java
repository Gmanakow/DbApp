package manakov.sample.dbapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import manakov.sample.dbapp.Course.Course;
import manakov.sample.dbapp.Course.CourseDao;
import manakov.sample.dbapp.Course.CourseLanguageProficiency;
import manakov.sample.dbapp.Course.CourseLanguageProficiencyDao;
import manakov.sample.dbapp.Course.CourseStudent;
import manakov.sample.dbapp.Course.CourseStudentDao;
import manakov.sample.dbapp.Course.CourseTeacher;
import manakov.sample.dbapp.Course.CourseTeacherDao;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.Language.LanguageDao;
import manakov.sample.dbapp.PaymentForm.PaymentForm;
import manakov.sample.dbapp.PaymentForm.PaymentFormDao;
import manakov.sample.dbapp.Proficiency.Proficiency;
import manakov.sample.dbapp.Proficiency.ProficiencyDao;
import manakov.sample.dbapp.Student.Student;
import manakov.sample.dbapp.Student.StudentDao;
import manakov.sample.dbapp.Student.StudentLanguageProficiency;
import manakov.sample.dbapp.Student.StudentLanguageProficiencyDao;
import manakov.sample.dbapp.Student.StudentPaymentForm;
import manakov.sample.dbapp.Student.StudentPaymentFormDao;
import manakov.sample.dbapp.PaymentForm.Payment;
import manakov.sample.dbapp.PaymentForm.PaymentDao;
import manakov.sample.dbapp.Teacher.Teacher;
import manakov.sample.dbapp.Teacher.TeacherDao;
import manakov.sample.dbapp.Teacher.TeacherLanguageProficiency;
import manakov.sample.dbapp.Teacher.TeacherLanguageProficiencyDao;

@Database(
        entities = {
            Student.class,
            Teacher.class,
            Language.class,
            Proficiency.class,
            Course.class,
            StudentLanguageProficiency.class,
            TeacherLanguageProficiency.class,
            CourseLanguageProficiency.class,
            CourseTeacher.class,
            CourseStudent.class,
            PaymentForm.class,
            StudentPaymentForm.class,
            Payment.class
        },
        version = 18,
        exportSchema = false
)
public abstract class MainDataBase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract TeacherDao teacherDao();
    public abstract LanguageDao languageDao();
    public abstract ProficiencyDao proficiencyDao();
    public abstract CourseDao courseDao();
    public abstract StudentLanguageProficiencyDao studentLanguageProficiencyDao();
    public abstract TeacherLanguageProficiencyDao teacherLanguageProficiencyDao();
    public abstract CourseLanguageProficiencyDao courseLanguageProficiencyDao();
    public abstract CourseStudentDao courseStudentDao();
    public abstract CourseTeacherDao courseTeacherDao();
    public abstract PaymentFormDao paymentFormDao();
    public abstract StudentPaymentFormDao studentPaymentFormDao();
    public abstract PaymentDao paymentDao();
}
