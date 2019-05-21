package manakov.sample.dbapp.Student;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {

    @Query("Select * from student")
    public List<Student> getAll();

    @Query("Delete from student")
    public void clear();

    @Insert
    public void insertAll(Student... students);
}
