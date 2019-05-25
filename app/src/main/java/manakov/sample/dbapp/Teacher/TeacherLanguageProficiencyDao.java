package manakov.sample.dbapp.Teacher;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TeacherLanguageProficiencyDao {
    @Insert
    public void insertAll(TeacherLanguageProficiency... teacherLanguageProficiency);

    @Query("select * from teacher_language_proficiency where teacherId =:teacherId Limit 1")
    public TeacherLanguageProficiency getTeacherLanguageProficiencyByTeacherId(int teacherId);

    @Query("select * from teacher_language_proficiency")
    public List<TeacherLanguageProficiency> getAll();

    @Query("Delete from teacher_language_proficiency")
    public void clear();
}
