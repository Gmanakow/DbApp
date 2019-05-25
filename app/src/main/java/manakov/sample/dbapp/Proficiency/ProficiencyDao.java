package manakov.sample.dbapp.Proficiency;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

@Dao
public interface ProficiencyDao {

    @Query("Select * from proficiency")
    public List<Proficiency> getAll();

    @Query("Delete from proficiency")
    public void clear();

    @Query("Select * from proficiency where id=:id limit 1")
    public Proficiency getProficiencyById(int id);

    @Insert
    public void insertAll(Proficiency... proficiencies);
}
