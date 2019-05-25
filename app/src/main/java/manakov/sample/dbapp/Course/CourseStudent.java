package manakov.sample.dbapp.Course;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import manakov.sample.dbapp.Student.Student;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "course_student",
        primaryKeys = {"courseId","studentId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "id",
                        childColumns = "courseId",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "id",
                        childColumns = "studentId",
                        onDelete = CASCADE
                )
        }
)
public class CourseStudent {
    private int courseId;
    private int studentId;

    public CourseStudent(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
