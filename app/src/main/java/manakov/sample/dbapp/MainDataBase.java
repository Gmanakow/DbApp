package manakov.sample.dbapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.Language.LanguageDao;
import manakov.sample.dbapp.Proficiency.Proficiency;
import manakov.sample.dbapp.Proficiency.ProficiencyDao;
import manakov.sample.dbapp.Student.Student;
import manakov.sample.dbapp.Student.StudentDao;
import manakov.sample.dbapp.Student.StudentLanguageProficiency;
import manakov.sample.dbapp.Student.StudentLanguageProficiencyDao;
import manakov.sample.dbapp.Teacher.Teacher;
import manakov.sample.dbapp.Teacher.TeacherDao;

@Database(
        entities = {
            Student.class,
            Teacher.class,
            Language.class,
            Proficiency.class,
            StudentLanguageProficiency.class
        },
        version = 1,
        exportSchema = false
)
public abstract class MainDataBase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract TeacherDao teacherDao();
    public abstract LanguageDao languageDao();
    public abstract ProficiencyDao proficiencyDao();
    public abstract StudentLanguageProficiencyDao studentLanguageProficiencyDao();
}
