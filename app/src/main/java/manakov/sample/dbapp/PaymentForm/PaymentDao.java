package manakov.sample.dbapp.PaymentForm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import manakov.sample.dbapp.PaymentForm.Payment;

@Dao
public interface PaymentDao {
    @Query("select * from payment")
    public List<Payment> getAll();

    @Query("delete from payment")
    public void clear();

    @Query("select * from payment where studentId =:studentId")
    public List<Payment> getPaymentsByStudentId(int studentId);

    @Insert
    public void insertAll(Payment... payments);

    @RawQuery(observedEntities = Payment.class)
    List<Payment> getPaymentsByDates(SupportSQLiteQuery query);

    @Query("select * from payment where date>:firstDate and date<:secondDate")
    public List<Payment> getPaymentsByDatesSecure(int firstDate, int secondDate);
}
