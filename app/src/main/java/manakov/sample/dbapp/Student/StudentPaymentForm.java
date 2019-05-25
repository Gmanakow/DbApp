package manakov.sample.dbapp.Student;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import manakov.sample.dbapp.PaymentForm.PaymentForm;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "student_paymentForm",
        indices = {
                @Index(
                        value = {"studentId","paymentFormId"},
                        unique = true
                )
        },
        primaryKeys = {"studentId", "paymentFormId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "id",
                        childColumns = "studentId",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = PaymentForm.class,
                        parentColumns = "id",
                        childColumns = "paymentFormId",
                        onDelete = CASCADE
                ),
        }
)

public class StudentPaymentForm {
    private int studentId;
    private int paymentFormId;

    public StudentPaymentForm(int studentId, int paymentFormId) {
        this.studentId = studentId;
        this.paymentFormId = paymentFormId;
    }

    public int getStudentId() {
        return studentId;
    }
    public int getPaymentFormId() {
        return paymentFormId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setPaymentFormId(int paymentFormId) {
        this.paymentFormId = paymentFormId;
    }
}
