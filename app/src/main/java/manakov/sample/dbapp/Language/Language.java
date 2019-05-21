package manakov.sample.dbapp.Language;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Language {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String language;

    public Language(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }
    public String getLanguage() {
        return language;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
}
