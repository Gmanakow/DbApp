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

    @Insert
    public void insertAll(Language... languages);
}
