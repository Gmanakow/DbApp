package manakov.sample.dbapp.Course;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CourseTeacherDao {
    @Insert
    public void insertAll(CourseTeacher... courseTeachers);

    @Query("select * from course_teacher where teacherId=:teacherId Limit 1")
    public CourseTeacher getCourseTeacherByTeacherId(int teacherId);

    @Query("select * from course_teacher where courseId=:courseId Limit 1")
    public CourseTeacher getCourseTeacherByCourseId(int courseId);

    @Query("select * from course_teacher")
    public List<CourseTeacher> getAll();

    @Query("Delete from course_teacher")
    public void clear();
}
