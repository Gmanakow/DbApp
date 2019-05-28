package manakov.sample.dbapp.PaymentForm;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import manakov.sample.dbapp.Course.Course;
import manakov.sample.dbapp.Student.Student;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "payment",
        indices = {
                @Index(
                        value = {"studentId","courseId"},
                        unique = true
                )
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "id",
                        childColumns = "studentId",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "id",
                        childColumns = "courseId",
                        onDelete = CASCADE
                )
        }
)
public class Payment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int studentId;
    private int courseId;
    private int amount;

    public Payment(int studentId, int courseId, int amount) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }
    public int getStudentId() {
        return studentId;
    }
    public int getCourseId() {
        return courseId;
    }
    public int getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
