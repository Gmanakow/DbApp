package manakov.sample.dbapp.Teacher;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TeacherDao {
    @Query("Select * from teacher")
    public List<Teacher> getAll();

    @Query("select * from teacher where id =:id Limit 1")
    public Teacher getTeacherById(int id);

    @Query("delete from teacher where id = :id")
    public void deleteTeacherByTeacherId(int id);

    @Query("Delete from teacher")
    public void clear();

    @Insert
    public void insertAll(Teacher... teachers);
}
