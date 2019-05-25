package manakov.sample.dbapp.Teacher;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.Proficiency.Proficiency;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "teacher_language_proficiency",
        primaryKeys = {"teacherId", "languageId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Teacher.class,
                        parentColumns = "id",
                        childColumns = "teacherId",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Language.class,
                        parentColumns = "id",
                        childColumns = "languageId",
                        onDelete = CASCADE
                ),
                @ForeignKey(
                        entity = Proficiency.class,
                        parentColumns = "id",
                        childColumns = "proficiencyId",
                        onDelete = CASCADE
                )
        }
)

public class TeacherLanguageProficiency {
    private int teacherId;
    private int languageId;
    private int proficiencyId;

    public TeacherLanguageProficiency(int teacherId, int languageId, int proficiencyId) {
        this.teacherId = teacherId;
        this.languageId = languageId;
        this.proficiencyId = proficiencyId;
    }

    public int getTeacherId() {
        return teacherId;
    }
    public int getLanguageId() {
        return languageId;
    }
    public int getProficiencyId() {
        return proficiencyId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
    public void setProficiencyId(int proficiencyId) {
        this.proficiencyId = proficiencyId;
    }

}
