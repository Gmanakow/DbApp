package manakov.sample.dbapp.Teacher;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TeacherDao {
    @Query("Select * from teacher")
    public List<Teacher> getAll();

    @Query("Delete from teacher")
    public void clear();

    @Insert
    public void insertAll(Teacher... teachers);
}