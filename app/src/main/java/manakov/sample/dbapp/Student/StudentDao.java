package manakov.sample.dbapp.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {

    @Query("Select * from student")
    public List<Student> getAll();

    @Query("select * from student where id =:id Limit 1")
    public Student getStudentById(int id);

    @Query("Delete from student where id=:id")
    public void deleteStudentByStudentId(int id);

    @Query("Delete from student")
    public void clear();

    @Insert
    public void insertAll(Student... students);
}
