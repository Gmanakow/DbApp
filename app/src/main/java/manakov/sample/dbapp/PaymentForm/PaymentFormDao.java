package manakov.sample.dbapp.PaymentForm;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PaymentFormDao {
    @Insert
    public void insert(PaymentForm paymentForm);

    @Insert
    public void insertAll(PaymentForm... paymentForm);

    @Query("select * from paymentform where id=:id Limit 1")
    public PaymentForm getPaymentFormById(int id);

    @Query("Delete from paymentform")
    public void clear();

    @Query("Select * from paymentform")
    public List<PaymentForm> getAll();
}
