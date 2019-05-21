package manakov.sample.dbapp.Proficiency;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Proficiency {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String proficiency;

    public Proficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public int getId() {
        return id;
    }
    public String getProficiency() {
        return proficiency;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}
