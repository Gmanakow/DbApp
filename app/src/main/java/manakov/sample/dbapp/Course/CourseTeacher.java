package manakov.sample.dbapp.Course;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import manakov.sample.dbapp.Teacher.Teacher;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "course_teacher",
        primaryKeys = {"courseId","teacherId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "id",
                        childColumns = "courseId",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Teacher.class,
                        parentColumns = "id",
                        childColumns = "teacherId",
                        onDelete = CASCADE
                )
        }
)
public class CourseTeacher {
    private int courseId;
    private int teacherId;

    public CourseTeacher(int courseId, int teacherId) {
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public int getCourseId() {
        return courseId;
    }
    public int getTeacherId() {
        return teacherId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
