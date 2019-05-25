package manakov.sample.dbapp.Language;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LanguageDao {

    @Query("Select * from language")
    public List<Language> getAll();

    @Query("Delete from language")
    public void clear();

    @Query("Select * from language where id = :id Limit 1")
    public Language getLanguageById(int id);

    @Insert
    public void insertAll(Language... languages);
}
