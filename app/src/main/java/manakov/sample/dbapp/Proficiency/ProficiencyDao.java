package manakov.sample.dbapp.Proficiency;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ProficiencyDao {

    @Query("Select * from proficiency")
    public List<Proficiency> getAll();

    @Query("Delete from proficiency")
    public void clear();

    @Insert
    public void insertAll(Proficiency... proficiencies);
}
