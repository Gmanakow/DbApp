package manakov.sample.dbapp.Teacher;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PaymentDao {
    @Query("select * from payment")
    public List<Payment> getAll();

    @Query("delete from payment")
    public void clear();

    @Insert
    public void insertAll(Payment... payments);
}
