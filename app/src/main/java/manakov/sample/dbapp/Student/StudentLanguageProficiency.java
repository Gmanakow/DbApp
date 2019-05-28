package manakov.sample.dbapp.Student;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.Proficiency.Proficiency;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "student_language_proficiency",
        primaryKeys = {"studentId", "languageId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "id",
                        childColumns = "studentId",
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
public class StudentLanguageProficiency {
    private int studentId;
    private int languageId;
    private int proficiencyId;

    public StudentLanguageProficiency(int studentId, int languageId, int proficiencyId) {
        this.studentId = studentId;
        this.languageId = languageId;
        this.proficiencyId = proficiencyId;
    }

    public int getStudentId() {
        return studentId;
    }
    public int getLanguageId() {
        return languageId;
    }
    public int getProficiencyId() {
        return proficiencyId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
    public void setProficiencyId(int proficiencyId) {
        this.proficiencyId = proficiencyId;
    }


}

