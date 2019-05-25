package manakov.sample.dbapp.Course;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CourseLanguageProficiencyDao {
    @Insert
    public void insertAll(CourseLanguageProficiency... courseLanguageProficiency);

    @Query("select * from course_language_proficiency")
    public List<CourseLanguageProficiency> getAll();

    @Query("select * from course_language_proficiency where courseId =:courseId Limit 1")
    public CourseLanguageProficiency getCourseLanguageProficiencyByCourseId(int courseId);

    @Query("Delete from course_language_proficiency")
    public void clear();
}
