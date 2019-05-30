package manakov.sample.dbapp;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import manakov.sample.dbapp.Course.Course;
import manakov.sample.dbapp.Course.CourseLanguageProficiency;
import manakov.sample.dbapp.Course.CourseStudent;
import manakov.sample.dbapp.Course.CourseTeacher;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.PaymentForm.PaymentForm;
import manakov.sample.dbapp.Proficiency.Proficiency;
import manakov.sample.dbapp.Student.Student;
import manakov.sample.dbapp.Student.StudentLanguageProficiency;
import manakov.sample.dbapp.Student.StudentPaymentForm;
import manakov.sample.dbapp.PaymentForm.Payment;
import manakov.sample.dbapp.Teacher.Teacher;
import manakov.sample.dbapp.Teacher.TeacherLanguageProficiency;

public class DbApplication extends Application{

    public MainDataBase database;

    public static final int addStudentTag = 1;
    public static final int studentInfoTag = 2;
    public static final int addTeacherTag = 3;
    public static final int teacherInfoTag = 4;
    public static final int addCourseTag = 5;
    public static final int courseInfoTag = 6;
    public static final int courseAddDataTag = 7;

    RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            String name;

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            db.execSQL("drop trigger if exists addPaymentTrigger");
            db.execSQL("drop trigger if exists  addStudentCourseTrigger");

            db.execSQL(
                    "create trigger if not exists addPaymentTrigger after insert on payment"+
                            " begin "+
                            " delete from payment where studentId in " +
                            "(" +
                            "select studentId from student_paymentForm where paymentFormId = 2" +
                            ");"+
                            " end "
            );

            db.execSQL(
                    "create trigger if not exists addStudentCourseTrigger after insert on course_student" +
                            " begin" +
                            " delete from course_student where courseId in " +
                            "(" +
                                "select courseId from " +

                                "(" +
                                " select courseId, studentId, courseProf, studentProf from" +

                                "(" +
                                    "Select course_language_proficiency.courseId as courseId," +
                                            "student_language_proficiency.studentId as studentId, " +
                                                " course_language_proficiency.proficiencyId as courseProf," +
                                                        " student_language_proficiency.proficiencyId as studentProf " +
                                                             " from course_language_proficiency join student_language_proficiency" +
                                                                " on course_language_proficiency.languageId = student_language_proficiency.languageId" +
                                ")" +
                                "where courseId = New.courseId and studentId = New.studentId and studentProf != (courseProf - 1)  " +

                                ")" +
                            ")" +
                            "and studentId in " +
                            "(" +
                                "select studentId from " +

                                "(" +
                                " select courseId, studentId, courseProf, studentProf from" +

                                "(" +
                                    "Select course_language_proficiency.courseId as courseId," +
                                            "student_language_proficiency.studentId as studentId, " +
                                                " course_language_proficiency.proficiencyId as courseProf," +
                                                    " student_language_proficiency.proficiencyId as studentProf " +
                                                        " from course_language_proficiency join student_language_proficiency" +
                                                            " on course_language_proficiency.languageId = student_language_proficiency.languageId" +
                                ")" +
                                "where courseId = New.courseId and studentId = New.studentId and studentProf != (courseProf - 1) " +

                                ")" +
                            ");" +
                            " end"
            );
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();



        database = Room.databaseBuilder(
                getApplicationContext(),
                MainDataBase.class,
                "Main Database"
        )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addCallback(callback)
                .build();

    }

    public ArrayList<Language> getLanguages(){
        return (ArrayList<Language>)
                database
                .languageDao()
                .getAll();
    }
    public void fillLanguages(){
        database
                .languageDao()
                .clear();

        database
                .languageDao()
                .insertAll(
                        new Language("Английский"),
                        new Language("Французский"),
                        new Language("Немецкий")
                );

    }

    public ArrayList<Proficiency> getProficiency(){
        return (ArrayList<Proficiency>)
                database
                .proficiencyDao()
                .getAll();
    }
    public void fillProficiencies(){
        database
                .proficiencyDao()
                .clear();

        database
                .proficiencyDao()
                .insertAll(
                        new Proficiency("Начальный"    ,1, 1000, 1500),
                        new Proficiency("Промежуточный",2, 1500, 2000),
                        new Proficiency("Продвинутый"  ,3, 2000, 2500),
                        new Proficiency("Превосходный" ,4, 2500, 3000)
                );
    }

    public ArrayList<Student> getStudents(){
        return  (ArrayList<Student>)
                database
                .studentDao()
                .getAll();
    }
    public void fillStudents(){
        database
                .studentDao()
                .clear();

        database
                .studentDao()
                .insertAll(
                        new Student(
                                "Афанасий",
                                "Мордвинов"
                        ),
                        new Student(
                                "Валерий",
                                "Бочкарев"
                        ),
                        new Student(
                                "Михей",
                                "Цирюльников"
                        ),
                        new Student(
                                "Измаил",
                                "Трошкин"
                        ),
                        new Student(
                                "Пимен",
                                "Ульянов"
                        ),
                        new Student(
                                "Елисей",
                                "Алистратов"
                        ),
                        new Student(
                                "Михаил",
                                "Тизенгаузен"
                        ),
                        new Student(
                                "Всеволод",
                                "Калашников"
                        )
                );
    }

    public ArrayList<Teacher> getTeacher(){
        return (ArrayList<Teacher>)
                database
                .teacherDao()
                .getAll();
    }
    public void fillTeachers(){
        database
                .teacherDao()
                .clear();

        database
                .teacherDao()
                .insertAll(
                        new Teacher(
                                "Давид",
                                "Шимякин"
                        ),
                        new Teacher(
                                "Евсей",
                                "Лобан"
                        ),
                        new Teacher(
                                "Кондратий",
                                "Гремпель"
                        ),
                        new Teacher(
                                "Владимир",
                                "Сапалёв"
                        )

                );
    }

    public void fillStudentLanguageProficiency() {
        database
                .studentLanguageProficiencyDao()
                .clear();

        database
                .studentLanguageProficiencyDao()
                .insertAll(
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(0).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(0).getId()
                        ),
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(1).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(0).getId()
                        ),
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(2).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        ),
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(3).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        ),
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(4).getId(),
                                database.languageDao().getAll().get(1).getId(),
                                database.proficiencyDao().getAll().get(0).getId()
                        ),
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(5).getId(),
                                database.languageDao().getAll().get(1).getId(),
                                database.proficiencyDao().getAll().get(0).getId()
                        ),
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(6).getId(),
                                database.languageDao().getAll().get(2).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        ),
                        new StudentLanguageProficiency(
                                database.studentDao().getAll().get(7).getId(),
                                database.languageDao().getAll().get(2).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        )
                );

    }
    public void fillTeacherLanguageProficiency(){
        database
                .teacherLanguageProficiencyDao()
                .clear();

        database
                .teacherLanguageProficiencyDao()
                .insertAll(
                        new TeacherLanguageProficiency(
                                database.teacherDao().getAll().get(0).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        ),
                        new TeacherLanguageProficiency(
                                database.teacherDao().getAll().get(1).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(2).getId()
                        ),
                        new TeacherLanguageProficiency(
                                database.teacherDao().getAll().get(2).getId(),
                                database.languageDao().getAll().get(1).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        ),
                        new TeacherLanguageProficiency(
                                database.teacherDao().getAll().get(3).getId(),
                                database.languageDao().getAll().get(2).getId(),
                                database.proficiencyDao().getAll().get(2).getId()
                        )
                );
    }

    public void fillCourses(){
        database
                .courseDao()
                .clear();
        database
                .courseDao()
                .insertAll(
                        new Course("Курс Английского, промежуточный уровень "),
                        new Course("Курс Английского, продвинутый уровень"),
                        new Course("Курс Французского, промежуточный уровень"),
                        new Course("Курс Немецкого, продвинутый уровень")
                );
    }
    public void fillCoursesLanguageProficiencyLevel(){
        database
                .courseLanguageProficiencyDao()
                .clear();
        database
                .courseLanguageProficiencyDao()
                .insertAll(
                        new CourseLanguageProficiency(
                                database.courseDao().getAll().get(0).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        ),
                        new CourseLanguageProficiency(
                                database.courseDao().getAll().get(1).getId(),
                                database.languageDao().getAll().get(0).getId(),
                                database.proficiencyDao().getAll().get(2).getId()
                        ),
                        new CourseLanguageProficiency(
                                database.courseDao().getAll().get(2).getId(),
                                database.languageDao().getAll().get(1).getId(),
                                database.proficiencyDao().getAll().get(1).getId()
                        ),
                        new CourseLanguageProficiency(
                                database.courseDao().getAll().get(3).getId(),
                                database.languageDao().getAll().get(2).getId(),
                                database.proficiencyDao().getAll().get(2).getId()
                        )

                );
    }
    public void fillCourseTeacher(){
        database
                .courseTeacherDao()
                .clear();

        database
                .courseTeacherDao()
                .insertAll(
                        new CourseTeacher(
                                database.courseDao().getAll().get(0).getId(),
                                database.teacherDao().getAll().get(0).getId()
                        ),
                        new CourseTeacher(
                                database.courseDao().getAll().get(1).getId(),
                                database.teacherDao().getAll().get(1).getId()
                        ),
                        new CourseTeacher(
                                database.courseDao().getAll().get(2).getId(),
                                database.teacherDao().getAll().get(2).getId()
                        ),
                        new CourseTeacher(
                                database.courseDao().getAll().get(3).getId(),
                                database.teacherDao().getAll().get(3).getId()
                        )


                );
    }
    public void fillCourseStudent(){
        database
                .courseStudentDao()
                .clear();

        database.courseStudentDao()
                .insertAll(
                        new CourseStudent(
                                database.courseDao().getAll().get(0).getId(),
                                database.studentDao().getAll().get(0).getId()
                        ),
                        new CourseStudent(
                                database.courseDao().getAll().get(0).getId(),
                                database.studentDao().getAll().get(1).getId()
                        ),
                        new CourseStudent(
                                database.courseDao().getAll().get(1).getId(),
                                database.studentDao().getAll().get(2).getId()
                        ),
                        new CourseStudent(
                                database.courseDao().getAll().get(1).getId(),
                                database.studentDao().getAll().get(3).getId()
                        ),
                        new CourseStudent(
                                database.courseDao().getAll().get(2).getId(),
                                database.studentDao().getAll().get(4).getId()
                        ),
                        new CourseStudent(
                                database.courseDao().getAll().get(2).getId(),
                                database.studentDao().getAll().get(5).getId()
                        ),
                        new CourseStudent(
                                database.courseDao().getAll().get(3).getId(),
                                database.studentDao().getAll().get(6).getId()
                        ),
                        new CourseStudent(
                                database.courseDao().getAll().get(3).getId(),
                                database.studentDao().getAll().get(7).getId()
                        )
                );

    }

    public void fillPaymentForm(){
        database
                .paymentFormDao()
                .clear();

        database
                .paymentFormDao()
                .insertAll(
                        new PaymentForm("Платная форма обучения"),
                        new PaymentForm("Бесплатная форма обучения")
                );
    }
    public void fillStudentPaymentForm(){
        database
                .studentPaymentFormDao()
                .clear();

        database
                .studentPaymentFormDao()
                .insertAll(
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(0).getId(),
                                database.paymentFormDao().getAll().get(0).getId()
                        ),
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(1).getId(),
                                database.paymentFormDao().getAll().get(0).getId()
                        ),
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(2).getId(),
                                database.paymentFormDao().getAll().get(1).getId()
                        ),
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(3).getId(),
                                database.paymentFormDao().getAll().get(0).getId()
                        ),
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(4).getId(),
                                database.paymentFormDao().getAll().get(0).getId()
                        ),
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(5).getId(),
                                database.paymentFormDao().getAll().get(1).getId()
                        ),
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(6).getId(),
                                database.paymentFormDao().getAll().get(0).getId()
                        ),
                        new StudentPaymentForm(
                                database.studentDao().getAll().get(7).getId(),
                                database.paymentFormDao().getAll().get(0).getId()
                        )
                );
    }
    public void fillPayment(){
        database
                .paymentDao()
                .clear();
        database
                .paymentDao()
                .insertAll(
                        new Payment(
                                database.studentDao().getAll().get(0).getId(),
                                database.courseDao().getAll().get(0).getId(),
                                1500,
                                1
                        ),
                        new Payment(
                                database.studentDao().getAll().get(1).getId(),
                                database.courseDao().getAll().get(0).getId(),
                                500,
                                2
                        ),
                        new Payment(
                                database.studentDao().getAll().get(2).getId(),
                                database.courseDao().getAll().get(1).getId(),
                                500,
                                3
                        ),
                        new Payment(
                                database.studentDao().getAll().get(3).getId(),
                                database.courseDao().getAll().get(1).getId(),
                                1100,
                                4
                        ),
                        new Payment(
                                database.studentDao().getAll().get(4).getId(),
                                database.courseDao().getAll().get(2).getId(),
                                1000,
                                5
                        ),
                        new Payment(
                                database.studentDao().getAll().get(5).getId(),
                                database.courseDao().getAll().get(2).getId(),
                                1000,
                                6
                        ),
                        new Payment(
                                database.studentDao().getAll().get(6).getId(),
                                database.courseDao().getAll().get(3).getId(),
                                700,
                                7
                        ),
                        new Payment(
                                database.studentDao().getAll().get(7).getId(),
                                database.courseDao().getAll().get(3).getId(),
                                800,
                                8
                        )
                );
    }

    public void fillDB(){
        fillLanguages();
        fillProficiencies();

        fillStudents();
        fillTeachers();

        fillStudentLanguageProficiency();
        fillTeacherLanguageProficiency();

        fillCourses();
        fillCoursesLanguageProficiencyLevel();

        fillCourseStudent();
        fillCourseTeacher();

        fillPaymentForm();
        fillStudentPaymentForm();
        fillPayment();

        //log();
    }

    public void log(){
        ArrayList<Student> students = new ArrayList<>();
        students.addAll(
                (ArrayList<Student>)
                        database
                            .studentDao()
                                .getAll()
        );
        for (int i = 0; i< students.size(); i++){
            Student student = students.get(i);
            Log.d("student", student.getId()+" "+ student.getFirstName() +" "+ student.getLastName());
            Course course = database.courseDao().getCourseById(
                    database.courseStudentDao().getCourseStudentByStudentId(
                            student.getId()
                    ).getCourseId()
            );
            Log.d("course", course.getCourseName());
            Teacher teacher = database.teacherDao().getTeacherById(
                    database.courseTeacherDao().getCourseTeacherByCourseId(
                            course.getId()
                    ).getTeacherId()
            );
            Log.d("teacher", teacher.getId() + " " + teacher.getFirstName() +" " + teacher.getLastName() + "\n");
            Log.d("paymentForm",
                        database.paymentFormDao().getPaymentFormById(
                                database.studentPaymentFormDao().getStudentPaymentFormByStudentId(
                                        student.getId()
                                ).getPaymentFormId()
                        ).getPaymentForm()
                    );
            ArrayList<Payment> payments = new ArrayList<>();
            payments.addAll(
                            database.paymentDao().getAll()
            );
            for (int j = 0; j< payments.size(); j++){
                if (payments.get(j).getStudentId() == student.getId()){
                    Log.d("payment", payments.get(j).getAmount() + " ");
                }
            }
        }
    }





}
