package manakov.sample.dbapp.Course;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CourseStudentDao {
    @Insert
    public void insertAll(CourseStudent... courseStudents);

    @Query("select * from course_student where studentId=:studentId Limit 1")
    public CourseStudent getCourseStudentByStudentId(int studentId);

    @Query("select * from course_student where courseId=:courseId")
    public List<CourseStudent> getCourseStudentsByCourseId(int courseId);

    @Query("select * from course_student where studentId=:studentId")
    public List<CourseStudent> getCourseStudentsByStudentId(int studentId);

    @Query("select * from course_student")
    public List<CourseStudent> getAll();

    @Query("Delete from course_student")
    public void clear();
}
