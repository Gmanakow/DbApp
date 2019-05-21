package manakov.sample.dbapp.Student;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentLanguageProficiencyDao {
    @Insert
    public void insert(StudentLanguageProficiency studentLanguageProficiency);

    @Query("select * from student_language_proficiency")
    public List<StudentLanguageProficiency> getAll();

    @Query("Delete from student_language_proficiency")
    public void clear();
}
