package manakov.sample.dbapp.Course;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CourseDao {
    @Query("Select * from course")
    public List<Course> getAll();

    @Query("Delete from course")
    public void clear();

    @Query("Select * from course where id=:id Limit 1")
    public Course getCourseById(int id);

    @Insert
    public void insertAll(Course... courses);
}
