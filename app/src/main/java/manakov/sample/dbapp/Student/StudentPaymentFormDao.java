package manakov.sample.dbapp.Student;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import manakov.sample.dbapp.PaymentForm.PaymentForm;

@Dao
public interface StudentPaymentFormDao {
    @Query("Select * from student_paymentForm")
    public List<StudentPaymentForm> getAll();

    @Insert
    public void insert(StudentPaymentForm studentPaymentForm);

    @Insert
    public void insertAll(StudentPaymentForm... studentPaymentForm);

    @Query("Delete from student_paymentForm")
    public void clear();

    @Query("select * from student_paymentForm where studentId=:studentId Limit 1")
    public StudentPaymentForm getStudentPaymentFormByStudentId(int studentId);

    @Query("select * from student_paymentForm where paymentFormId=:paymentFormId")
    public List<StudentPaymentForm> getStudentPaymentFormByPaymentFormId(int paymentFormId);
}
