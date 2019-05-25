package manakov.sample.dbapp.Course;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.Proficiency.Proficiency;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "course_language_proficiency",
        primaryKeys = {"courseId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "id",
                        childColumns = "courseId",
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
public class CourseLanguageProficiency {
    private int courseId;
    private int languageId;
    private int proficiencyId;

    public CourseLanguageProficiency(int courseId, int languageId, int proficiencyId) {
        this.courseId = courseId;
        this.languageId = languageId;
        this.proficiencyId = proficiencyId;
    }

    public int getCourseId() {
        return courseId;
    }
    public int getLanguageId() {
        return languageId;
    }
    public int getProficiencyId() {
        return proficiencyId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
    public void setProficiencyId(int proficiencyId) {
        this.proficiencyId = proficiencyId;
    }


}
